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
        map.put(SCHOOL_EMAIL.getColumnName(),school.getSchoolEmail());
        map.put(SCHOOL_NAME.getColumnName(),school.getSchoolName());
        map.put(SCHOOL_STREET.getColumnName(),school.getSchoolStreet());
        map.put(SCHOOL_HOUSE.getColumnName(),school.getSchoolHouse());
        map.put(SCHOOL_ZIP.getColumnName(),school.getSchoolZip());
        map.put(SCHOOL_CITY.getColumnName(),school.getSchoolCity());
        map.put(SCHOOL_STATE.getColumnName(),school.getSchoolState());
        map.put(SCHOOL_COUNTRY.getColumnName(),school.getSchoolCountry());
        map.put(SCHOOL_PHONE.getColumnName(),school.getSchoolPhone());
        map.put(STATUS.getColumnName(),school.getStatus());
        map.put(IMAGE.getColumnName(),school.getImage());
        map.put(CREATED_BY.getColumnName(), school.getCreatedBy());
        map.put(UPDATED_BY.getColumnName(), school.getUpdatedBy());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(school.getCreatedDate()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(school.getUpdatedDate()));
        return map;
    }

    @Override
    public School mapRow(ResultSet rs, int rowNum) throws SQLException {
        School school = new School();
        
        school.setId(rs.getLong(ID.getColumnName()));
        school.setSchoolName(rs.getString(SCHOOL_NAME.getColumnName()));
        school.setSchoolEmail(rs.getString(SCHOOL_EMAIL.getColumnName()));
        school.setSchoolStreet(rs.getString(SCHOOL_STREET.getColumnName()));
        school.setSchoolHouse(rs.getString(SCHOOL_HOUSE.getColumnName()));
        school.setSchoolZip(rs.getLong(SCHOOL_ZIP.getColumnName()));
        school.setSchoolCity(rs.getString(SCHOOL_CITY.getColumnName()));
        school.setSchoolState(rs.getString(SCHOOL_STATE.getColumnName()));
        school.setSchoolCountry(rs.getString(SCHOOL_COUNTRY.getColumnName()));
        school.setSchoolPhone(rs.getLong(SCHOOL_PHONE.getColumnName()));
        school.setStatus(rs.getString(STATUS.getColumnName()));
        school.setImage(rs.getString(IMAGE.getColumnName()));
        school.setCreatedBy(rs.getLong(CREATED_BY.getColumnName()));
        school.setUpdatedBy(rs.getLong(UPDATED_BY.getColumnName()));
        school.setCreatedDate(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        school.setUpdatedDate(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return school;
    }
}
