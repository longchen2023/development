create table user_info (
id varchar(10) NOT NULL,
name varchar(50) NOT NULL,
password varchar(20) NOT NULL,
register_time varchar(20) NOT NULL,
update_time varchar(20),
PRIMARY KEY (id)
);

create table authority_info  (
id int NOT NULL,
user_id varchar(10) NOT NULL,
function_id int NOT NULL,
availability_flag varchar(1) NOT NULL,
valid_flag varchar(1) NOT NULL,
register_time varchar(20) NOT NULL,
update_time varchar(20),
PRIMARY KEY (id)
);

create table function_info   (
id int NOT NULL,
function_no varchar(20) NOT NULL,
name varchar(50) NOT NULL,
register_time varchar(20) NOT NULL,
update_time varchar(20),
PRIMARY KEY (id)
);

create table izakaya_info   (
id int NOT NULL,
user_id varchar(10) NOT NULL,
name varchar(50) NOT NULL,
area varchar(20) NOT NULL,
genre varchar(1) NOT NULL,
address varchar(50) NOT NULL,
tele varchar(13) NOT NULL,
max_num_of_people int NOT NULL,
price int NOT NULL,
opening_time varchar(4) NOT NULL,
closing_time varchar(4) NOT NULL,
holiday varchar(50) NOT NULL,
url varchar(50) NOT NULL,
register_time varchar(20) NOT NULL,
update_time varchar(20),
PRIMARY KEY (id)
);

create table comment_info   (
id int NOT NULL,
content varchar(100) NOT NULL,
assessment varchar(1) NOT NULL,
valid_flag varchar(1) NOT NULL,
izakaya_id int NOT NULL,
user_id varchar(10) NOT NULL,
register_time varchar(20) NOT NULL,
update_time varchar(20),
PRIMARY KEY (id)
);

create sequence seq_izakaya_info 
	minvalue 1 
	maxvalue 1000000 
	start with 1 
	increment by 1 
	cache 20 
	cycle 
	order;
	
create sequence seq_izakaya_comment_info 
	minvalue 1 
	maxvalue 1000000 
	start with 1 
	increment by 1 
	cache 20 
	cycle 
	order;

