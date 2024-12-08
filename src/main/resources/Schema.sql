CREATE TABLE IF NOT EXISTS pet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL,
    breed VARCHAR(250) NOT NULL,
    age INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS household (
    id INT AUTO_INCREMENT PRIMARY KEY,
    eircode VARCHAR(250) NOT NULL,
    number_of_occupants INT NOT NULL,
    max_number_of_occupants INT NOT NULL,
    owner_occupied BOOLEAN NOT NULL
    );