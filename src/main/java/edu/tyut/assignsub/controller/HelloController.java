package edu.tyut.assignsub.controller;

import edu.tyut.assignsub.mapper.ManagerMapper;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.mapper.TeacherMapper;
import edu.tyut.assignsub.pojo.Manager;
import edu.tyut.assignsub.pojo.Student;
import edu.tyut.assignsub.pojo.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HelloController {

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ManagerMapper managerMapper;

    @GetMapping("/")
    public String hello() {
        return "user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("student");
        session.removeAttribute("manager");
        session.removeAttribute("teacher");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String lo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String identify = request.getParameter("identify");
        Integer userid = null;
        try {
            if (!"manager".equals(identify))
                userid = Integer.valueOf(request.getParameter("username"));
        } catch (Exception e) {
            request.setAttribute("message", "id输入错误,应为学号或职工号");
            return "user/login";
        }
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if ("stu".equals(identify)) {
            Student stu = studentMapper.getStudentByIdAndPassword(userid, password);
            if (stu == null) {
                request.setAttribute("message", "该学生不存在或密码错误");
            } else {
                session.setAttribute("student", stu);
                response.sendRedirect("/student");
            }
        } else if ("tea".equals(identify)) {
            Teacher tea = teacherMapper.getTeacherByIdAndPassword(userid, password);
            if (tea == null) {
                request.setAttribute("message", "该教师不存在或密码错误");
            } else {
                session.setAttribute("teacher", tea);
                response.sendRedirect("/teacher");
            }
        } else if ("manager".equals(identify)) {
            String username = request.getParameter("username");
            Manager manager = managerMapper.getManager(username, password);
            if (manager == null) {
                request.setAttribute("message", "该管理员不存在或密码错误");
            } else {
                session.setAttribute("manager", manager);
                response.sendRedirect("/admin");
            }
        }
        return "user/login";
    }
}
