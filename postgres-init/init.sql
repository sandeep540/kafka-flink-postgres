


CREATE TABLE people (
	id VARCHAR ( 500 )  PRIMARY KEY,
	name VARCHAR ( 500 ) NULL,
	brand VARCHAR ( 500 ) NULL,
	date VARCHAR ( 500 ) NULL
);

SELECT id, name, brand, date
	FROM public.people;