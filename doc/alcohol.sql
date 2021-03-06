-- drop and create database
drop DATABASE IF EXISTS `alcohol`;
CREATE DATABASE alcohol character set utf8;

use alcohol;

-- create user table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username',
  `password` varchar(50) NOT NULL COMMENT 'password',
  `first_name` varchar(20) NOT NULL  COMMENT 'first name',
  `last_name` varchar(20) NOT NULL  COMMENT 'last name',
  `email` varchar(50) NOT NULL COMMENT 'email address',
  `gender` INT NOT NULL COMMENT ' 0-male，1-female',
  `age` INT NOT NULL  COMMENT 'age',
  `researcher_id` varchar(50) NOT NULL COMMENT ' the special id  for researcher',
  `create_time` DATETIME COMMENT 'create time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='user table';

-- insert one user for test
INSERT INTO user(username,password,first_name,last_name,email,gender,age,researcher_id) VALUES('test','df352f37d6d606903a666bd14c2cac05','Nannan','Gu','test@gmail.com',0,30,'12345');




--
-- -- create ddt_questions table
-- DROP TABLE IF EXISTS `DDT_questions`;
-- CREATE TABLE `DDT_questions` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT ,
--   `one_day_money` INT NOT NULL COMMENT 'the choice of money for one day',
--   `more_days_money` INT NOT NULL  COMMENT 'the choice of money for more days',
--   `days` INT NOT NULL  COMMENT 'exact days for more days choice',
--
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ddt_questions table';
--
--
-- -- insert the 27 answers to ddt_questions table
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(54,55,117);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(55,75,61);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(19,25,53);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(31,85,7);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(14,25,19);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(47,50,160);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(15,35,13);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(25,60,14);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(78,80,162);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(40,55,62);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(11,30,7);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(67,75,119);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(34,35,186);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(27,50,21);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(69,85,91);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(49,60,89);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(80,85,157);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(24,35,29);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(33,80,14);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(28,30,179);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(34,50,30);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(25,30,80);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(41,75,20);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(54,60,111);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(54,80,30);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(22,25,136);
-- INSERT INTO DDT_questions(one_day_money,more_days_money,days) VALUES(20,55,7);


-- create ddt_records table
DROP TABLE IF EXISTS `DDT_Record`;
CREATE TABLE `DDT_Record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'the username of who answers these answers',
  `question_1`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_2`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_3`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_4`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_5`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_6`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_7`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_8`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_9`  INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_10` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_11` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_12` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_13` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_14` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_15` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_16` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_17` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_18` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_19` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_20` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_21` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_22` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_23` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_24` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_25` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_26` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_27` INT  NOT NULL COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `create_time` DATETIME COMMENT 'create time',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ddt_answer_records table';



-- SST_Record table

DROP TABLE IF EXISTS `SST_Record`;
CREATE TABLE `SST_Record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username of user',
  `block` int NOT NULL  COMMENT '0 - practice block, 1- first experiment block, 2- second, 3 third,4 fourth  ',
  `trials` INT  NOT NULL COMMENT 'trails of every block, in practice it is 10, in experiment it is 20',
  `incorrect` INT NOT NULL  COMMENT 'num of incorrect response of the test',
  `missed` INT NOT NULL  COMMENT 'num of missed response of the test',
  `reaction_time` FLOAT NOT NULL  COMMENT 'Mean reaction time to go stimuli',
  `percentage` FLOAT NOT NULL  COMMENT 'Percentage of correctly suppressed responses on stop trials',
  `create_time` DATETIME COMMENT 'create time',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='SST_Record table';


--
-- NBack_Record table
--
DROP TABLE IF EXISTS `NBack_Record`;
CREATE TABLE `NBack_Record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username of user',
  `block` int NOT NULL  COMMENT '0 - practice block, 1- first experiment block, 2- second, 3 third,4 fourth  ',
  `trials` INT  NOT NULL COMMENT 'trails of every block, in practice it is 10, in experiment it is 20',
  `level` int NOT NULL  COMMENT '1 -back，2-back or 3-back',
  `incorrect` INT NOT NULL  COMMENT 'num of incorrect response of the test',
  `missed` INT NOT NULL  COMMENT 'num of missed response of the test',
  `percentage` FLOAT NOT NULL  COMMENT 'Percentage of correct answer',
  `create_time` DATETIME COMMENT 'create time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='NBack_Record table';
--

-- INSERT INTO NBack_Record(username,block,trials,level,incorrect,missed,percentage) VALUES('test','1','20','1','2','2','88.88');
-- INSERT INTO NBack_Record(username,block,trials,level,incorrect,missed,percentage) VALUES('test','1','20','2','2','2','88.88');
-- INSERT INTO NBack_Record(username,block,trials,level,incorrect,missed,percentage) VALUES('test','1','20','2','2','2','88.88');
-- INSERT INTO NBack_Record(username,block,trials,level,incorrect,missed,percentage) VALUES('test','1','20','3','2','2','88.88');




-- create Researcher Table

DROP TABLE IF EXISTS `researcher`;
CREATE TABLE `researcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username',
  `first_name` varchar(20) NOT NULL  COMMENT 'first name',
  `last_name` varchar(20) NOT NULL  COMMENT 'last name',
  `email` varchar(50) NOT NULL COMMENT 'email address',
  `researcher_id` varchar(50) NOT NULL COMMENT ' the special id number for researcher',
  `access_token` varchar(100)  NOT NULL COMMENT 'access token for dropbox',
  `create_time` DATETIME COMMENT 'create time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `researcher_id` (`researcher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='researcher table';

-- insert one researcher for test
INSERT INTO researcher(username,first_name,last_name,email,researcher_id,access_token) VALUES('alcoholproject2019','alcohol','admin','alcoholproject2019@gmail.com','12345','T2ZW21NqqjAAAAAAAAAAC17knFz4aTzALMQpuQ65jOMsFy5S2qRD4asHDh3jIcyr');


-- create administrator Table

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username',
  `password` varchar(50) NOT NULL COMMENT 'password，here only admin can access the system,so other researchers do not have it',
  `email` varchar(50) NOT NULL COMMENT 'email address',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='admin table';

-- insert one researcher for test
INSERT INTO administrator(username,password,email) VALUES('alcoholproject2019','727928dc759026565dd81c63376ace03','alcoholproject2019@gmail.com');


-- create Setting Table

DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `show_result` varchar(20) NOT NULL COMMENT 'if show result to the user, 0-show,1-hide',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='setting table';

-- insert one researcher for test
INSERT INTO setting(show_result) VALUES(0);






