CREATE TABLE Category(
                         id INT IDENTITY(1,1),
                         name VARCHAR(200) NOT NULL,
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