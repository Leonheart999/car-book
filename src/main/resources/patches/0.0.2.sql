CREATE SEQUENCE cars_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE cars (
                      id BIGINT NOT NULL DEFAULT nextval('cars_id_seq') PRIMARY KEY,
                      state_number VARCHAR(255) NOT NULL UNIQUE,
                      win_code VARCHAR(255) NOT NULL UNIQUE,
                      color VARCHAR(255),
                      model VARCHAR(255),
                      brand VARCHAR(255),
                      active BOOLEAN,
                      user_id BIGINT NOT NULL,
                      CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

-- Indexes for optimization
CREATE INDEX idx_cars_state_number ON cars(state_number);
CREATE INDEX idx_cars_win_code ON cars(win_code);
CREATE INDEX idx_cars_user_id ON cars(user_id);

CREATE SEQUENCE car_provided_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE car_provided_services (
                                       id BIGINT NOT NULL DEFAULT nextval('car_provided_services_id_seq') PRIMARY KEY,
                                       provided_service VARCHAR(255),
                                       date DATE,
                                       price NUMERIC(19, 2),
                                       car_id BIGINT,
                                       CONSTRAINT fk_car_id FOREIGN KEY(car_id) REFERENCES cars(id)
);

-- Indexes for optimization
CREATE INDEX idx_car_provided_services_car_id ON car_provided_services(car_id);
CREATE INDEX idx_car_provided_services_date ON car_provided_services(date);