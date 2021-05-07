package com.db;

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

import javax.annotation.CheckForNull;
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

        school.setStatus("ACTIVE");
        school.setCreatedDate(LocalDateTime.now());
        school.setUpdatedDate(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcTemplate.update(sql("createSchool"),
                new MapSqlParameterSource(rowMapper.mapObject(school)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new DaoException(String.format("SchoolDao: Failed attempt to create user %s - affected %s rows", school.toString(), result));
        }

        @CheckForNull
        Long id = keyHolder.getKey().longValue();
        school.setId(id);


        return school;
    }

    @Override
    public School read(Long id) {
        try {
            return this.jdbcTemplate.queryForObject(sql("readSchool"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(String.format("School Dao: couldn't get a user with id: %d" , id));
        }
        return null;
    }

    public School readByEmail (String email){
        try {
            return this.jdbcTemplate.queryForObject(sql("readSchoolByEmail"), new MapSqlParameterSource("school_email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(String.format("School dao couldn't get a school with email: %s" , email));
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
        school.setUpdatedDate(LocalDateTime.now());
        school.setUpdatedBy(null); //TODO

        int result = this.jdbcTemplate.update(sql("updateSchool"), new MapSqlParameterSource(rowMapper.mapObject(school)));
        if (result != 1 ){
            throw new DaoException(String.format("School Dao: Failed attempt to update user %s", school.toString()));
        }

    }

    @Override
    public void delete(long id) {
    }

    public void changeStatusByEmail(String email) {
        School school=readByEmail(email);

        if(school.getStatus().equals("DEACTIVE")){
            school.setStatus("ACTIVE");
        }
        else{
            school.setStatus("DEACTIVE");
        }
        school.setUpdatedBy(null);// TODO
        school.setUpdatedDate(LocalDateTime.now());
        int result = this.jdbcTemplate.update(sql("deleteSchool"),new MapSqlParameterSource(rowMapper.mapObject(school)));//"school_email", email));
        if(result != 1){
            throw new DaoException(String.format("School Dao: Failed attempt to disable school %s affected %s rows", email, result));
        }

    }



}
