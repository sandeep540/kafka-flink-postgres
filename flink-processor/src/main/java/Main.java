import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.HashSet;


public class Main {

    static final String TOPIC = "people";
    static final String BROKERS = "localhost:9092";
    //static final String BROKERS = "redpanda-1:29092,redpanda-2:29093,redpanda-3:29093";

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        

        final HashSet<TopicPartition> partitionSet = new HashSet<>(Arrays.asList(
                new TopicPartition("input", 0),
                new TopicPartition("input", 1),
                new TopicPartition("input", 2)));
        //KafkaSource.builder().setPartitions(partitionSet);

        //Define Source
        KafkaSource<Event> source =
                KafkaSource.<Event>builder()
                        .setBootstrapServers(BROKERS)
                        .setProperty("partition.discovery.interval.ms", "10000")
                        .setTopics(TOPIC)
                        .setGroupId("groupId-919292")
                        .setStartingOffsets(OffsetsInitializer.earliest())
                        .setValueOnlyDeserializer(new EventDeserializationSchema())
                        .build();

        DataStreamSource<Event> kafka =
                env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka");

        //Add Sink
        kafka.addSink(JdbcSink.sink( "insert into public.people (id, name, brand, date) values (?, ?, ?, ?)",
                (statement, event) -> {
                    statement.setString(1, event.id);
                    statement.setString(2, event.name);
                    statement.setString(3, event.brand);
                    statement.setString(4, event.date);
                },
                JdbcExecutionOptions.builder()
                        .withBatchSize(1000)
                        .withBatchIntervalMs(200)
                        .withMaxRetries(5)
                        .build(),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:postgresql://localhost:5438/postgres")
                        .withDriverName("org.postgresql.Driver")
                        .withUsername("postgres")
                        .withPassword("postgres")
                        .build()
        ));

       // kafka.print();
        env.execute("Kafka2postgres");
    }
}