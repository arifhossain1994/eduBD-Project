package com.db;

import com.model.Admin;
import com.model.Teacher;
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

public class TeacherDao extends BaseDao<Teacher> {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherDao.class);
    
    @Override
    public Teacher create(Teacher teacher, Long creatorId) {
        if(teacher == null) {
            throw new DaoException("Request to create a new Teacher received null");
        } else if (teacher.getId() != null) {
            throw new DaoException("When creating a new teacher the id should be null, but was set to " + teacher.getId());
        }
        LOG.trace("TeacherDao creating user {}", teacher);

        teacher.setCreatedDate(LocalDateTime.now());
        teacher.setUpdatedDate(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcTemplate.update(sql("createTeacher"),
                new MapSqlParameterSource(rowMapper.mapObject(teacher)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new DaoException(String.format("TeacherDao: Failed attempt to create teacher %s - affected %s rows", teacher.toString(), result));
        }

        @CheckForNull
        Long id = keyHolder.getKey().longValue();
        teacher.setId(id);


        return teacher;
    }

    @Override
    public Teacher read(Long id) {
        try {
            return this.jdbcTemplate.queryForObject(sql("readTeacher"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(String.format("TeacherDao: couldn't get a teacher with id %d ", id));
        }
        return null;
    }

    public Teacher readTeacherByEmail(String email){
        try {
            return this.jdbcTemplate.queryForObject(sql("readTeacherByEmail"), new MapSqlParameterSource("email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(String.format("TeacherDao couldn't get a teacher with email %s " , email));
        }
        return null;
    }

    public List<Teacher> readAll(){
        LOG.trace("Reading all teachers from Table.");
        return jdbcTemplate.query(sql("getAllTeachers"), rowMapper);
    }

    public Teacher readTeacherBySchoolId(Long id){
        try {
            return this.jdbcTemplate.queryForObject(sql("readTeacherBySchoolId"), new MapSqlParameterSource("school_id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error(String.format("TeacherDao couldn't get a teacher with id %s " , id));
        }
        return null;
    }

    @Override
    public void update(Teacher teacher) {
        if (teacher == null){
            throw new DaoException("Request to update a teacher received null");
        }
        teacher.setUpdatedDate(LocalDateTime.now());
        int result = this.jdbcTemplate.update(sql("updateTeacher"), new MapSqlParameterSource(rowMapper.mapObject(teacher)));
        if (result != 1 ){
            throw new DaoException(String.format("TeacherDao: Failed attempt to update teacher %s", teacher.toString()));
        }

    }

    @Override
    public void delete(long id) {
        Teacher teacher=this.read(id);
        teacher.setStatus("Deleted");
        teacher.setUpdatedDate(LocalDateTime.now());
        teacher.setUpdatedBy(null); // this will change later.
        int result = this.jdbcTemplate.update(sql("deleteTeacher"),new MapSqlParameterSource("id", id));
        if(result != 1){
            throw new DaoException(String.format("TeacherDao: Failed attempt to delete teacher %s affected %s rows", id, result));
        }

    }
}
