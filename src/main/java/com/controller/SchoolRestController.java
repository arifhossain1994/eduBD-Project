package com.controller;

import com.db.SchoolDao;
import com.model.School;
import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SchoolRestController {


    public static final String BASE_USER_PATH = "/school/";
    public static final String GET_EMAIL_PATH = BASE_USER_PATH + "email/";
    public static final String DELETE_USER_PATH = BASE_USER_PATH + "delete/";

    private final SchoolDao schoolDao;

    private static final Logger logger = LoggerFactory.getLogger(SchoolRestController.class);

    @Autowired
    public SchoolRestController(SchoolDao schoolDao){this.schoolDao = schoolDao;}

    @ApiOperation(value = "Create User")
    @PostMapping(value = BASE_USER_PATH)
    public School create (@NonNull @RequestBody School school, @ApiIgnore HttpServletResponse response) throws IOException {
        // components tests are expecting this assertion and exception handling, and will fail if removed
        try {
            Assert.isNull(school.getId(), "User ID must be null");
            return schoolDao.create(school, null);
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
}
