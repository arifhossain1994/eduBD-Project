--STATEMENT createUserAudit
INSERT INTO user_audit (
                    id,
                    user_id,
                    email,
                    password,
                    name,
                    status,
                    role,
                    image,
                    created_date,
                    created_by,
                    updated_date,
                    updated_by

) VALUES (
            :id,
            :user_id,
            :email,
            :password,
            :name,
            :status,
            :role,
            :image,
            :created_date,
            :created_by,
            :updated_date,
            :updated_by

);

--STATEMENT readAuditUser
SELECT * FROM user_audit WHERE id = :id;

--STATEMENT readAllUserAudit
SELECT * FROM user_audit;

--STATEMENT readAllByInactiveStatus
SELECT * FROM user_audit WHERE status = :status;

--STATEMENT readAuditByEmail
SELECT * FROM user_audit WHERE email = :email;

--STATEMENT readAllByActiveStatus
SELECT * FROM user_audit WHERE status = :status;


--STATEMENT updateAuditStatus
update user_audit set
    status = :status,
    updated_by = :updated_by
where
    id = :id;

--STATEMENT updateAuditUser
UPDATE user_audit SET
  email = :email,
  password = :password,
  name =:name,
  role = :role,
  image = :image,
  status =:status,
  updated_date = :updated_date,
  updated_by = :updated_by
WHERE
  id = :id;



