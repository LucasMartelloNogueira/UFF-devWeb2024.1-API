drop schema if exists petadoption;
create schema if not exists petadoption;
use petadoption;

drop table tbusers;
create table if not exists TbUsers (
	`id` int not null auto_increment,
    `name` varchar(100) not null,
    `email` varchar(128) not null,
    `password` varchar(256) not null,
    
    primary key(id)
);

create table if not exists TbPets (
	`id` int not null auto_increment,
    `name` varchar(64) not null,
    `animal` varchar(64) not null,
    `age` varchar(64) not null,
    `height` double not null,
    `weight` double not null,
    
    primary key(id)
);

create table if not exists TbSanctuaries (
	`id` int not null auto_increment,
    `ownerId` int not null,
    `name` varchar(64) not null,
    `address` varchar(128) not null,
	
    primary key (id)
);


create table if not exists TbSanctuaryPets (
	`id` int not null auto_increment,
    `sanctuaryId` int not null,
    `petId` int not null,
    `admissionDate` datetime not null,
    `status` varchar(64) not null,
    `observations` varchar(256),
    
    primary key(id)
);

drop table TbAdoptions;
create table if not exists TbAdoptions (
	`id` int not null auto_increment,
    `userId` int not null,
    `sanctuaryPetId` int not null,
    `dateTime` datetime not null,
    
    primary key (id)
);

