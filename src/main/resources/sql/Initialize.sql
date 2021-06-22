CREATE DATABASE foroblog;

CREATE TABLE Category(
                         id INT IDENTITY(1,1),
                         name VARCHAR(200) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE Foro(
                     id INT IDENTITY(1,1),
                     name VARCHAR(200) NOT NULL,
                     dates TIMESTAMP NOT NULL,
                     idCategory INT NOT NULL,
                     PRIMARY KEY (id),
                     FOREIGN KEY (idCategory) REFERENCES Category(id)
);

CREATE TABLE Comment(
                        id INT IDENTITY(1,1),
                        description VARCHAR(2000) NOT NULL,
                        dates TIMESTAMP NOT NULL,
                        idForo INT NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (idForo) REFERENCES Foro(id)
);

CREATE TABLE Blog(
                     id INT IDENTITY(1,1),
                     name VARCHAR(200) NOT NULL,
                     dates DATETIME NOT NULL,
                     description VARCHAR(3000) NOT NULL,
                     PRIMARY KEY (id)
);


CREATE PROCEDURE saveCategory
@Name VARCHAR(200)
AS
BEGIN
    INSERT INTO Category (name) VALUES (@Name);
END
BEGIN
    SELECT * FROM Category WHERE name = @Name;
end
GO;

EXEC saveCategory @Name= 'Tech'

