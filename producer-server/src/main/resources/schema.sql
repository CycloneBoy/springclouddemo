drop table  if exists t_user;
create table t_user(
  id bigint not null auto_increment primary key ,
  username varchar(40),
  name varchar(20),
  age int(3) ,
  balance decimal(10,2)
) engine=InnoDB  DEFAULT CHARSET=utf8 comment '用户表';