package com.controller;

import com.db.TeacherDao;
import com.model.Teacher;
import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/ManageTeacher")
public class TeacherRestController {
    public static final String BASE_TEACHER_PATH = "/teacher/";
    public static final String GET_EMAIL_PATH = BASE_TEACHER_PATH + "email/";
    public static final String DELETE_USER_PATH = BASE_TEACHER_PATH + "delete/";
    
    private final TeacherDao teacherDao;

    private static final Logger logger = LoggerFactory.getLogger(TeacherRestController.class);

    @Autowired
    public TeacherRestController(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @ApiOperation(value = "Create Teacher")
    @PostMapping(value = BASE_TEACHER_PATH)
    public Teacher create (@NonNull @RequestBody Teacher teacher, @ApiIgnore HttpServletResponse response) throws IOException {
        // components tests are expecting this assertion and exception handling, and will fail if removed
        try {
            Assert.isNull(teacher.getId(), "Teacher ID must be null");
            Assert.notNull(teacher.getEmail(), "Teacher email cannot be null.");
            Assert.isNull(teacherDao.readTeacherByEmail(teacher.getEmail()),"Teacher already exists in the system.");
            return teacherDao.create(teacher, null);
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
