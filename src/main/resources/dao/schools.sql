--STATEMENT createSchool
INSERT INTO schools (
    school_name,
    school_email,
    school_street,
    school_house,
    school_zip,
    school_city,
    school_state,
    school_country,
    school_phone,
    status,
    image,
    created_date,
    updated_date,
    created_by,
    updated_by
) VALUES (
             :school_name,
             :school_email,
             :school_street,
             :school_house,
             :school_zip,
             :school_city,
             :school_state,
             :school_country,
             :school_phone,
             :status,
             :image,
             :created_date,
             :updated_date,
             :created_by,
             :updated_by
         );

--STATEMENT updateSchool
UPDATE schools SET
                   school_name=:school_name,
                   school_email=:school_email,
                   school_street=:school_street,
                   school_house=:school_house,
                   school_zip=:school_zip,
                   school_city=:school_city,
                   school_state=:school_state,
                   school_country=:school_country,
                   school_phone = :school_phone,
                   image=:image,
                   updated_date=:updated_date,
                   updated_by=:updated_by
WHERE id=:id;

--STATEMENT readAllSchools
SELECT * FROM schools;

--STATEMENT readSchool
SELECT * FROM schools WHERE id = :id;

--STATEMENT readSchoolByEmail
SELECT * FROM schools WHERE school_email = :school_email;

--STATEMENT deleteSchool
UPDATE schools SET
                   status = :status,
                   updated_by = :updated_by,
                   updated_date = :updated_date
WHERE
        school_email = :school_email;

