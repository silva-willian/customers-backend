CREATE SCHEMA customers;
CREATE TABLE customers.customer (
	id SERIAL PRIMARY KEY,
	name varchar(200) NOT NULL,
	sex char(1) NOT NULL,
	email varchar(200) NOT NULL,
	cel_ddi varchar(5) NOT NULL,
	cel_ddd varchar(4) NOT NULL,
	cel_number varchar(20) NOT NULL,
	date_birth DATE NOT NULL,
	register_date TIMESTAMP NOT NULL,
	last_update_date TIMESTAMP NULL
)
WITH (
	OIDS=FALSE
);