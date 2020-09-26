drop table customer if exists;
drop table customer_stocks if exists;
drop table done_requests if exists;
drop table running_requests if exists;
drop table deniad_requests if exists;
drop table stocks if exists;
drop table buyQueue if exists;
drop table sellQueue if exists;





create table customer (
	id integer not null,
	name varchar(100) not null,
	family varchar(100) not null,
	credit integer not null,
	primary key (id)
);

create table customer_stocks (
	customer_id integer not null,
	sign varchar(100) not null,
	volume integer not null,
	primary key (customer_id),
	constraint customer_id_fk foreign key(customer_id)
	references customer(id)
);

create table done_requests (
	customer_id integer not null,
	pricePerOne integer not null,
	volume integer not null,
	staticVolume integer not null,
	sign varchar(100) not null,
	type varchar(100) not null,
	state varchar(100) not null,
	reqmode varchar(100) not null,

	constraint customer_id_donefk foreign key(customer_id)
	references customer(id)
);

create table running_requests (
	customer_id integer not null,
	pricePerOne integer not null,
	volume integer not null,
	staticVolume integer not null,
	stock_sign varchar(100) not null,
	type varchar(100) not null,
	state varchar(100) not null,
	reqmode varchar(100) not null,

	constraint customer_id_runningfk foreign key(customer_id)
	references customer(id)
 
	constraint stock_sign_runningfk foreign key(stock_sign)
	refrences stocks(sign)
);

create table deniad_requests (
	customer_id integer not null,
	pricePerOne integer not null,
	volume integer not null,
	staticVolume integer not null,
	sign varchar(100) not null,
	type varchar(100) not null,
	state varchar(100) not null,
	reqmode varchar(100) not null,

	constraint customer_id_deniadfk foreign key(customer_id)
	references customer(id)
);

create table stocks (
	sign varchar(100) not null,
	price integer not null,
	primary key (sign)
);

create table buyQueue (
	stock_id varchar(100) not null,
	pricePerOne integer not null,
	volume integer not null,
	staticVolume integer not null,
	sign varchar(100) not null,
	type varchar(100) not null,
	state varchar(100) not null,
	reqmode varchar(100) not null,

	constraint stock_id_buyQueuefk foreign key(stock_id)
	references stocks(sign)
);

create table sellQueue (
	stock_id varchar(100) not null,
	pricePerOne integer not null,
	volume integer not null,
	staticVolume integer not null,
	sign varchar(100) not null,
	type varchar(100) not null,
	state varchar(100) not null,
	reqmode varchar(100) not null,

	constraint stock_id_sellQueuefk foreign key(stock_id)
	references stocks(sign)
);

insert into customer values (1, 'ali', 'alavi', 400);
insert into customer values (1, 'mamad', 'alavi', 50000);





