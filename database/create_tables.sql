CREATE TABLE clients (
    id_client SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE brands (
    id_brand SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE component_type (
    id_component_type SERIAL PRIMARY KEY,
    type VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE computer_type (
    id_computer_type SERIAL PRIMARY KEY,
    type VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE components (
    id_component SERIAL PRIMARY KEY,
    id_type INT NOT NULL,
    id_type_computer INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    serial_number VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (id_type) REFERENCES component_type(id_component_type),
    FOREIGN KEY (id_type_computer) REFERENCES computer_type(id_computer_type)
);

CREATE TABLE Recommendation(
    id  SERIAL PRIMARY KEY,
    id_component INT NOT NULL,
    period timestamp ,
     FOREIGN KEY (id_component) REFERENCES components(id_component)
);


CREATE TABLE computer_usage (
    id_usage SERIAL PRIMARY KEY,
    usage_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE computers (
    id_computer SERIAL PRIMARY KEY,
    id_type_computer INT NOT NULL,
    id_usage INT NOT NULL,
    model VARCHAR(50) NOT NULL,
    id_client INT NOT NULL,
    id_brand INT NOT NULL,
    FOREIGN KEY (id_type_computer) REFERENCES computer_type(id_computer_type),
    FOREIGN KEY (id_usage) REFERENCES computer_usage(id_usage),
    FOREIGN KEY (id_client) REFERENCES clients(id_client),
    FOREIGN KEY (id_brand) REFERENCES brands(id_brand)
);


CREATE TABLE status (
    id_status SERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE computer_components (
    id_computer_component SERIAL PRIMARY KEY,
    id_computer INT NOT NULL,
    id_component INT NOT NULL,
    id_status INT NOT NULL,
    FOREIGN KEY (id_computer) REFERENCES computers(id_computer),
    FOREIGN KEY (id_component) REFERENCES components(id_component),
    FOREIGN KEY (id_status) REFERENCES status(id_status)
);

CREATE TABLE reparation_types (
    id_reparation_type SERIAL PRIMARY KEY,
    type VARCHAR(100) UNIQUE NOT NULL,
    id_component INT,
    duration INT NOT NULL,
    FOREIGN KEY (id_component) REFERENCES components(id_component)
);

CREATE TABLE reparation_type_prices (
    id_reparation_type_price SERIAL PRIMARY KEY,
    id_reparation_type INT NOT NULL,
    price NUMERIC NOT NULL CHECK (price > 0),
    effective_date TIMESTAMP NOT NULL,
    FOREIGN KEY (id_reparation_type) REFERENCES reparation_types(id_reparation_type)
);

CREATE TABLE role_technicien(
      id_role_technicien SERIAL PRIMARY KEY,
      name varchar(50),
      commission NUMERIC NOT NULL CHECK(commission >= 0)
);


CREATE TABLE  genre(
    id_genre SERIAL PRIMARY KEY,
    genre VARCHAR(50)
);

CREATE TABLE technicien(
      id_technicien SERIAL PRIMARY KEY,
      name varchar(50),
      id_genre int not null REFERENCES genre(id_genre),
      id_role_technicien int not null REFERENCES role_technicien(id_role_technicien)
);

CREATE TABLE reparation (
    id_reparation SERIAL PRIMARY KEY,
    id_technicien int not null,
    id_computer INT NOT NULL,
    start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP,
    total_amount NUMERIC NOT NULL CHECK (total_amount >= 0),
    FOREIGN KEY (id_computer) REFERENCES computers(id_computer),
    FOREIGN KEY (id_technicien) REFERENCES technicien(id_technicien)
);

CREATE TABLE return(
    id_return SERIAL PRIMARY KEY,
    return_date TIMESTAMP NOT NULL,
    id_reparation INT NOT NULL,
    FOREIGN KEY (id_reparation) REFERENCES reparation(id_reparation)
);

CREATE TABLE reparation_details (
    id_reparation_detail SERIAL PRIMARY KEY,
    id_reparation INT NOT NULL,
    id_reparation_type INT NOT NULL,
    price NUMERIC NOT NULL CHECK (price > 0),
    FOREIGN KEY (id_reparation) REFERENCES reparation(id_reparation),
    FOREIGN KEY (id_reparation_type) REFERENCES reparation_types(id_reparation_type)
);



CREATE TABLE achat (
    id_achat SERIAL PRIMARY KEY,
    id_component INT NOT NULL,
    quantity INT NOT NULL,
    unit_price NUMERIC NOT NULL CHECK (unit_price > 0),
    purchase_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_component) REFERENCES components(id_component)
);

CREATE TABLE stock_history (
    id_stock_history SERIAL PRIMARY KEY,
    id_component INT NOT NULL,
    change INT NOT NULL,
    change_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_component) REFERENCES components(id_component)
);

