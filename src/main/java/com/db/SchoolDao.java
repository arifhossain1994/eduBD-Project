package com.db;

import com.model.Admin;
import com.model.School;
import com.sql.dao.BaseDao;
import com.sql.dao.BaseRowMapper;
import com.sql.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.time.LocalDateTime;
import java.util.List;

public class SchoolDao extends BaseDao<School> {
    private static final Logger LOG = LoggerFactory.getLogger(SchoolDao.class);

    @Override
    public School create(School school, Long creatorId) {
        if (school == null) {
            throw new DaoException("Request to create a new school received null values.");
        } else if (school.getId() != null) {
            throw new DaoException("When creating a new school the id should be null, but was set to " + school.getId());
        }
        LOG.trace("School Dao creating user {}", school);

        school.setCreated_date(LocalDateTime.now());
        school.setUpdated_date(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcTemplate.update(sql("createSchool"),
                new MapSqlParameterSource(rowMapper.mapObject(school)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new DaoException(String.format("SchoolDao: Failed attempt to create user %s - affected %s rows", school.toString(), result));
        }
        Long id = keyHolder.getKey().longValue();
        school.setId(id);

        return school;
    }

    @Override
    public School read(Long id) {
        try {
            return (School) this.jdbcTemplate.queryForObject(sql("readSchool"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("School Dao: couldn't get a user with id: " + id);
        }
        return null;
    }

    public School readByEmail (String email){
        try {
            return (School) this.jdbcTemplate.queryForObject(sql("readSchoolByEmail"), new MapSqlParameterSource("school_email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("School dao couldn't get a school with email: " + email);
        }
        return null;
    }

    public List<School> readAll() {
        LOG.trace("Reading all Schools from the Table.");
        return jdbcTemplate.query(sql("readAllSchools"), rowMapper);

    }

    @Override
    public void update(School school) {
        if (school == null){
            throw new DaoException("Request to update a school received null id. ");
        }
        school.setUpdated_date(LocalDateTime.now());

        int result = this.jdbcTemplate.update(sql("updateSchool"), new MapSqlParameterSource(rowMapper.mapObject(school)));
        if (result != 1 ){
            throw new DaoException(String.format("School Dao: Failed attempt to update user %s", school.toString()));
        }

    }

    @Override
    public void delete(long id) { //TODO
        School school=read(id);
        school.setStatus("Inactive");
        school.setUpdated_date(LocalDateTime.now());
        school.setUpdated_by(null);// this will change later.
        int result = this.jdbcTemplate.update(sql("deleteSchool"),new MapSqlParameterSource("id", id));
        if(result != 1){
            throw new DaoException(String.format("School Dao: Failed attempt to update user %s affected %s rows", id, result));
        }

    }
}
