CREATE TABLE Blog(
                     id INT IDENTITY(1,1),
                     name VARCHAR(200) NOT NULL,
                     dates DATETIME NOT NULL,
                     description VARCHAR(3000) NOT NULL,
                     PRIMARY KEY (id)
);