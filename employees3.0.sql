CREATE DATABASE employees;
USE employees;

CREATE TABLE Employee(
	EmployeeID int(15) PRIMARY KEY auto_increment,
	FirstName varchar(40),
	SurName varchar(40),
	Gender enum('Female', 'Male'),
	BirthDate Date
);

CREATE TABLE `Position`(
	PositionID int(15) auto_increment,
	position_name varchar(40),
	primary key(PositionID)
);

CREATE TABLE Contract(
	ContractID int(15) PRIMARY KEY auto_increment,
	contract_name varchar(40)
);

CREATE TABLE Contact(
	EmployeeID int(15),
	Phone int(15),
	Email varchar(40),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE Address(
	EmployeeID int(15),
	Country varchar(40),
	City varchar(40),
	Street varchar(40),
	PostCode varchar(10),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE Salary(
	EmployeeID int(15),
	Salary float(15),
	Salary_date DATE,
	Hours float(15),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);



CREATE TABLE Employment_detail(
	EmployeeID int(15),
	PositionID int(15),
	ContractID int(15),
	Salary_per_hour float(15),
	Start_work date,
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade,
	foreign key(PositionID) references `Position`(PositionID) on update cascade on delete cascade,
	foreign key(ContractID) references Contract(ContractID) on update cascade on delete cascade
);

CREATE TABLE Work_shift (
	EmployeeID int(15) unique,
	Check_time TIMESTAMP,
	`Type` ENUM('IN','OUT'),
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE Past_shift(
	EmployeeID int(15),
	Enter_time TIMESTAMP,
	Exit_time TIMESTAMP,
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade on delete cascade
);

CREATE TABLE `login`(
	Username varchar(20) not null unique,
	Password varchar(30) not null
);

CREATE TABLE Hours(
	EmployeeID int(15),
	Hours int(15),
	Since DATE,
	foreign key(EmployeeID) references Employee(EmployeeID) on update cascade
);

delimiter //
CREATE TRIGGER sum_hours AFTER UPDATE ON work_shift FOR EACH ROW
BEGIN
IF new.`type`='OUT' THEN
BEGIN
DECLARE tmp_time INT(5);
SET tmp_time = (SELECT time_to_sec(timediff(new.Check_time, old.Check_time))/3600);
IF (SELECT EXISTS(SELECT 1 FROM Hours WHERE Hours.EmployeeID=OLD.EmployeeID)) != '0' THEN
UPDATE Hours set Hours=Hours+tmp_time WHERE EmployeeID=OLD.EmployeeID;
ELSE 
INSERT INTO Hours VALUES (OLD.EmployeeID, tmp_time, NEW.Check_time);
END IF;
INSERT INTO Past_shift VALUES (OLD.EmployeeID, OLD.Check_time, NEW.Check_time);
END;
END IF;
END; //
delimiter ;

INSERT INTO `login` VALUES ('admin','admin');

INSERT INTO `position` (position_name) VALUES
('Chief Executive Officer'),('Chief Operating Officer'),('Chief Financial Officer'),
('Marketing Manager'),('Production Manager'),('Operations Manager'),
('Quality manager'),('Accountant'),('Office manager'),('Receptionist'),
('Supervisor'),('Staff');

INSERT INTO contract (contract_name) VALUES
('full-time'),('part-time'),('fixed-term'),
('freelancer'),('consultant'),('contractor'),
('agency-staff'),('zero-hour');

INSERT INTO employee (firstname,surname,gender,birthdate) VALUES
('Caleb','Mills','Male','1977-01-01'),('Jacob','Dean','Male','1979-06-01'),('Louie','Hart','Male','1979-08-22'),
('Theo','Robinson','Male','1979-11-05'),('Zac','McCarthy','Male','1979-12-13'),('Darnell ','Bridges','Male','1981-09-30'),
('Conrad','Kirkland','Male','1982-03-26'),('Matias','Pruitt','Male','1983-04-05'),('Leonidas','Henderson','Male','1985-04-13'),
('Giovanny','Goff','Male','1985-12-26'),('Molly','Moss','Female','1986-03-27'),('Eliza','Richards','Female','1986-05-24'),
('Victoria','Robinson','Female','1987-01-05'),('Leah','Murray','Female','1987-07-29'),('Sophie','Moore','Female','1987-08-21'),
('Adalyn','Kim','Female','1980-09-21'),('Celeste','Lawson','Female','1980-11-22'),('Hallie','Boyle','Female','1981-03-20'),
('Eden','West','Female','1989-12-16'),('Natalee','Weeks','Female','1988-01-09'),('Sam','Hopkins','Male','1991-01-25');

INSERT INTO Contact VALUES
('1','0912159887','caleb.mills@gmail.com'),('2','0942654705','jacob.dean@yahoo.com'),('3','0944262587','louie.hart@gmail.com'),
('4','0903852552','theo.robinson@gmail.com'),('5','0922336951','zac.carthy@outlook.com'),('6','0944086221','darnell.bridges@yahoo.com'),
('7','0902123654','conrad.kirkland@yahoo.com'),('8','0922321203','matias.pruitt@yahoo.com'),('9','0914771561','leonidas.henderson@gmail.com'),
('10','0921366322','giovanny.goff@gmail.com'),('11','0913557262','molly.moss@gmail.com'),('12','0936998605','eliza.richards@outlook.com'),
('13','0921333456','victoria.robinson@yahoo.com'),('14','0905699621','leah.murray@outlook.com'),('15','0936258558','sophie.moore@outlook.com'),
('16','0902018059','adalyn.kim@gmail.com'),('17','0905753357','celeste.lawson@gmail.com'),('18','0904400567','hallie.boyle@gmail.com'),
('19','0921100588','eden.west@yahoo.com'),('20','0923025863','natalee.weeks@gmail.com'),('21','0904336974','sam.hopkins@outlook.com');

INSERT INTO address VALUES 
('1','Great Britain','London','9 Guild Street','SE19 5AD'),('2','Great Britain','London','13 Elizabeth Street','SW2E 1HF'),
('3','Great Britain','London','31 8th Street West','W4 6RZ'),('4','Great Britain','London','6 Crown Street','SW4E 1HF'),
('5','Great Britain','London','22 Victoria Court','SE20 5AD'),('6','Great Britain','London','6 Hamilton Road','SW3E 1HF'),
('7','Great Britain','London','12 Guild Street','E5 4XU'),('8','Great Britain','London','54 Chestnut Street','SW1E 1HF'),
('9','Great Britain','London','3 Madison Court','SE21 5AD'),('10','Great Britain','London','91 Crown Street','W3 6RZ'),
('11','Great Britain','London','14 Church Road','E5 4XU'),('12','Great Britain','London','6 Cross Street','E8 4XU'),
('13','Great Britain','London','7 4th Avenue','E5 4XU'),('14','Great Britain','London','11 Race Street','W1 6RZ'),
('15','Great Britain','London','7 Parker Street','SE21 5AD'),('16','Great Britain','London','22 Magnolia Drive','SW1E 1HF'),
('17','Great Britain','London','20 Route 64','E2 4XU'),('19','Great Britain','London','22 Ridge Road','E1 4XU'),
('19','Great Britain','London','1 New Street','SW1E 1HF'),('20','Great Britain','London','3 Ashley Court','SE21 5AD'),
('21','Great Britain','London','45 Linden Street','SE21 5AD');

INSERT INTO employment_detail VALUES
('1','1','1','25','2010-01-20'),('2','8','1','12','2010-12-15'),('3','12','3','20','2014-02-28'),
('4','11','1','20','2012-11-04'),('5','12','3','15','2014-01-04'),('6','12','7','11','2013-03-18'),
('7','2','1','19','2011-09-12'),('8','5','1','15','2014-10-23'),('9','9','3','12','2012-03-12'),
('10','12','7','13','2012-04-30'),('11','12','2','14','2011-12-09'),('12','12','1','10','2011-10-16'),
('13','3','1','20','2011-05-06'),('14','6','1','16','2014-03-20'),('15','12','1','10','2012-03-17'),
('16','4','1','17','2011-09-25'),('17','8','1','16','2014-05-23'),('18','10','1','12','2013-10-03'),
('19','12','1','13','2013-04-22'),('20','7','1','18','2015-07-31'),('21','12','1','8','2012-10-07');



