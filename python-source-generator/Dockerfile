FROM python:3.8-slim

COPY requirements.txt .

RUN set -ex; \
  	pip install --no-cache-dir -r requirements.txt

ADD kafka-producer.py .

CMD ["python", "-u", "./pythob-producer.py"]

# docker build -t kafka-producer .
# docker run --network=host -it --rm kafka-producer

#KAFKA_BROKERS=localhost:54595,localhost:54601,localhost:54600