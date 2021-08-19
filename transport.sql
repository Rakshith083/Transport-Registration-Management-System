*********************************************************************************************************************************************************
Table Creation :
*********************************************************************************************************************************************************
CREATE TABLE TRANSPORT_AGENCY
(
  AGC_Code VARCHAR(20),
  Agc_Name VARCHAR(20) Not null,
  Number_of_Bus INT  Not null,
  HeadQuaters VARCHAR(20)  Not null,
  PRIMARY KEY (AGC_Code)
);

select * from TRANSPORT_AGENCY

CREATE TABLE BUS
(
  Bus_number  varchar(20),
  SEATS_AVAILABLE int Not null,
  Origion VARCHAR(20) Not null,
  Destination VARCHAR(20) Not null,
  Departure_Time VARCHAR(20) Not null,
  Arrival_time VARCHAR(20) Not null,
  AGC_Code  VARCHAR(20) ,
  PRIMARY KEY (Bus_number),
  FOREIGN KEY (AGC_Code) REFERENCES TRANSPORT_AGENCY(AGC_Code)  On delete cascade
);

select * from Bus

CREATE TABLE PASSENGER
(
  Pass_Id  varchar(20) PRIMARY KEY,
  Name VARCHAR(20) Not null,
  Phone NUMERIC,
  Date_of_travel DATE Not null,
  Destination VARCHAR(20) Not null,
  Bus_number varchar(20) REFERENCES BUS(Bus_number) On delete cascade
)

CREATE TABLE BOOKING
(
  Booking_ID  varchar(20) ,
  Booking_time VARCHAR(20) ,
  Booking_date DATE ,
  Pass_Id  varchar(20) ,
  PRIMARY KEY (Booking_ID),
  FOREIGN KEY (Pass_Id) REFERENCES PASSENGER(Pass_Id)  On delete cascade
);

CREATE TABLE TRANSACTION
(
  Transaction_id VARCHAR(20),
  Amount INT ,
  Pass_Id  VARCHAR(20),
  Booking_ID  VARCHAR(20),
  PRIMARY KEY (Transaction_id,Booking_ID,Pass_Id),
  FOREIGN KEY (Pass_Id) REFERENCES PASSENGER(Pass_Id)  On delete cascade,
  FOREIGN KEY (Booking_ID) REFERENCES BOOKING(Booking_ID) On delete cascade
);

******************************************************************************************************************************************************************************
Drop Table Commands
******************************************************************************************************************************************************************************
drop table TRANSACTION
drop table BOOKING
drop table PASSENGER
drop table BUS
drop table TRANSPORT_AGENCY

******************************************************************************************************************************************************************************
Trigger in Transport Agency Table

******************************************************************************************************************************************************************************
CREATE OR REPLACE trigger MQTT
BEFORE 
insert 
ON TRANSPORT_AGENCY
FOR EACH ROW
BEGIN 
	IF:NEW.Number_of_Bus<2 THEN
	RAISE_APPLICATION_ERROR(-20001,'Number_of_Bus Cant be < 0');
	END if;
END ;

*******************************************************************************************************************************************************************************

Trigger in Bus Table
*******************************************************************************************************************************************************************************
CREATE OR REPLACE trigger RAK1
BEFORE 
insert 
ON BUS
FOR EACH ROW
BEGIN 
	IF:NEW.SEATS_AVAILABLE<0 THEN
	RAISE_APPLICATION_ERROR(-20002,'No_Of_Seats Cant be < 0');
	END if;
END ;

*******************************************************************************************************************************************************************************
Stored Procedure in TRANSPORT_AGENCY Table
*******************************************************************************************************************************************************************************
CREATE OR REPLACE PROCEDURE PROCE(AGC_Code  IN VARCHAR,Name IN VARCHAR,Number_of_Bus IN INT,HeadQuaters IN VARCHAR) 
AS 
BEGIN INSERT INTO TRANSPORT_AGENCY VALUES(AGC_CodE,Name,Number_of_Bus,HeadQuaters);
end proce;
******************************************************************************************************************************************************************************
Trigger in TRANSACTION Table

******************************************************************************************************************************************************************************
CREATE OR REPLACE trigger RAK2
BEFORE 
insert 
ON TRANSACTION
FOR EACH ROW
BEGIN 
	IF:NEW.AMOUNT<=100 THEN
	RAISE_APPLICATION_ERROR(-20001,'Amount cant be 0 or less');
	END if;
END ;
