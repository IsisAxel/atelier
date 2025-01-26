-- Active: 1732906693261@@127.0.0.1@5432@atelier
INSERT INTO brands (name) VALUES 
('MSI'),
('ASUS'),
('Razer Blade');

INSERT INTO component_type (type) VALUES 
('Processeur'),
('RAM'),
('Disque dur'),
('Carte mere'),
('Carte graphique'),
('Ecran');

INSERT INTO computer_type (type) VALUES 
('Portable'),
('Bureau');

INSERT INTO status (status) VALUES ('normal'), ('anormal');


INSERT INTO computer_usage (usage_name) 
VALUES ('Gaming'), ('Bureautique'), ('General');

INSERT INTO role_technicien(name,commission) VALUES ('Role 1' , 5) , ('Role 2',10);

INSERT INTO technicien (name,id_role_technicien,id_genre) VALUES 
('Jean Dupont',1,1),
('Alice Martin',2,1),
('Mohamed Ali',1,2),
('Sophie Bernard',2,2),
('Thomas Durand',1,1);

INSERT INTO genre(genre) VALUES 
('homme'),
('femme');


