package com.controller;

import com.db.AdminDao;
import com.model.Admin;
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
public class AdminRestController {

    public static final String BASE_USER_PATH = "/admin/";
    public static final String GET_EMAIL_PATH = BASE_USER_PATH + "email/";
    public static final String DELETE_USER_PATH = BASE_USER_PATH + "delete/";

    private final AdminDao adminDao;

    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    public AdminRestController(AdminDao adminDao){this.adminDao = adminDao;}

    @ApiOperation(value = "Create User")
    @PostMapping(value = BASE_USER_PATH)
    public Admin create (@NonNull @RequestBody Admin admin, @ApiIgnore HttpServletResponse response) throws IOException {
        // components tests are expecting this assertion and exception handling, and will fail if removed
        try {
            Assert.isNull(admin.getId(), "User ID must be null");
            Assert.notNull(admin.getEmail(), "User email cannot be null.");
            Assert.isNull(adminDao.readByEmail(admin.getEmail()),"User already exists in the system.");
            return adminDao.create(admin, null);
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
    public Admin read(@NonNull @PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        Admin user = adminDao.read(id);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with ID: " + id + " not found.");
            return null;
        }
        return user;
    }

    @ApiOperation(value = "Get user by email")
    @GetMapping(value = GET_EMAIL_PATH + "{email}")
    public Admin readUserByEmail(@NonNull @PathVariable String email,  @ApiIgnore HttpServletResponse response) throws IOException {
        Admin user = adminDao.readByEmail(email);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with email: " + email + " not found.");
            return null;
        }
        return user;
    }

    @ApiOperation(value = "Get All user")
    @GetMapping(value = BASE_USER_PATH+ "all/")
    public List<Admin> readAll() {
        return adminDao.readAll();
    }


    @ApiOperation(value = "Update user")
    @PutMapping(value = BASE_USER_PATH)
    public void update (@RequestBody Admin admin, @ApiIgnore HttpServletResponse response) throws IOException {
        try{
            Assert.notNull(admin.getId(), "User Id must not be null");
            Assert.notNull(adminDao.read(admin.getId()), "Could not find user: " + admin.getId() + " record not found.");
            adminDao.update(admin);
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
            Assert.notNull(id,"Admin ID cannot be null");
            Assert.notNull(adminDao.read(id),"Admin does not exist in the system.");
            adminDao.delete(id);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not delete user: " + id + " record not found.");
        }
    }


}

