package com.db;

import com.model.User;
import com.sql.dao.BaseRowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.HashMap;
import java.util.Map;

import static com.db.UserDaoRowMapper.UserColumnType.NAME;
import static com.db.UserDaoRowMapper.UserColumnType.EMAIL;
import static com.db.UserDaoRowMapper.UserColumnType.PASSWORD;
import static com.db.UserDaoRowMapper.UserColumnType.STATUS;
import static com.db.UserDaoRowMapper.UserColumnType.ROLE;
import static com.db.UserDaoRowMapper.UserColumnType.IMAGE;

import static com.sql.dao.BaseRowMapper.BaseColumnType.*;

public class UserDaoRowMapper extends BaseRowMapper<User> {

    public enum UserColumnType {
       NAME, EMAIL, PASSWORD, STATUS, ROLE, IMAGE;
        private String columnName;
        UserColumnType() {
            columnName = name().toLowerCase();
        }
        public String getColumnName() { return columnName; }
    }
    /**
     * user object to a row in the data source
     * maps user column names to user_fields
     * @param user
     * @return a hash map<ColumnName,ColumnValue> for the given user
     */
    @Override
    public Map<String, Object> mapObject(@NotNull User user) {
        Map <String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(), user.getId());
        map.put(CREATED_BY.getColumnName(), user.getCreated_by());
        map.put(UPDATED_BY.getColumnName(), user.getUpdated_by());
        map.put(NAME.getColumnName(),user.getName());
        map.put(EMAIL.getColumnName(),user.getEmail());
        map.put(PASSWORD.getColumnName(),user.getPassword());
        map.put(STATUS.getColumnName(),user.getStatus());
        map.put(ROLE.getColumnName(),user.getRole());
        map.put(IMAGE.getColumnName(),user.getImage());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(user.getCreated_date()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(user.getUpdated_date()));
        return map;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user  = new User();
        user.setId(rs.getLong(ID.getColumnName()));
        user.setCreated_by(rs.getLong(CREATED_BY.getColumnName()));
        user.setUpdated_by(rs.getLong(UPDATED_BY.getColumnName()));
        user.setName(rs.getString(NAME.getColumnName()));
        user.setEmail(rs.getString(EMAIL.getColumnName()));
        user.setPassword(rs.getString(PASSWORD.getColumnName()));
        user.setStatus(rs.getString(STATUS.getColumnName()));
        user.setRole(rs.getString(ROLE.getColumnName()));
        user.setImage(rs.getString(IMAGE.getColumnName()));
        user.setCreated_date(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        user.setUpdated_date(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return user;
    }
}
