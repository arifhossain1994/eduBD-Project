--STATEMENT createTeacher
INSERT INTO teachers (
    school_id,
    first_name,
    last_name,
    email,
    password,
    teacher_phone,
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
             :teacher_phone,
             :status ,
             :image ,
             :created_date,
             :updated_date,
             :created_by,
             :updated_by,
             :role
         );

--STATEMENT readTeacher
SELECT * FROM teachers WHERE id = :id;

--STATEMENT readTeacherByEmail
SELECT * FROM teachers WHERE email = :email;

--STATEMENT readTeacherBySchoolId
SELECT * FROM teachers WHERE school_id= :school_id;

--STATEMENT deleteTeacher
UPDATE teachers SET
                  status = :status,
                  updated_by =:updated_by,
                  updated_date=:updated_date
WHERE
        id = :id;

--STATEMENT updateTeacher
UPDATE teachers SET
                  first_name = :first_name,
                  last_name = :last_name,
                  email = :email,
                  password = :password,
                  Teacher_phone = :Teacher_phone,
                  status = :status,
                  image = :image,
                  updated_date = :updated_date,
                  created_by = :created_by,
                  updated_by = :updated_by,
                  role = :role
WHERE
        id = :id;

--STATEMENT getAllTeachers
SELECT * from teachers;


--INSERT INTO teachers VALUES ( 1,'Arif Hossain','Teacher@uwm.edu','Teacher123','active','Teacher',NOW(),NOW(),'Teacher',5000,6000);
