package com.db;

import com.model.Admin;
import com.sql.dao.BaseRowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.sql.dao.BaseRowMapper.BaseColumnType.*;
import static com.db.AdminDaoRowMapper.AdminColumnType.FIRST_NAME;
import static com.db.AdminDaoRowMapper.AdminColumnType.LAST_NAME;
import static com.db.AdminDaoRowMapper.AdminColumnType.EMAIL;
import static com.db.AdminDaoRowMapper.AdminColumnType.PASSWORD;
import static com.db.AdminDaoRowMapper.AdminColumnType.STATUS;
import static com.db.AdminDaoRowMapper.AdminColumnType.ROLE;
import static com.db.AdminDaoRowMapper.AdminColumnType.IMAGE;
import static com.db.AdminDaoRowMapper.AdminColumnType.SCHOOL_ID;
import static com.db.AdminDaoRowMapper.AdminColumnType.ADMIN_PHONE;

public class AdminDaoRowMapper extends BaseRowMapper<Admin>{


    public enum AdminColumnType {
        SCHOOL_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ADMIN_PHONE, STATUS, ROLE, IMAGE;
        private String columnName;
        AdminColumnType() {
            columnName = name().toLowerCase();
        }
        public String getColumnName() { return columnName; }
    }
    /**
     * user object to a row in the data source
     * maps user column names to user_fields
     * @param admin
     * @return a hash map<ColumnName,ColumnValue> for the given user
     */
    @Override
    public Map<String, Object> mapObject(@NotNull Admin admin) {
        Map <String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(), admin.getId());
        map.put(SCHOOL_ID.getColumnName(),admin.getSchool_id());
        map.put(FIRST_NAME.getColumnName(),admin.getFirst_name());
        map.put(LAST_NAME.getColumnName(),admin.getLast_name());
        map.put(EMAIL.getColumnName(),admin.getEmail());
        map.put(PASSWORD.getColumnName(),admin.getPassword());
        map.put(ADMIN_PHONE.getColumnName(),admin.getAdmin_phone());
        map.put(STATUS.getColumnName(),admin.getStatus());
        map.put(ROLE.getColumnName(),admin.getRole());
        map.put(IMAGE.getColumnName(),admin.getImage());
        map.put(CREATED_BY.getColumnName(), admin.getCreated_by());
        map.put(UPDATED_BY.getColumnName(), admin.getUpdated_by());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(admin.getCreated_date()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(admin.getUpdated_date()));
        return map;
    }

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin  = new Admin();
        admin.setId(rs.getLong(ID.getColumnName()));
        admin.setSchool_id(rs.getLong(SCHOOL_ID.getColumnName()));
        admin.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        admin.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        admin.setEmail(rs.getString(EMAIL.getColumnName()));
        admin.setPassword(rs.getString(PASSWORD.getColumnName()));
        admin.setAdmin_phone(rs.getLong(ADMIN_PHONE.getColumnName()));
        admin.setStatus(rs.getString(STATUS.getColumnName()));
        admin.setRole(rs.getString(ROLE.getColumnName()));
        admin.setImage(rs.getString(IMAGE.getColumnName()));
        admin.setCreated_by(rs.getLong(CREATED_BY.getColumnName()));
        admin.setUpdated_by(rs.getLong(UPDATED_BY.getColumnName()));
        admin.setCreated_date(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        admin.setUpdated_date(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return admin;
    }
}

