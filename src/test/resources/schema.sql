CREATE TABLE POSITIONS(POSITION_ID int PRIMARY KEY auto_increment, COMPANY_NAME varchar(50), TITLE varchar(50));
CREATE TABLE USERS(USER_ID int PRIMARY KEY auto_increment, FIRST_NAME varchar(20), LAST_NAME varchar(20), POSITION_ID int references POSITIONS(POSITION_ID));
