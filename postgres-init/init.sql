


CREATE TABLE people (
	id VARCHAR ( 500 )  PRIMARY KEY,
	name VARCHAR ( 500 ) NULL,
	timestamp VARCHAR ( 500 ) NULL,
	country VARCHAR ( 500 ) NULL,
	job VARCHAR ( 500 ) NULL,
	image VARCHAR ( 500 ) NULL,

);

SELECT id, name, timestamp, country, job, image
	FROM public.people;