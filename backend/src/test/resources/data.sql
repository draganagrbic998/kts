----------------------------------------AUTHORITIES----------------------------------------
insert into authority_table (name) values ('authority_one');
insert into authority_table (name) values ('admin');
insert into authority_table (name) values ('guest');

----------------------------------------IMAGES----------------------------------------
insert into image_table (path) values ('image_one');

----------------------------------------USERS----------------------------------------
insert into user_table (email, password, first_name, last_name, enabled)
values ('email_one', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'first_name_one', 'last_name_one', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('email_two', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'first_name_two', 'last_name_two', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('email_three', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'first_name_three', 'last_name_three', true);
insert into user_table (email, password, first_name, last_name, enabled)
values ('asd', '$2a$10$aL2cRpbMvSsvTcIGxUoauO4RMefDmYtEEARsmKJpwJ7T585HfBsra', 'asd', 'asd', true);
insert into user_authority (user_id, authority_id) values (1, 3);
insert into user_authority (user_id, authority_id) values (2, 2);
insert into user_authority (user_id, authority_id) values (4, 3);

----------------------------------------ACCOUNT ACTIVATIONS----------------------------------------
insert into account_activation_table (user_id, code) values (1, 'code_one');

----------------------------------------CATEGORIES----------------------------------------
insert into category_table (name) values ('category_one');
insert into category_table (name) values ('category_two');
insert into category_table (name) values ('category_three');

----------------------------------------TYPES----------------------------------------
insert into type_table (name, category_id) values ('type_one', 1);
insert into type_table (name, category_id) values ('type_two', 2);
insert into type_table (name, category_id) values ('type_three', 3);

----------------------------------------CULTURAL OFFERS----------------------------------------
insert into cultural_offer_table (type_id, name, lat, lng, location) values (1, 'cultural_offer_one', 10, 10, 'location_one');
insert into cultural_offer_table (type_id, name, lat, lng, location) values (2, 'cultural_offer_two', 20, 20, 'location_two');
insert into cultural_offer_table (type_id, name, lat, lng, location) values (3, 'cultural_offer_three', 30, 30, 'location_three');

----------------------------------------NEWS----------------------------------------
insert into news_table (created_at, cultural_offer_id, text) values ('2020-09-12', 2, 'news_one');
insert into news_table (created_at, cultural_offer_id, text) values ('2020-09-14', 3, 'news_two');
insert into news_table (created_at, cultural_offer_id, text) values ('2020-09-10', 3, 'news_three');
insert into news_table (created_at, cultural_offer_id, text) values ('2020-09-16', 3, 'news_four');

----------------------------------------COMMENTS----------------------------------------
insert into comment_table (created_at, user_id, cultural_offer_id, rate, text) values ('2020-12-12', 1, 1, 3, 'comment_one');
insert into comment_table (created_at, user_id, cultural_offer_id, rate, text) values ('2020-12-14', 2, 1, 2, 'comment_two');
insert into comment_table (created_at, user_id, cultural_offer_id, rate, text) values ('2020-12-10', 2, 1, 4, 'comment_three');
insert into comment_table (created_at, user_id, cultural_offer_id, rate, text) values ('2020-12-11', 1, 2, 1, 'comment_four');

----------------------------------------USER FOLLOWINGS----------------------------------------
insert into user_following_table (user_id, cultural_offer_id) values (1, 1);
insert into user_following_table (user_id, cultural_offer_id) values (1, 2);
insert into user_following_table (user_id, cultural_offer_id) values (1, 3);
insert into user_following_table (user_id, cultural_offer_id) values (2, 1);
