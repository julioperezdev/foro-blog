CREATE TABLE Comment(
                        id INT IDENTITY(1,1),
                        description VARCHAR(2000) NOT NULL,
                        dates TIMESTAMP NOT NULL,
                        idForo INT NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (idForo) REFERENCES Foro(id)
);