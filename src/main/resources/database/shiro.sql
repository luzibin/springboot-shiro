## USE test;

/**
CREATE TABLE wp_sys_user(
	UserID INT NOT NULL AUTO_INCREMENT,
	role_id INT NOT NULL,
	 BDate   DATETIME  NOT NULL,
	 EDate   DATETIME  NULL,
	 isActive   BIT  NOT NULL,
	 guid   VARCHAR (22) NOT NULL,
	 add_date   DATETIME  NOT NULL,
	 NAME   VARCHAR (20) NULL,
	 email   VARCHAR (50) NULL,
	 PASSWORD   VARCHAR (32) NULL,
	 account   VARCHAR (30) NULL,
	 mobile   VARCHAR (20) NULL,
	 time_stamp   TIMESTAMP  NOT NULL,
	PRIMARY KEY (UserID)
	);

**/

/*
INSERT INTO wp_sys_user(role_id,BDate,isActive,guid,add_date,NAME,PASSWORD,account,time_stamp) VALUE(100,NOW(),1,'100',NOW(),'管理员','e10adc3949ba59abbe56e057f20f883e','wpPallas',now());
*/
