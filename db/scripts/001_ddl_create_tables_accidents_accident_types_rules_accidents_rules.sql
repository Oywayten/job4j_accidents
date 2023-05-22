CREATE TABLE accident_types (
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE accidents (
    id               SERIAL PRIMARY KEY,
    name             TEXT,
    text             TEXT,
    address          TEXT,
    accident_type_id INT REFERENCES accident_types (id) NOT NULL
);

CREATE TABLE rules (
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE accidents_rules (
    id          SERIAL PRIMARY KEY,
    accident_id INT REFERENCES accidents (id) NOT NULL ,
    rule_id     INT REFERENCES rules (id) NOT NULL
);

