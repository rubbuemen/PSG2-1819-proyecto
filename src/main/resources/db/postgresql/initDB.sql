CREATE TABLE IF NOT EXISTS vets (
  id SERIAL,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  CONSTRAINT pk_vets PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_vets_last_name ON vets (last_name);

ALTER SEQUENCE vets_id_seq RESTART WITH 100;


CREATE TABLE IF NOT EXISTS specialties (
  id SERIAL,
  name VARCHAR(80),
  CONSTRAINT pk_specialties PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_specialties_name ON specialties (name);

ALTER SEQUENCE specialties_id_seq RESTART WITH 100;


CREATE TABLE IF NOT EXISTS vet_specialties (
  vet_id INT NOT NULL,
  specialty_id INT NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  CONSTRAINT unique_ids UNIQUE (vet_id,specialty_id)
);



CREATE TABLE IF NOT EXISTS types (
  id SERIAL,
  name VARCHAR(80),
  CONSTRAINT pk_types PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_types_name ON types (name);

ALTER SEQUENCE types_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS owners (
  id SERIAL,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  CONSTRAINT pk_owners PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_owners_last_name ON owners (last_name);

ALTER SEQUENCE owners_id_seq RESTART WITH 100;


CREATE TABLE IF NOT EXISTS pets (
  id SERIAL,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT NOT NULL,
  owner_id INT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id),
  CONSTRAINT pk_pets PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_pets_name ON pets (name);

ALTER SEQUENCE pets_id_seq RESTART WITH 100;


CREATE TABLE IF NOT EXISTS visits (
  id SERIAL,
  pet_id INT NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id),
  CONSTRAINT pk_visits PRIMARY KEY (id)
);

ALTER SEQUENCE visits_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS hotels (
  id SERIAL,
  pet_id INT NOT NULL,
  details VARCHAR(255),
  start_date_book DATE,
  end_date_book DATE,
  FOREIGN KEY (pet_id) REFERENCES pets(id),
  CONSTRAINT pk_hotels PRIMARY KEY (id)
);

ALTER SEQUENCE hotels_id_seq RESTART WITH 100;

CREATE TABLE IF NOT EXISTS causes (
  id SERIAL,
  name VARCHAR(30),
  description VARCHAR(255),
  budged_target DOUBLE NOT NULL,
  organization VARCHAR(30),
  is_closed BOOLEAN,
  CONSTRAINT pk_causes PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS donations (
  id SERIAL,
  client VARCHAR(30),
  date_of_donation DATE,
  amount DOUBLE NOT NULL,
  cause_id INT NOT NULL,
  FOREIGN KEY (cause_id) REFERENCES donations(id),
  CONSTRAINT pk_donations PRIMARY KEY (id)
);
