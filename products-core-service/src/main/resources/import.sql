insert into catalogue (catalogue_id, catalogue_version, catalogue_name) values (1, 1, 'Electronice');
insert into catalogue (catalogue_id, catalogue_version, catalogue_name) values (2, 1, 'TV');
insert into product (product_id, product_version, product_name, product_description, product_tags) values (1, 1, 'Casio G Shock', 'Casion Exclusive G Shock', 'Wristwatches');
insert into product (product_id, product_version, product_name, product_description, product_tags) values (2, 1, 'Sony X00B2', 'Sony Bravia X Series', 'Home Theater');
insert into product_catalogue (product_id, catalogue_id) values (1, 1);
insert into product_catalogue (product_id, catalogue_id) values (2, 1);
insert into product_catalogue (product_id, catalogue_id) values (2, 2);