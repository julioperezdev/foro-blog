CREATE TABLE Foro(
                     id INT IDENTITY(1,1),
                     name VARCHAR(200) NOT NULL,
                     dates TIMESTAMP NOT NULL,
                     idCategory INT NOT NULL,
                     PRIMARY KEY (id),
                     FOREIGN KEY (idCategory) REFERENCES Category(id)
);