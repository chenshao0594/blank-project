-- ----------------------------------------------------------
-- Users
-- ----------------------------------------------------------

insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired, created_by,created_date,last_modified_by,last_modified_date) values (1, 'admin@aol.com', 'admin', '$2a$04$xaSaTL6AF6nIiD32tDlsuuSvegdQEm3oKiBWi6ApCTSP9nFNAj/Qy', 'Admin', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired,created_by,created_date,last_modified_by,last_modified_date) values (2, 'user@aol.com', 'user', '$2a$04$4cUGAGKkm2AxnMJIRZ5dG.BrA5XtYXNm9Wt2VlyFC/xxTI0c3NbKu', 'User', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired,created_by,created_date,last_modified_by,last_modified_date) values (3, 'keith@aol.com', 'keith', '$2a$04$nSLp/Xcx7M3dzWKBS8FHNuzvc5/PjtMRyIbiljXtIr3aGB.I5OMAe', 'Keith', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired,created_by,created_date,last_modified_by,last_modified_date) values (4, 'erwin@aol.com', 'erwin', '$2a$04$qYCNZtXalJNrYFqPPzAxFehY3/XCEG7cF0outlr6jFwtHBTBMgpua', 'Erwin', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired,created_by,created_date,last_modified_by,last_modified_date) values (5, 'jeremy@aol.com', 'jeremy', '$2a$04$Fiy/tKQ6j5Easl1YeN1PfuIr6YyCham5Ezm/992Wq3y2rhvyVnAfS', 'Jeremy', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into users (id, email, username, password, name, enabled, account_expired, account_locked, credentials_expired,created_by,created_date,last_modified_by,last_modified_date) values (6, 'scott@aol.com', 'scott', '$2a$04$BdckjEcXl9cjbG.1WHoVluNNtlxKhUcykpS4ts.TVymm9pb/ZZmFu', 'Scott', true, false, false, false,'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);



insert into user_profiles (id,address, city,created_by,created_date,last_modified_by,last_modified_date) values (1,'scott@aol.com', 'scott','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into user_profiles (id,address, city,created_by,created_date,last_modified_by,last_modified_date) values (2,'pudong ', 'Shanghai','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);

-- ----------------------------------------------------------
-- Authorities
-- ----------------------------------------------------------

insert into authorities (id, authority,created_by,created_date,last_modified_by,last_modified_date) values (1, 'ROLE_ADMIN','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into authorities (id, authority,created_by,created_date,last_modified_by,last_modified_date) values (2, 'ROLE_USER','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);

-- ----------------------------------------------------------
-- User_Authorities
-- ----------------------------------------------------------

insert into user_authorities (user_id, authority_id) values (1, 2);
insert into user_authorities (user_id, authority_id) values (1, 1);
insert into user_authorities (user_id, authority_id) values (2, 2);
insert into user_authorities (user_id, authority_id) values (3, 2);
insert into user_authorities (user_id, authority_id) values (4, 2);
insert into user_authorities (user_id, authority_id) values (5, 2);
insert into user_authorities (user_id, authority_id) values (6, 2);

-- ----------------------------------------------------------
-- Blank Project
-- ----------------------------------------------------------
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487','system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
insert into owners(id, first_name, last_name, address, city, telephone,created_by,created_date,last_modified_by,last_modified_date) values (11, 'Kim', 'Picadilli', '2715 Amanda Way', 'Kissimmee', '7072883864', 'system', CURRENT_TIMESTAMP, 'system',  CURRENT_TIMESTAMP);
