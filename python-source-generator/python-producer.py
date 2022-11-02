import uuid
import datetime
import time
import schedule
import random
from kafka import KafkaProducer
from json import dumps  
from kafka.admin import KafkaAdminClient, NewTopic
from faker import Faker

kafka_nodes=['localhost:9092']
myTopic = 'people'

admin_client = KafkaAdminClient(
    bootstrap_servers=kafka_nodes, 
    client_id='python-test-01'
)
topics = []
topics = admin_client.list_topics()
print("List of topics -----> ", topics)


if myTopic not in topics:
    # create topic
    print("Creating topic as it does not exist")
    topic_list = []
    topic_list.append(NewTopic(name=myTopic, num_partitions=1, replication_factor=1))
    admin_client.create_topics(new_topics=topic_list, validate_only=False)


def gen_data():

    faker = Faker()

    # Producer instance
    my_data = {'id' : str(uuid.uuid4()), 'timestamp': str(datetime.datetime.now()), 'name': faker.name(), 'country': faker.country(), 'job': faker.job(), 'image': faker.image_url()}
    prod = KafkaProducer(bootstrap_servers=kafka_nodes,value_serializer = lambda x:dumps(x).encode('utf-8'))
    print(my_data)
    prod.send(topic=myTopic, value=my_data)
    prod.flush()

if __name__ == "__main__":

    gen_data()
    schedule.every(1).seconds.do(gen_data)

    while True:
        schedule.run_pending()
        time.sleep(0.5)