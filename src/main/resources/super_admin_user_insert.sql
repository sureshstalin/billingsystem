insert into user(is_deleted,email_id,first_name,last_name,mobile_no,password,date_created) values
(0,'suresh@gmail.com','Suresh','Stalin','8383838383','123',curdate());

insert into employee(is_deleted,employee_code,full_name,user_id,date_created)
values(0,'EMP11111','Suresh Stalin',1,curdate());

insert into address (is_deleted,address1,city,country,state,user_id,date_created)
values (0,'test street','Chennai','India','TN',1,curdate());

insert into user_role values(1,4)