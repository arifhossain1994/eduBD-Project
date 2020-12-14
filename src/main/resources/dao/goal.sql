--STATEMENT createGoal
INSERT INTO goals (
    user_id,
    name,
    description,
    current_value,
    total_value,
    status,
    created_date,
    created_by
) VALUES (
             :user_id,
             :name,
             :description,
             :current_value,
             :total_value,
             :status,
             :created_date,
             :created_by
         );

--STATEMENT readGoal
SELECT * FROM goals WHERE id = :id;

--STATEMENT readGoals
SELECT * FROM goals;

--STATEMENT readgoalsbyuserid
SELECT * FROM goals WHERE user_id = :user_id;

--STATEMENT deleteGoal
DELETE FROM goals WHERE id = :id;

--STATEMENT updateGoal
UPDATE goals SET
    name = :name,
    user_id = :user_id,
    description = :description,
    current_value = :current_value,
    total_value = :total_value,
    status = :status,
    updated_date = :updated_date,
    updated_by = :updated_by
WHERE
    id = :id;

