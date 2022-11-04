import datetime
import time
import uuid
from json import dumps

import schedule
from faker import Faker
from kafka import KafkaProducer
from kafka.admin import KafkaAdminClient, NewTopic

kafka_nodes=['redpanda:9092']
myTopic = 'people'


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
    schedule.every(3).seconds.do(gen_data)

    while True:
        schedule.run_pending()
        time.sleep(0.5)