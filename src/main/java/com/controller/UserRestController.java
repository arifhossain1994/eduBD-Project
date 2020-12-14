package com.controller;

import com.db.UserDao;
import com.model.User;

import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserRestController {

    public static final String BASE_USER_PATH = "/user/";
    public static final String GET_EMAIL_PATH = BASE_USER_PATH + "email/";
    public static final String DELETE_USER_PATH = BASE_USER_PATH + "delete/";



    private final UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    public UserRestController(UserDao userDao){this.userDao = userDao;}

    @ApiOperation(value = "Create User")
    @PostMapping(value = BASE_USER_PATH)
    public User create (@NonNull @RequestBody User user,  @ApiIgnore HttpServletResponse response) throws IOException {
        // components tests are expecting this assertion and exception handling, and will fail if removed
        try {
            Assert.isNull(user.getId(), "User ID must be null");
            return userDao.create(user, null);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return null;
        }

    }

    @ApiOperation(value = "Read User")
    @GetMapping(value = BASE_USER_PATH + "{id}")
    public User read(@NonNull @PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        User user = userDao.read(id);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with ID: " + id + " not found.");
            return null;
        }
        return user;
    }

    @ApiOperation(value = "Get user by email")
    @GetMapping(value = GET_EMAIL_PATH + "{email}")
    public User readUserByEmail(@NonNull @PathVariable String email,  @ApiIgnore HttpServletResponse response) throws IOException {
        User user = userDao.readByEmail(email);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with email: " + email + " not found.");
            return null;
        }
        return user;
    }

    @ApiOperation(value = "Get All user")
    @GetMapping(value = BASE_USER_PATH+ "all/")
    public List<User> readAll() {
        return userDao.readAll();
    }


    @ApiOperation(value = "Update user")
    @PutMapping(value = BASE_USER_PATH)
    public void update (@RequestBody User user, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            Assert.notNull(user.getId(), "User Id must not be null");
            Assert.notNull(userDao.read(user.getId()), "Could not find user: " + user.getId() + " record not found.");
            userDao.update(user);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping(DELETE_USER_PATH + "{id}")
    public @ResponseBody void delete  (@PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            userDao.delete(id);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not delete user: " + id + " record not found.");
        }
    }


}
