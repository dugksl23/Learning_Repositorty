-- create table member (
--   `id` number NOT NULL AUTO_INCREMENT primary key,
--   createdDate DATE NOT NULL DEFAULT current_timestamp(),
--   updatedDate DATE NULL DEFAULT NULL ON UPDATE current_timestamp(), 
--   memberName varchar(40),
--   `password` varchar(200),
--   roles number.
--   lastLoginDate DATE NOT NULL DEFAULT current_timestamp()
-- );