package com.db;

import com.model.Teacher;
import com.sql.dao.BaseRowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.sql.dao.BaseRowMapper.BaseColumnType.*;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.FIRST_NAME;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.LAST_NAME;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.EMAIL;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.PASSWORD;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.STATUS;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.ROLE;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.IMAGE;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.SCHOOL_ID;
import static com.db.TeacherDaoRowMapper.TeacherColumnType.TEACHER_PHONE;

public class TeacherDaoRowMapper  extends BaseRowMapper<Teacher> {

    public enum TeacherColumnType {
        SCHOOL_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, TEACHER_PHONE, STATUS, ROLE, IMAGE;
        private String columnName;
        TeacherColumnType() {
            columnName = name().toLowerCase();
        }
        public String getColumnName() { return columnName; }
    }

    @Override
    public Map<String, Object> mapObject(@NotNull Teacher teacher) {
        Map <String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(), teacher.getId());
        map.put(SCHOOL_ID.getColumnName(),teacher.getSchoolId());
        map.put(FIRST_NAME.getColumnName(),teacher.getFirstName());
        map.put(LAST_NAME.getColumnName(),teacher.getLastName());
        map.put(EMAIL.getColumnName(),teacher.getEmail());
        map.put(PASSWORD.getColumnName(),teacher.getPassword());
        map.put(TEACHER_PHONE.getColumnName(),teacher.getTeacherPhone());
        map.put(STATUS.getColumnName(),teacher.getStatus());
        map.put(ROLE.getColumnName(),teacher.getRole());
        map.put(IMAGE.getColumnName(),teacher.getImage());
        map.put(CREATED_BY.getColumnName(), teacher.getCreatedBy());
        map.put(UPDATED_BY.getColumnName(), teacher.getUpdatedBy());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(teacher.getCreatedDate()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(teacher.getUpdatedDate()));
        return map;
    }

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher=new Teacher();
        teacher.setId(rs.getLong(ID.getColumnName()));
        teacher.setSchoolId(rs.getLong(SCHOOL_ID.getColumnName()));
        teacher.setFirstName(rs.getString(FIRST_NAME.getColumnName()));
        teacher.setLastName(rs.getString(LAST_NAME.getColumnName()));
        teacher.setEmail(rs.getString(EMAIL.getColumnName()));
        teacher.setPassword(rs.getString(PASSWORD.getColumnName()));
        teacher.setTeacherPhone(rs.getLong(TEACHER_PHONE.getColumnName()));
        teacher.setStatus(rs.getString(STATUS.getColumnName()));
        teacher.setRole(rs.getString(ROLE.getColumnName()));
        teacher.setImage(rs.getString(IMAGE.getColumnName()));
        teacher.setCreatedBy(rs.getLong(CREATED_BY.getColumnName()));
        teacher.setUpdatedBy(rs.getLong(UPDATED_BY.getColumnName()));
        teacher.setCreatedDate(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        teacher.setUpdatedDate(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return teacher;
    }
}
