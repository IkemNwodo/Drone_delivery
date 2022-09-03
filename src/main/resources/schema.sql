/*create table `drones`
(
    `serial_no`        varchar(100) not null unique,
    `model`            varchar(128),
    `weight_limit`     decimal,
    `battery_capacity` decimal,
    `state`            varchar(500),

    primary key (`serial_no`)
);

create table `battery_history`
(
    `id`               int not null auto_increment,
    `battery_capacity` int not null,

    primary key (`id`),

    foreign key (`id`) references drones(`ser`)
);

create table `medications`
(
    `serial_no`  varchar(100) not null,
    `id`         int          not null auto_increment,
    `name`       varchar(500),
    `weight`     decimal,
    `code`       int,
    `image_path` varchar(max),

    primary key (`id`),


    constraint drones_medications foreign key (`serial_no`) references drones(`serial_no`)

);

*/

    create table batteryHistories (
       id bigint not null auto_increment,
        batteryCapacity integer,
        serialNo varchar(255),
        primary key (id)
    );

    create table drones (
       serialNo varchar(255) not null,
        batteryCapacity integer,
        model varchar(255),
        state varchar(255),
        weightLimit double,
        primary key (serialNo)
    );

    create table medications (
       Id integer not null auto_increment,
        code integer,
        imagePath varchar(255),
        name varchar(255),
        weight double,
        serialNo varchar(255) not null,
        primary key (Id)
    );

    alter table batteryHistories
       add constraint drones_battery_histories
       foreign key (serialNo)
       references drones;

    alter table medications
       add constraint drones_medications
       foreign key (serialNo)
       references drones;
