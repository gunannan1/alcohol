last version

-- create ddt_questions table
DROP TABLE IF EXISTS `ddt_questions`;
CREATE TABLE `ddt_questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `one_day_choice` varchar(40) NOT NULL COMMENT 'the question of choice for one day',
  `more_days_choice` varchar(40) NOT NULL  COMMENT 'the question of choices for more days',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ddt_questions table';


-- insert the 27 questions to ddt_questions table
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$54 today','$55 in 117 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$55 today','$75 in 61 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$19 today','$25 in 33 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$31 today','$85 in 7 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$14 today','$25 in 19 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$47 today','$50 in 160 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$15 today','$35 in 13 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$25 today','$60 in 14 days');
INSERT INTO ddt_questions(one_day_choice,more_days_choice) VALUES('$78 today','$80 in 162 days');


----------------------------------------------------------------------------------------------