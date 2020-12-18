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
        map.put(SCHOOL_ID.getColumnName(),admin.getSchoolId());
        map.put(FIRST_NAME.getColumnName(),admin.getFirstName());
        map.put(LAST_NAME.getColumnName(),admin.getLastName());
        map.put(EMAIL.getColumnName(),admin.getEmail());
        map.put(PASSWORD.getColumnName(),admin.getPassword());
        map.put(ADMIN_PHONE.getColumnName(),admin.getAdminPhone());
        map.put(STATUS.getColumnName(),admin.getStatus());
        map.put(ROLE.getColumnName(),admin.getRole());
        map.put(IMAGE.getColumnName(),admin.getImage());
        map.put(CREATED_BY.getColumnName(), admin.getCreatedBy());
        map.put(UPDATED_BY.getColumnName(), admin.getUpdatedBy());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(admin.getCreatedDate()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(admin.getUpdatedDate()));
        return map;
    }

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin  = new Admin();
        admin.setId(rs.getLong(ID.getColumnName()));
        admin.setSchoolId(rs.getLong(SCHOOL_ID.getColumnName()));
        admin.setFirstName(rs.getString(FIRST_NAME.getColumnName()));
        admin.setLastName(rs.getString(LAST_NAME.getColumnName()));
        admin.setEmail(rs.getString(EMAIL.getColumnName()));
        admin.setPassword(rs.getString(PASSWORD.getColumnName()));
        admin.setAdminPhone(rs.getLong(ADMIN_PHONE.getColumnName()));
        admin.setStatus(rs.getString(STATUS.getColumnName()));
        admin.setRole(rs.getString(ROLE.getColumnName()));
        admin.setImage(rs.getString(IMAGE.getColumnName()));
        admin.setCreatedBy(rs.getLong(CREATED_BY.getColumnName()));
        admin.setUpdatedBy(rs.getLong(UPDATED_BY.getColumnName()));
        admin.setCreatedDate(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        admin.setUpdatedDate(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return admin;
    }
}

