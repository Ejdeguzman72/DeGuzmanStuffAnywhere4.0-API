-- SQL StARTUP DATABASE SCRIPTS 

-- Drop table

-- DROP TABLE public.auto_shop;

CREATE TABLE public.auto_shop (
	auto_shop_id serial NOT NULL,
	address varchar(255) NULL,
	auto_shop_name varchar(255) NULL,
	city varchar(255) NULL,
	state varchar(255) NULL,
	zip varchar(255) NULL,
	CONSTRAINT auto_shop_pkey PRIMARY KEY (auto_shop_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.auto_transactions;

CREATE TABLE public.auto_transactions (
	auto_transaction_id bigserial NOT NULL,
	amount float8 NULL,
	auto_transaction_date varchar(255) NULL,
	auto_shop_id int4 NULL,
	vehicle_id int8 NULL,
	transaction_type_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT auto_transactions_pkey PRIMARY KEY (auto_transaction_id)
);

ALTER TABLE public.auto_transactions ADD CONSTRAINT fkle3yre5oe5iu03wop2xt6pfoy FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE public.auto_transactions ADD CONSTRAINT fkoxw0x915af5hd3ywmgwy8yb2v FOREIGN KEY (auto_shop_id) REFERENCES auto_shop(auto_shop_id);
ALTER TABLE public.auto_transactions ADD CONSTRAINT fkqru82k5cwgf70etiyxdw6dck4 FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(transaction_type_id);
ALTER TABLE public.auto_transactions ADD CONSTRAINT fkr6f794uw5jg7myifxldd7cgaj FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.books;

CREATE TABLE public.books (
	book_id serial NOT NULL,
	author varchar(255) NULL,
	descr varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT book_pkey PRIMARY KEY (book_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.exercise;

CREATE TABLE public.exercise (
	exercise_id serial NOT NULL,
	"date" varchar NULL,
	exercise_name varchar(255) NULL,
	reps int4 NULL,
	"sets" int4 NULL,
	weight float8 NULL,
	exercise_type_id int4 NULL,
	user_id int8 NULL,
	CONSTRAINT exercise_pkey PRIMARY KEY (exercise_id)
);

ALTER TABLE public.exercise ADD CONSTRAINT fk29kq784csd7ats3udx8rapviy FOREIGN KEY (exercise_type_id) REFERENCES exercise_type(exercise_type_id);
ALTER TABLE public.exercise ADD CONSTRAINT fkbule0evr8cieoj5aah3fbvinw FOREIGN KEY (user_id) REFERENCES users(user_id);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.exercise_type;

CREATE TABLE public.exercise_type (
	exercise_type_id serial NOT NULL,
	exercise_type_name varchar(255) NULL,
	CONSTRAINT exercise_type_pkey PRIMARY KEY (exercise_type_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.general_transaction;

CREATE TABLE public.general_transaction (
	transaction_id bigserial NOT NULL,
	amount float8 NULL,
	entity varchar(255) NULL,
	payment_date varchar(255) NULL,
	transaction_type_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT general_transaction_pkey PRIMARY KEY (transaction_id)
);

ALTER TABLE public.general_transaction ADD CONSTRAINT fk5o2tuwv73y71s84wx8jqtx6ab FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE public.general_transaction ADD CONSTRAINT fk6m5979tfgkr26v49x8cc1487l FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(transaction_type_id);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.home;

CREATE TABLE public.home (
	home_id serial NOT NULL,
	address01 varchar(255) NULL,
	city varchar(255) NULL,
	state varchar(255) NULL,
	zip varchar(255) NULL,
	address02 text NULL,
	CONSTRAINT home_pkey PRIMARY KEY (home_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.medical_office;

CREATE TABLE public.medical_office (
	medical_office_id bigserial NOT NULL,
	address varchar(255) NULL,
	city varchar(255) NULL,
	"name" varchar(255) NULL,
	state varchar(255) NULL,
	zip varchar(255) NULL,
	CONSTRAINT facility_pkey PRIMARY KEY (medical_office_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.medical_transactions;

CREATE TABLE public.medical_transactions (
	medical_transaction_id bigserial NOT NULL,
	amount float8 NULL,
	medical_transaction_date varchar(255) NULL,
	medical_office_id int8 NULL,
	transaction_type_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT medical_transactions_pkey PRIMARY KEY (medical_transaction_id)
);

ALTER TABLE public.medical_transactions ADD CONSTRAINT fknjwq24h5ngu89loglwij4hsga FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(transaction_type_id);
ALTER TABLE public.medical_transactions ADD CONSTRAINT fko4bgiuyy0t2pndkvxnw73h3qk FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE public.medical_transactions ADD CONSTRAINT fkohtsehww7guto7e4cde259vyq FOREIGN KEY (medical_office_id) REFERENCES medical_office(medical_office_id);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.person;

CREATE TABLE public.person (
	person_id bigserial NOT NULL,
	birthdate varchar(255) NULL,
	email varchar(255) NULL,
	firstname varchar(255) NULL,
	lastname varchar(255) NULL,
	phone varchar(255) NULL,
	address01 varchar(255) NULL,
	address02 varchar(255) NULL,
	city varchar(255) NULL,
	middle_initial varchar(255) NULL,
	state varchar(255) NULL,
	zipcode varchar(255) NULL,
	age int4 NULL,
	CONSTRAINT person_pkey PRIMARY KEY (person_id)
);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.recipe;

CREATE TABLE public.recipe (
	recipe_id serial NOT NULL,
	"name" varchar(255) NULL,
	recipe_type_id int4 NULL,
	CONSTRAINT recipe_pkey PRIMARY KEY (recipe_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.recipe_type;

CREATE TABLE public.recipe_type (
	recipe_type_id serial NOT NULL,
	descr varchar(255) NULL,
	CONSTRAINT recipe_type_pkey PRIMARY KEY (recipe_type_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.restaurant;

CREATE TABLE public.restaurant (
	restaurant_id serial NOT NULL,
	address varchar(255) NULL,
	city varchar(255) NULL,
	"name" varchar(255) NULL,
	state varchar(255) NULL,
	zip varchar(255) NULL,
	restaurant_type_id int4 NULL,
	CONSTRAINT restaurant_pkey PRIMARY KEY (restaurant_id)
);

ALTER TABLE public.restaurant ADD CONSTRAINT fkj8wsklhq1mfna9b1wn0c5xirs FOREIGN KEY (restaurant_type_id) REFERENCES restaurant_type(restaurant_type_id);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.restaurant_type;

CREATE TABLE public.restaurant_type (
	restaurant_type_id serial NOT NULL,
	descr varchar(255) NULL,
	CONSTRAINT restaurant_type_pkey PRIMARY KEY (restaurant_type_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	role_id serial NOT NULL,
	role_descr varchar(255) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.run_tracker;

CREATE TABLE public.run_tracker (
	run_id bigserial NOT NULL,
	run_date varchar(255) NULL,
	run_distance float8 NULL,
	run_time varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT run_tracker_pkey PRIMARY KEY (run_id)
);

ALTER TABLE public.run_tracker ADD CONSTRAINT fk71nsf54t47ah22gml97ymfug1 FOREIGN KEY (user_id) REFERENCES users(user_id);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.song;

CREATE TABLE public.song (
	song_id serial NOT NULL,
	artist varchar(255) NULL,
	genre varchar(255) NULL,
	title varchar(255) NULL,
	CONSTRAINT song_pkey PRIMARY KEY (song_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.transaction_type;

CREATE TABLE public.transaction_type (
	transaction_type_id bigserial NOT NULL,
	transaction_type_descr varchar(255) NULL,
	CONSTRAINT transaction_type_pkey PRIMARY KEY (transaction_type_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.user_status;

CREATE TABLE public.user_status (
	user_status_id serial NOT NULL,
	user_status_descr varchar(255) NULL,
	CONSTRAINT user_status_pkey PRIMARY KEY (user_status_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id bigserial NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	role_id int4 NULL,
	user_status_id int4 NULL,
	auto_transaction_id int8 NULL,
	transaction_id int8 NULL,
	medical_transaction_id int8 NULL,
	exercise_id int4 NULL,
	run_id int8 NULL,
	CONSTRAINT uk_dc4eq7plr20fdhq528twsak1b UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

ALTER TABLE public.users ADD CONSTRAINT fkmbe8x0swyl1ow97qfm9dbeami FOREIGN KEY (user_status_id) REFERENCES user_status(user_status_id);
ALTER TABLE public.users ADD CONSTRAINT fkrl3jm05yj85i454t8abexh0rf FOREIGN KEY (role_id) REFERENCES roles(role_id);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.utility;

CREATE TABLE public.utility (
	utility_id bigserial NOT NULL,
	due_date varchar(255) NULL,
	"name" varchar(255) NULL,
	phone varchar(255) NULL,
	url varchar(255) NULL,
	utility_type_id int4 NULL,
	CONSTRAINT utility_pkey PRIMARY KEY (utility_id)
);

ALTER TABLE public.utility ADD CONSTRAINT fksclxkox6mjle4xwxyni4yysuk FOREIGN KEY (utility_type_id) REFERENCES utility_type(utility_type_id);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.utility_type;

CREATE TABLE public.utility_type (
	utility_type_id serial NOT NULL,
	utility_type_descr varchar(255) NULL,
	CONSTRAINT utility_type_pkey PRIMARY KEY (utility_type_id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Drop table

-- DROP TABLE public.vehicle;

CREATE TABLE public.vehicle (
	vehicle_id bigserial NOT NULL,
	capacity int4 NULL,
	make varchar(255) NULL,
	model varchar(255) NULL,
	transmission varchar(255) NULL,
	"year" varchar(255) NULL,
	CONSTRAINT car_pkey PRIMARY KEY (vehicle_id)
);



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');