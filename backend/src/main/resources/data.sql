----------------------------------------AUTHORITIES----------------------------------------
insert into authority_table (name) values ('admin');
insert into authority_table (name) values ('guest');

----------------------------------------USERS----------------------------------------
insert into user_table (email, password, first_name, last_name, enabled)
values ('admin@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'admin', 'admin', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('guest@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'guest', 'guest', true);
insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (2, 2);

