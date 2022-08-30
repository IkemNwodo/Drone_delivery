create table `drones` (
    `serial_no` varchar(100) not null unique,
    `model` varchar(128),
    `weight_limit` decimal,
    `battery_capacity` decimal,
    `state` varchar(500),

    primary key (`serial_no`)
);

create table `medications` (
    `serial_no` varchar(100) not null,
    `id` int not null auto_increment,
    `name` varchar(500),
    `weight` decimal,
    `code` int,
    `image_path` varchar(max),

    primary key (`id`),


    constraint drones_medications foreign key (`serial_no`) references drones(`serial_no`)

);