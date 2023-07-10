
INSERT INTO coffee(nombre,precio,medida,img,state) VALUES('Negro',100.70,200,'/img/coffee/CafeNegro.jpg',1);
INSERT INTO coffee(nombre,precio,medida,img,state) VALUES('Capuccino',120.50,250,'/img/coffee/Capuccino.jpg',1);
INSERT INTO coffee(nombre,precio,medida,img,state) VALUES('Espresso',95,150,'/img/coffee/Espresso.jpg',1);
INSERT INTO coffee(nombre,precio,medida,img,state) VALUES('Latte',120.50,100,'/img/coffee/Latte.jpg',1);
INSERT INTO coffee(nombre,precio,medida,img,state) VALUES('Ristretto',320.50,200,'/img/coffee/Latte.jpg',0);


INSERT INTO sweets(nombre,precio,peso,img,state) VALUES('Cheesecake',450.50,200,'/img/sweets/Cheesecake.jpg',1);
INSERT INTO sweets(nombre,precio,peso,img,state) VALUES('CarrotCake',500,250,'/img/sweets/CarrotCake.jpg',1);
INSERT INTO sweets(nombre,precio,peso,img,state) VALUES('Bownie',125.15,120,'/img/sweets/Brownie.jpg',1);
INSERT INTO sweets(nombre,precio,peso,img,state) VALUES('OreoCake',350,235,'/img/sweets/OreoCake.jpg',1);
INSERT INTO sweets(nombre,precio,peso,img,state) VALUES('Crumble',400,235,'/img/sweets/OreoCake.jpg',0);


INSERT INTO users (username,password) VALUES('Juan','juan123');
INSERT INTO users (username,password) VALUES('potter','potter123');

/* Populate tabla  autorities*/
INSERT INTO autorities (name) VALUES('ROLE_ADMIN');
INSERT INTO autorities (name) VALUES('ROLE_USER');
INSERT INTO autorities (name) VALUES('READ');
INSERT INTO autorities (name) VALUES('WRITE');

/* Populate tabla  user_authorirty*/
INSERT INTO user_authority (authority_id,user_id) VALUES (4,1);
INSERT INTO user_authority (authority_id,user_id) VALUES (1,1);
INSERT INTO user_authority (authority_id,user_id) VALUES (2,2);

/* Populate tabla purchase*/
INSERT INTO purchase(numero_mesa,precio_total,fecha) VALUES (4,320.85,'2023-07-03 19:10:50');
INSERT INTO purchase(numero_mesa,precio_total,fecha) VALUES (1,545.50,'2023-07-03 19:10:50');
INSERT INTO purchase(numero_mesa,precio_total,fecha) VALUES (3,716,'2023-07-03 19:10:50');
INSERT INTO purchase(numero_mesa,precio_total,fecha) VALUES (5,721.20,'2023-07-03 19:10:50');
INSERT INTO purchase(numero_mesa,precio_total,fecha) VALUES (7,950.80,'2023-07-03 19:10:50');

/* Populate tabla purchase_sweets*/
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (1,3);
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (2,1);
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (3,3);
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (3,4);
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (4,2);
INSERT INTO purchase_sweets(purchase_id,sweets_id) VALUES (5,4);


/* Populate tabla purchase_coffees*/
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(1,1)
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(2,1);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(3,2);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(2,3);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(4,3);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(1,4);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(2,4);
INSERT INTO purchase_coffees(coffees_id,purchase_id) VALUES(3,5);




