package com.controller;

import com.db.SchoolDao;
import com.model.School;
import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SchoolRestController {

    public static final String BASE_SCHOOL_PATH = "/School";
    public static final String GET_EMAIL_PATH = BASE_SCHOOL_PATH + "/email";
    public static final String DELETE_USER_PATH = BASE_SCHOOL_PATH + "/delete";

    private final SchoolDao schoolDao;

    private static final Logger logger = LoggerFactory.getLogger(SchoolRestController.class);

    @Autowired
    public SchoolRestController(SchoolDao schoolDao){this.schoolDao = schoolDao;}


    // Manage school Page
    @GetMapping("/ManageSchool"+BASE_SCHOOL_PATH)
    public String createSchoolForm(Model model){
        model.addAttribute("school",new School());
        return "createSchoolForm";
    }

    @ApiOperation(value = "Create School")
    @PostMapping(value = "/ManageSchool"+BASE_SCHOOL_PATH, produces = {"application/json"},
            consumes = {"application/json"})
    public String create (@RequestBody School school, @ApiIgnore HttpServletResponse response) throws IOException {
        // components tests are expecting this assertion and exception handling, and will fail if removed
        try {
            Assert.isNull(school.getId(), "School ID field must be null");
            Assert.notNull(school.getSchoolEmail(),"School email cannot be null.");
            Assert.notNull(school.getSchoolPhone(),"School Phone number cannot be null. ");
            Assert.isNull(schoolDao.readByEmail(school.getSchoolEmail()),"School already exists in the system.");
            schoolDao.create(school, null);
            return "createSchoolForm";
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

    @ApiOperation(value = "Read School by ID")
    @GetMapping(value = BASE_SCHOOL_PATH + "{id}")
    public School read(@NonNull @PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        School school = schoolDao.read(id);
        if (school == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "School with ID: " + id + " not found.");
            return null;
        }
        return school;
    }

    @ApiOperation(value = "Get school by email")
    @GetMapping(value = GET_EMAIL_PATH + "{email}")
    public School readUserByEmail(@NonNull @PathVariable String email,  @ApiIgnore HttpServletResponse response) throws IOException {
        School school = schoolDao.readByEmail(email);
        if (school == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "School with email: " + email + " not found.");
            return null;
        }
        return school;
    }

    @ApiOperation(value = "Get All Schools")
    @GetMapping(value ="/ManageSchool"+ BASE_SCHOOL_PATH+ "/allSchool", produces = {"application/json"},
            consumes = {"application/json"})
    public @ResponseBody List<School> readAll() {
        List<School> schools=schoolDao.readAll();
        return schools;
    }

    @ApiOperation(value = "De-activate School")
    @DeleteMapping(DELETE_USER_PATH + "{id}")
    public @ResponseBody void delete  (@PathVariable Long id, @ApiIgnore HttpServletResponse response) throws IOException {
        try {
            Assert.notNull(id,"School ID cannot be null");
            Assert.notNull(schoolDao.read(id),"School does not exist in the system.");
            schoolDao.delete(id);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not delete School: " + id + " record not found.");
        }
    }
    
    
}
