DROP TABLE IF EXISTS users, pets;

CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256),
    age INT,
    pet_id BIGINT
--    FOREIGN KEY (pet_id) REFERENCES pets (id)
);


CREATE TABLE pets(
     id  BIGINT PRIMARY KEY AUTO_INCREMENT,
     breed VARCHAR(256)
);


ALTER TABLE users ADD FOREIGN KEY (pet_id) REFERENCES pets (id);