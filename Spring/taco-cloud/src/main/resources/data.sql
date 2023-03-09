delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type) values ('FLTO', 'flour', 'WRAP');
insert into Ingredient (id, name, type) values ('COTO', 'corn', 'WRAP');
insert into Ingredient (id, name, type) values ('GRBF', 'ground beef', 'PROTEIN');
insert into Ingredient (id, name, type) values ('CARN', 'piece of meat', 'PROTEIN');
insert into Ingredient (id, name, type) values ('TMTO', 'tomato cubes', 'VEGGIES');
insert into Ingredient (id, name, type) values ('LETC', 'lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) values ('CHED', 'cheddar', 'CHEESE');
insert into Ingredient (id, name, type) values ('JACK', 'monterey Jack', 'CHEESE');
insert into Ingredient (id, name, type) values ('SLSA', 'spice tomato sauce', 'SAUCE');
insert into Ingredient (id, name, type) values ('SRCR', 'cream', 'SAUCE');