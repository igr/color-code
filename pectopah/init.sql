/* SQLEditor (Postgres)*/


CREATE TABLE restaurants
(
    id UUID NOT NULL UNIQUE ,
    name TEXT NOT NULL UNIQUE ,
    max_duration INTEGER NOT NULL DEFAULT 120,
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);

CREATE TABLE reservations
(
    id UUID NOT NULL UNIQUE ,
    restaurant_id UUID NOT NULL,
    people INTEGER NOT NULL,
    date DATE NOT NULL,
    time INTEGER NOT NULL,
    duration INTEGER NOT NULL,
    CONSTRAINT reservations_pkey PRIMARY KEY (id)
);

CREATE TABLE tables
(
    id UUID NOT NULL UNIQUE ,
    restaurant_id UUID NOT NULL,
    places INTEGER NOT NULL,
    CONSTRAINT tables_pkey PRIMARY KEY (id)
);

CREATE TABLE reserved_tables
(
    reservation_id UUID NOT NULL,
    table_id UUID NOT NULL
);

CREATE INDEX restaurants_name_idx ON restaurants(name);

CREATE INDEX reservations_restaurant_id_idx ON reservations(restaurant_id);

ALTER TABLE reservations ADD FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

CREATE INDEX reservations_date_idx ON reservations(date);

CREATE INDEX tables_restaurant_id_idx ON tables(restaurant_id);

ALTER TABLE tables ADD FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

ALTER TABLE reserved_tables ADD FOREIGN KEY (reservation_id) REFERENCES reservations (id);

ALTER TABLE reserved_tables ADD FOREIGN KEY (table_id) REFERENCES tables (id);
