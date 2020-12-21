----------------------------------------IMAGES----------------------------------------
insert into image_table (path) values ('image_one');

----------------------------------------AUTHORITIES----------------------------------------
insert into authority_table (name) values ('authority_one');
insert into authority_table (name) values ('admin');
insert into authority_table (name) values ('guest');

----------------------------------------USERS----------------------------------------
insert into user_table (email, password, first_name, last_name, enabled)
values ('email_one@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'first_name_one', 'last_name_one', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('email_two@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'first_name_two', 'last_name_two', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('asd@gmail.com', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'asd', 'asd', true);
insert into user_authority (user_id, authority_id) values (1, 3);
insert into user_authority (user_id, authority_id) values (2, 2);
insert into user_authority (user_id, authority_id) values (3, 3);

----------------------------------------ACCOUNT ACTIVATIONS----------------------------------------
insert into account_activation_table (user_id, code) values (1, 'code_one');

----------------------------------------CATEGORIES----------------------------------------
insert into category_table (name) values ('category_one');
insert into category_table (name) values ('category_two');
insert into category_table (name) values ('category_three');

----------------------------------------TYPES----------------------------------------
insert into type_table (category_id, name) values (1, 'type_one');
insert into type_table (category_id, name) values (2, 'type_two');
insert into type_table (category_id, name) values (3, 'type_three');

----------------------------------------CULTURAL OFFERS----------------------------------------
insert into cultural_offer_table (type_id, name, location, lat, lng) values (1, 'cultural_offer_one', 'location_one', 10, 10);
insert into cultural_offer_table (type_id, name, location, lat, lng) values (2, 'cultural_offer_two', 'location_two', 20, 20);
insert into cultural_offer_table (type_id, name, location, lat, lng) values (3, 'cultural_offer_three', 'location_three', 30, 30);

----------------------------------------NEWS----------------------------------------
insert into news_table (cultural_offer_id, created_at, text) values (2, '2020-09-12', 'news_one');
insert into news_table (cultural_offer_id, created_at, text) values (3, '2020-09-14', 'news_two');
insert into news_table (cultural_offer_id, created_at, text) values (3, '2020-09-10', 'news_three');
insert into news_table (cultural_offer_id, created_at, text) values (3, '2020-09-16', 'news_four');

----------------------------------------COMMENTS----------------------------------------
insert into comment_table (user_id, cultural_offer_id, created_at, rate, text) values (1, 1, '2020-12-12', 3, 'comment_one');
insert into comment_table (user_id, cultural_offer_id, created_at, rate, text) values (2, 1, '2020-12-14', 2, 'comment_two');
insert into comment_table (user_id, cultural_offer_id, created_at, rate, text) values (2, 1, '2020-12-10', 4, 'comment_three');
insert into comment_table (user_id, cultural_offer_id, created_at, rate, text) values (1, 2, '2020-12-11', 1, 'comment_four');

----------------------------------------USER FOLLOWINGS----------------------------------------
insert into user_following_table (user_id, cultural_offer_id) values (1, 1);
insert into user_following_table (user_id, cultural_offer_id) values (1, 2);
insert into user_following_table (user_id, cultural_offer_id) values (1, 3);
insert into user_following_table (user_id, cultural_offer_id) values (2, 1);
