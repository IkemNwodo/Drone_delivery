
    create table if not exists batteryHistories (
       id bigint not null auto_increment,
        batteryCapacity integer,
        serialNo varchar(255),
        primary key (id)
    );

    create table if not exists drones (
       serialNo varchar(255) not null,
        batteryCapacity integer,
        model varchar(255),
        state varchar(255),
        weightLimit double,
        primary key (serialNo)
    );

    create table if not exists medications (
       Id integer not null auto_increment,
        code integer,
        imagePath varchar(255),
        name varchar(255),
        weight double,
        serialNo varchar(255) not null,
        primary key (Id)
    );

    alter table batteryHistories
       add constraint if not exists drones_battery_histories
       foreign key (serialNo)
       references drones;

    alter table medications
       add constraint if not exists drones_medications
       foreign key (serialNo)
       references drones;