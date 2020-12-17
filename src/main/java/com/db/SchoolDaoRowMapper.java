package com.db;

import com.model.School;
import com.sql.dao.BaseRowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import static com.sql.dao.BaseRowMapper.BaseColumnType.*;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_NAME;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_EMAIL;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_STREET;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_HOUSE;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_ZIP;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_CITY;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_STATE;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_COUNTRY;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.STATUS;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.IMAGE;
import static com.db.SchoolDaoRowMapper.SchoolColumnType.SCHOOL_PHONE;

public class SchoolDaoRowMapper extends BaseRowMapper<School> {

    public enum SchoolColumnType {
        SCHOOL_NAME, SCHOOL_EMAIL, SCHOOL_STREET, SCHOOL_HOUSE,
        SCHOOL_ZIP, SCHOOL_CITY, SCHOOL_STATE, SCHOOL_COUNTRY, SCHOOL_PHONE,
        STATUS, IMAGE;
        private String columnName;
        SchoolColumnType() {
            columnName = name().toLowerCase();
        }
        public String getColumnName() { return columnName; }
    }

    @Override
    public Map<String, Object> mapObject(@NotNull School school) {
        Map <String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(), school.getId());
        map.put(SCHOOL_EMAIL.getColumnName(),school.getSchool_email());
        map.put(SCHOOL_NAME.getColumnName(),school.getSchool_name());
        map.put(SCHOOL_STREET.getColumnName(),school.getSchool_street());
        map.put(SCHOOL_HOUSE.getColumnName(),school.getSchool_house());
        map.put(SCHOOL_ZIP.getColumnName(),school.getSchool_zip());
        map.put(SCHOOL_CITY.getColumnName(),school.getSchool_city());
        map.put(SCHOOL_STATE.getColumnName(),school.getSchool_state());
        map.put(SCHOOL_COUNTRY.getColumnName(),school.getSchool_country());
        map.put(SCHOOL_PHONE.getColumnName(),school.getSchool_phone());
        map.put(STATUS.getColumnName(),school.getStatus());
        map.put(IMAGE.getColumnName(),school.getImage());
        map.put(CREATED_BY.getColumnName(), school.getCreated_by());
        map.put(UPDATED_BY.getColumnName(), school.getUpdated_by());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(school.getCreated_date()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(school.getUpdated_date()));
        return map;
    }

    @Override
    public School mapRow(ResultSet rs, int rowNum) throws SQLException {
        School school = new School();
        
        school.setId(rs.getLong(ID.getColumnName()));
        school.setSchool_name(rs.getString(SCHOOL_NAME.getColumnName()));
        school.setSchool_email(rs.getString(SCHOOL_EMAIL.getColumnName()));
        school.setSchool_street(rs.getString(SCHOOL_STREET.getColumnName()));
        school.setSchool_house(rs.getString(SCHOOL_HOUSE.getColumnName()));
        school.setSchool_zip(rs.getLong(SCHOOL_ZIP.getColumnName()));
        school.setSchool_city(rs.getString(SCHOOL_CITY.getColumnName()));
        school.setSchool_state(rs.getString(SCHOOL_STATE.getColumnName()));
        school.setSchool_country(rs.getString(SCHOOL_COUNTRY.getColumnName()));
        school.setSchool_phone(rs.getLong(SCHOOL_PHONE.getColumnName()));
        school.setStatus(rs.getString(STATUS.getColumnName()));
        school.setImage(rs.getString(IMAGE.getColumnName()));
        school.setCreated_by(rs.getLong(CREATED_BY.getColumnName()));
        school.setUpdated_by(rs.getLong(UPDATED_BY.getColumnName()));
        school.setCreated_date(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        school.setUpdated_date(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return school;
    }
}
