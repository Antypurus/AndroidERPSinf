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