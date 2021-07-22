package edu.tyut.assignsub.controller.student;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.tyut.assignsub.mapper.SubmitMapper;
import edu.tyut.assignsub.mapper.TaskMapper;
import edu.tyut.assignsub.mapper.TeacherMapper;
import edu.tyut.assignsub.pojo.*;
import edu.tyut.assignsub.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentIndexController {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private SubmitMapper submitMapper;

    @Resource
    private FileUploadService fileUploadService;

    @GetMapping("")
    public String index() {
        return "user/student/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        Submit submit = submitMapper.getSubmitById(Integer.valueOf(id));
        Task task = taskMapper.getTaskById(submit.getTaskId());
        model.addAttribute("task", task);
        model.addAttribute("submit", submit);
        return "user/student/submit-update";
    }

    @GetMapping("/over")
    public String over(HttpServletRequest request, Model model) {
        Student student = (Student) request.getSession().getAttribute("student");
        List<Task> tasks = taskMapper.getTaskByClassId(student.getClassId());
        tasks.removeIf(task -> task.getDeadline().getTime() > new Date().getTime());
        HashMap<Integer, String> map = new HashMap<>();
        for (Teacher teacher : teacherMapper.getAll()) {
            map.put(teacher.getId(), teacher.getName());
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("map", map);
        return "user/student/over-task";
    }

    @PostMapping("/update/{id}")
    public String update(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable String id) {
        Submit submit = submitMapper.getSubmitById(Integer.valueOf(id));
        submit.setContent(request.getParameter("content"));
        submit.setUpdateTime(new Date());
        if (file != null) {
            FileUploadResult result = fileUploadService.upload(file);
            submit.setEnclosure(result.getName());
        }
        submitMapper.updateSubmit(submit);
        return "redirect:/student/submited";
    }

    @GetMapping("/unsubmit")
    public String unSubmit(HttpServletRequest request, Model model) {
        Student student = (Student) request.getSession().getAttribute("student");
        List<Task> tasks = taskMapper.getTaskByClassId(student.getClassId());
        List<Submit> submits = submitMapper.getSubmitByStudentId(student.getId());
        HashSet<Integer> hasSubmit = new HashSet<>();
        for (Submit submit : submits) {
            hasSubmit.add(submit.getTaskId());
        }
        tasks.removeIf(task -> hasSubmit.contains(task.getId()));
        List<Teacher> all = teacherMapper.getAll();
        HashMap<Integer, String> map = new HashMap<>();
        for (Teacher teacher : all) {
            map.put(teacher.getId(), teacher.getName());
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("map", map);
        return "user/student/un-submit";
    }

    @GetMapping("/submit-task/{id}")
    public String submitTask(@PathVariable String id, Model model) {
        Task task = taskMapper.getTaskById(Integer.valueOf(id));
        model.addAttribute("task", task);
        return "user/student/submit-add";
    }

    @PostMapping("/submit-task/{id}")
    public String submit(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable String id) {
        HttpSession session = request.getSession();
        Submit submit = new Submit();
        submit.setSubmitTime(new Date());
        submit.setUpdateTime(new Date());
        submit.setContent(request.getParameter("content"));
        Student student = (Student) session.getAttribute("student");
        submit.setStudentId(student.getId());
        submit.setTaskId(Integer.valueOf(id));
        if (file != null) {
            FileUploadResult result = fileUploadService.upload(file);
            submit.setEnclosure(result.getName());
        }
        submitMapper.insertSubmit(submit);
        return "user/student/index";
    }

    @GetMapping("/submited")
    public String submitted(HttpServletRequest request, Model model) {
        Student student = (Student) request.getSession().getAttribute("student");
        List<Submit> submits = submitMapper.getSubmitByStudentId(student.getId());
        for (Submit submit : submits) {
            Integer taskId = submit.getTaskId();
            Task task = taskMapper.getTaskById(taskId);
            Date now = new Date();
            if (task.getDeadline().getTime() < now.getTime()) {
                submits.remove(submit);
            }
        }
        model.addAttribute("submits", submits);
        return "user/student/submitted";
    }
}
