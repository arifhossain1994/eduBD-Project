--STATEMENT createUser
INSERT INTO users (
    name,
    email,
    password,
    status,
    image,
    created_date,
    updated_date,
    created_by,
    updated_by,
    role
) VALUES (
    :name,
    :email ,
    :password,
    :status ,
    :image ,
    :created_date,
    :updated_date,
    :created_by,
    :updated_by,
    :role
 );

--STATEMENT readUser
SELECT * FROM users WHERE id = :id;

--STATEMENT readUserByEmail
SELECT * FROM users WHERE email = :email;

--STATEMENT deleteUser
DELETE FROM users WHERE id = :id;

--STATEMENT updateUser
UPDATE users SET
 name = :name,
 email = :email,
 password = :password,
 status = :status,
 image = :image,
 updated_date = :updated_date,
 created_by = :created_by,
 updated_by = :updated_by,
 role = :role
WHERE
    id = :id;

--STATEMENT getAllUsers
SELECT * from users;


--INSERT INTO users VALUES ( 1,'Arif Hossain','admin@uwm.edu','admin123','active','admin',NOW(),NOW(),'admin',5000,6000);
