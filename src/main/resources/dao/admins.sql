--STATEMENT createAdmin
INSERT INTO admins (
    school_id,
    first_name,
    last_name,
    email,
    password,
    admin_phone,
    status,
    image,
    created_date,
    updated_date,
    created_by,
    updated_by,
    role
) VALUES (
             :school_id,
             :first_name,
             :last_name,
             :email ,
             :password,
             :admin_phone,
             :status ,
             :image ,
             :created_date,
             :updated_date,
             :created_by,
             :updated_by,
             :role
         );

--STATEMENT readAdmin
SELECT * FROM admins WHERE id = :id;

--STATEMENT readAdminByEmail
SELECT * FROM admins WHERE email = :email;

--STATEMENT deleteAdmin
UPDATE admins SET
                  status = :status,
                  updated_by =:updated_by,
                  updated_date=:updated_date
WHERE
      id = :id;

--STATEMENT updateAdmin
UPDATE admins SET
                 first_name = :first_name,
                 last_name = :last_name,
                 email = :email,
                 password = :password,
                 admin_phone = :admin_phone,
                 status = :status,
                 image = :image,
                 updated_date = :updated_date,
                 created_by = :created_by,
                 updated_by = :updated_by,
                 role = :role
WHERE
        id = :id;

--STATEMENT getAllAdmins
SELECT * from admins;


--INSERT INTO admins VALUES ( 1,'Arif Hossain','admin@uwm.edu','admin123','active','admin',NOW(),NOW(),'admin',5000,6000);
