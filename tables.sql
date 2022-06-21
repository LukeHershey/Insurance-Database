create table Customer (
    cust_id int not null,
    name varchar(255) not null,
    age int not null,
    date_joined date not null,
    primary key (cust_id)
);

create table Car_policy (
    car_policy_id int not null,
    make varchar(50) not null,
    model varchar(50) not null,
    vehicle_year int not null,
    vin varchar(17) not null,
    mileage_py int not null,
    coverage int not null,
    monthly_payment int not null,
    start_date date not null,
    end_date date not null,
    primary key (car_policy_id)
);

create table Home_policy (
    home_policy_id int not null,
    address varchar(255) not null,
    area_sqft int not null,
    number_bedrooms int not null,
    number_bathrooms int not null,
    price int not null,
    coverage int not null,
    monthly_payment int not null,
    start_date date not null,
    end_date date not null,
    primary key (home_policy_id)
);

create table Life_policy (
    life_policy_id int not null,
    existing_conditions varchar(255) not null,
    coverage int not null,
    beneficiary varchar(255) not null,
    monthly_payment int not null,
    start_date date not null,
    primary key (life_policy_id)
);

create table Insures_car (
    cust_id int not null,
    car_policy_id int not null,
    primary key (cust_id, car_policy_id),
	foreign key (cust_id) references Customer
		on delete cascade,
    foreign key (car_policy_id) references Car_policy
		on delete cascade
);

create table Insures_home (
    cust_id int not null,
    home_policy_id int not null,
    primary key (cust_id, home_policy_id),
	foreign key (cust_id) references Customer
		on delete cascade,
    foreign key (home_policy_id) references Home_policy
		on delete cascade
);

create table Insures_life (
    cust_id int not null,
    life_policy_id int not null,
    primary key (cust_id, life_policy_id),
	foreign key (cust_id) references Customer
		on delete cascade,
    foreign key (life_policy_id) references Life_policy
		on delete cascade
);
