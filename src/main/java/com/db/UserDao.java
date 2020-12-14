package com.db;

import com.model.User;
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

public class UserDao extends BaseDao <User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

    /**
     * create a User object
     * @param user  {@link User}
     * @param creatorId
     * @return
     */
    @Override
    public User create(User user, Long creatorId) {
        if (user == null) {
            throw new DaoException("Request to create a new User received null");
        } else if (user.getId() != null) {
            throw new DaoException("When creating a new user the id should be null, but was set to " + user.getId());
        }
        LOG.trace("UserDao creating user {}", user);

        user.setCreated_date(LocalDateTime.now());
        user.setUpdated_date(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = this.jdbcTemplate.update(sql("createUser"),
                new MapSqlParameterSource(rowMapper.mapObject(user)), keyHolder, new String[]{BaseRowMapper.BaseColumnType.ID.name()});

        if (result != 1) {
            throw new DaoException(String.format("UserDao: Failed attempt to create user %s - affected %s rows", user.toString(), result));
        }
        Long id = keyHolder.getKey().longValue();
        user.setId(id);

        //UserAuditDao u= new UserAuditDao();
       // u.create2(user, creatorId);

        return user;
    }


    /**
     * Read a {@link User} by ID from the datasource
     * @param id to query
     * @return the user with this id or null if not found
     */
    @Override
    public User read(Long id) {
        try {
            return (User) this.jdbcTemplate.queryForObject(sql("readUser"), new MapSqlParameterSource("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("User dao: couldn't get a user with id: " + id);
        }
        return null;
    }

    /**
     * Read a {@link User} by email from the datasource
     * @param email
     * @return user found or null if not found
     */
    public User readByEmail(String email) {
        try {
            return (User) this.jdbcTemplate.queryForObject(sql("readUserByEmail"), new MapSqlParameterSource("email", email), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOG.error("User dao couldn't get a user with email: " + email);
        }
        return null;
    }

    public List<User> readAll() {
        LOG.trace("Reading all users from User Audit Table.");
        return jdbcTemplate.query(sql("getAllUsers"), rowMapper);

    }

    /**
     * update a {@link User} by the given user id
     * @param user {@link User}
     */
    @Override
    public void update(User user) {
        if (user == null){
            throw new DaoException("Request to update a user received null");
        }
        user.setUpdated_date(LocalDateTime.now());
        int result = this.jdbcTemplate.update(sql("updateUser"), new MapSqlParameterSource(rowMapper.mapObject(user)));
        if (result != 1 ){
            throw new DaoException(String.format("UserDao: Failed attempt to update user %s", user.toString()));
        }
    }


    @Override
    public void delete(long id) {
        int result = this.jdbcTemplate.update(sql("deleteUser"),new MapSqlParameterSource("id", id));
        if(result != 1){
            throw new DaoException(String.format("User Dao: Failed attempt to update user %s affected %s rows", id, result));
        }
    }
}
