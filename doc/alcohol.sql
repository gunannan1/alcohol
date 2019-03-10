-- drop and create database
drop DATABASE IF EXISTS `alcohol`;
CREATE DATABASE alcohol character set utf8;

use alcohol;

-- create user table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `username` varchar(20) NOT NULL COMMENT 'username',
  `password` varchar(20) NOT NULL COMMENT 'password',
  `name` varchar(20) NOT NULL  COMMENT 'name',
  `email` varchar(50) NOT NULL COMMENT 'email address',
  `sex` INT NOT NULL COMMENT ' 0-male，1-female',
  `age` INT NOT NULL  COMMENT 'age',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='user table';

-- insert one user for test
INSERT INTO user(username,password,name,email,sex,age) VALUES('admin','admin','admin','admin@gmail.com',0,100);





-- create ddt_questions table
DROP TABLE IF EXISTS `ddt_questions`;
CREATE TABLE `ddt_questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `one_day_money` INT NOT NULL COMMENT 'the choice of money for one day',
  `more_days_money` INT NOT NULL  COMMENT 'the choice of money for more days',
  `days` INT NOT NULL  COMMENT 'exact days for more days choice',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ddt_questions table';


-- insert the 27 questions to ddt_questions table
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(54,55,117);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(55,75,61);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(19,25,53);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(31,85,7);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(14,25,19);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(47,50,160);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(15,35,13);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(25,60,14);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(78,80,162);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(40,55,62);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(11,30,7);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(67,75,119);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(34,35,186);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(27,50,21);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(69,85,91);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(49,60,89);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(80,85,157);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(24,35,29);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(33,80,14);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(28,30,179);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(34,50,30);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(25,30,80);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(41,75,20);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(54,60,111);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(54,80,30);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(22,25,136);
INSERT INTO ddt_questions(one_day_money,more_days_money,days) VALUES(20,55,7);



-- create ddt_records table
DROP TABLE IF EXISTS `ddt_answer_records`;
CREATE TABLE `ddt_answer_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL COMMENT 'the id of who answers these questions',
  `question_1`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_2`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_3`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_4`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_5`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_6`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_7`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_8`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_9`  INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_10` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_11` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_12` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_13` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_14` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_15` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_16` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_17` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_18` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_19` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_20` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_21` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_22` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_23` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_24` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_25` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_26` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',
  `question_27` INT  COMMENT ' answer for the question,0/null -no answer,1 - choose 1 day, 2 - choose more days',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ddt_answer_records table';

-- insert one raw to test

INSERT INTO ddt_answer_records(user_id,question_1) VALUES(1,2);



















