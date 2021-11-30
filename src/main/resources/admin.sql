insert into users(first_name, last_name, login, password)
VALUES ('admin', 'adminov', 'admin', '$2a$12$H8GA14kYsNmNntj2ubbu3e6dOfU.YX7P//2OioUAQuJ9oYY.quDpS');
insert into role(user_id, roles)
VALUES (1, 'ADMIN');
insert into users(first_name, last_name, login, password)
VALUES ('user', 'userov', 'user', '$2a$12$WiLaMqak6z40D6jngEVZb.BgEkFHsIv1eUWlYzsOfsECNeF.TSw1e');
insert into role(user_id, roles)
VALUES (2, 'USER');
