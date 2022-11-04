FROM python:3.8-slim

COPY requirements.txt .

RUN set -ex; \
  	pip install --no-cache-dir -r requirements.txt

ADD python-producer.py .

CMD ["python", "-u", "./python-producer.py"]

# docker build -t python-source-generator .
# docker run --network=host -it --rm python-source-generator
# Hello
#KAFKA_BROKERS=localhost:54595,localhost:54601,localhost:54600