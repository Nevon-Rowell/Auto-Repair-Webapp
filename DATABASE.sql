create database Auto_repair_shop;
use Auto_repair_shop;
create table Job(
jobID  int primary key auto_increment,
jobStatus VARCHAR(100),
jobNotes VARCHAR(500),
leadMech varchar(25)
);
select*from job;
select*from job_seq;
DROP table job;