drop table if exists client;
drop table if exists meeting;
drop table if exists salesperson;


create table salesperson(
    -- primary key
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT newid(),
    -- data
    username text not null,
    password text not null,
    primavera_id text not null
    -- foreign keys
);

create table client(
    -- primary key
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT newid(),
    -- data
    primavera_id text not null,
    -- foreign keys
    vendor_id UNIQUEIDENTIFIER not null REFERENCES salesperson(id)
);

create table meeting(
    -- primary key
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT newid(),
    -- data
    meeting_name text not null,
    start_time datetime not null,
    end_time datetime not null,
    description text not null,
    -- foreign keys
    vendor_id UNIQUEIDENTIFIER not null REFERENCES salesperson(id)
);