package com.db;

import com.model.Admin;
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

public class AdminDao extends BaseDao<Admin> {

    private static final Logger LOG = LoggerFactory.getLogger(AdminDao.class);

    /**
     * create a User object
     * @param admin  {@link Admin}
     * @param creatorId
     * @return
     */
    @Override
    public Admin create(Admin admin, Long creatorId) {
        if (admin == null) {
            throw new DaoException("Request to create a new User received null");
        } else if (admin.getId() != null) {
            throw new DaoException("When creating a new user the id should be null, but was set to " + admin.getId());
        }
        LOG.trace("UserDao creating user {}", admin);

        admin.setCreated_date(LocalDateTime.now());
        admin.setUpdated_date(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcTemplate.update(sql("createAdmin"),
                new MapSqlParameterSource(rowMapper.mapObject(admin)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new DaoException(String.format("UserDao: Failed attempt to create user %s - affected %s rows", admin.toString(), result));
        }
        Long id = keyHolder.getKey().longValue();
        admin.setId(id);

        //UserAuditDao u= new UserAuditDao();
        // u.create2(user, creatorId);

        return admin;
    }


    /**
     * Read a {@link Admin} by ID from the datasource
     * @param id to query
     * @return the user with this id or null if not found
     */
    @Override
    public Admin read(Long id) {
        try {
            return (Admin) this.jdbcTemplate.queryForObject(sql("readAdmin"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("User dao: couldn't get a user with id: " + id);
        }
        return null;
    }

    /**
     * Read a {@link Admin} by email from the datasource
     * @param email
     * @return user found or null if not found
     */
    public Admin readByEmail(String email) {
        try {
            return (Admin) this.jdbcTemplate.queryForObject(sql("readAdminByEmail"), new MapSqlParameterSource("email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("User dao couldn't get a user with email: " + email);
        }
        return null;
    }

    public List<Admin> readAll() {
        LOG.trace("Reading all users from User Audit Table.");
        return jdbcTemplate.query(sql("getAllUsers"), rowMapper);

    }

    /**
     * update a {@link Admin} by the given user id
     * @param admin {@link Admin}
     */
    @Override
    public void update(Admin admin) {
        if (admin == null){
            throw new DaoException("Request to update a user received null");
        }
        admin.setUpdated_date(LocalDateTime.now());
        int result = this.jdbcTemplate.update(sql("updateAdmin"), new MapSqlParameterSource(rowMapper.mapObject(admin)));
        if (result != 1 ){
            throw new DaoException(String.format("UserDao: Failed attempt to update user %s", admin.toString()));
        }
    }


    @Override
    public void delete(long id) {
        int result = this.jdbcTemplate.update(sql("deleteAdmin"),new MapSqlParameterSource("id", id));
        if(result != 1){
            throw new DaoException(String.format("User Dao: Failed attempt to update user %s affected %s rows", id, result));
        }
    }
}

