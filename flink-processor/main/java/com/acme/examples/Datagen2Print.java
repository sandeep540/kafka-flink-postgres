package com.acme.examples;
import com.acme.examples.model.Product;
import org.apache.flink.api.common.typeinfo.TypeHint;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.datagen.DataGenerator;
import org.apache.flink.streaming.api.functions.source.datagen.DataGeneratorSource;
import org.apache.flink.streaming.api.functions.source.datagen.RandomGenerator;

import java.util.UUID;

public class Datagen2Print {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataGenerator<Product> generator = new RandomGenerator<>() {
            @Override
            public Product next() {
               Product p = new Product(
                        UUID.randomUUID(),
                        random.nextHexString(9),
                        random.nextHexString(11));

               return p;
            }
        };

       DataGeneratorSource<Product> source = new DataGeneratorSource<>(generator, 20, null);
       env.addSource(source, "DataGeneratorSource").returns(new TypeHint<>() {
       }).print();

       env.execute();

    }

    }


