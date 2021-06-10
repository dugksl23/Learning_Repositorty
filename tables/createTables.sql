create table settings (
  `id` INT(10) NOT NULL AUTO_INCREMENT primary key,
  `createdDate` DATE NULL DEFAULT current_timestamp(),
	`updatedDate` DATE NULL DEFAULT NULL ON UPDATE current_timestamp()
  , memberName varchar(40)
  , password varchar(200)
  , role int(10),
  lastLoginDate Date NULL DEFAULT current_timestamp()

);