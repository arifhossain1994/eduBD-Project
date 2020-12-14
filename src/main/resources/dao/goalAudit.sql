--STATEMENT createGoalAudit
INSERT INTO goal_audit (
    id,
    goal_id,
    user_id,
    name,
    description,
    current_value,
    total_value,
    status,
    created_date,
    created_by,
    updated_date,
    updated_by
) VALUES (
             :id,
             :goal_id,
             :user_id,
             :name,
             :description,
             :current_value,
             :total_value,
             :status,
             :created_date,
             :created_by,
             :updated_by,
             :updated_date,
         );

--STATEMENT readGoalAuditID
SELECT * FROM goal_audit WHERE id = :id;

--STATEMENT readGoalAuditAll
SELECT * FROM goal_audit;

--STATEMENT readGoalAuditByUserId
SELECT * FROM goal_audit WHERE user_id = :user_id;

--STATEMENT deleteGoalAudit
DELETE FROM goal_audit WHERE id = :id;

--STATEMENT updateGoalAudit
UPDATE goal_audit SET
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

