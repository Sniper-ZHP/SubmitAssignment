package edu.tyut.assignsub.controller.teacher;

import com.sun.org.apache.xpath.internal.operations.Mod;
import edu.tyut.assignsub.mapper.ClassMapper;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.mapper.SubmitMapper;
import edu.tyut.assignsub.mapper.TaskMapper;
import edu.tyut.assignsub.pojo.*;
import edu.tyut.assignsub.pojo.Class;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/teacher")
public class TeacherIndexController {

    @Resource
    ClassMapper classMapper;

    @Resource
    TaskMapper taskMapper;

    @Resource
    SubmitMapper submitMapper;

    @Resource
    StudentMapper studentMapper;

    @GetMapping("")
    public String index() {
        return "user/teacher/index";
    }

    @GetMapping("/addTask")
    public String addTask(Model model) {
        List<Class> classes = classMapper.getAll();
        model.addAttribute("classes", classes);
        return "user/teacher/add-task";
    }

    @PostMapping("/addTask")
    public String add(HttpServletRequest request, Task task) {
        String[] classes = request.getParameterValues("class");
        task.setCreateTime(new Date());
        task.setTeacherId(2000);
        for (String cl : classes) {
            task.setClassId(Integer.valueOf(cl));
            taskMapper.insertTask(task);
        }
        return "redirect:/teacher";
    }

    @GetMapping("/listTask")
    public String listTask(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Task> tasks = taskMapper.getTaskByTeacherId(teacher.getId());
        model.addAttribute("tasks", tasks);
        List<Class> classes = classMapper.getAll();
        HashMap<Integer, String> map = new HashMap<>();
        for (Class aClass : classes) {
            String str = aClass.getMajor() + aClass.getName();
            map.put(aClass.getId(), str);
        }
        model.addAttribute("map", map);
        return "user/teacher/list-task";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id, Model model){
        Submit submit = submitMapper.getSubmitById(Integer.valueOf(id));
        model.addAttribute("submit", submit);
        return "user/teacher/detail-submit";
    }

    @GetMapping("/listSubmit/{id}")
    public String listSubmit(@PathVariable String id, Model model) {
        List<Submit> submits = submitMapper.getSubmitByTaskId(Integer.valueOf(id));
        model.addAttribute("submits", submits);
        HashMap<String, ArrayList<Submit>> map = new HashMap<>();
        for (Submit submit : submits) {
            Student student = studentMapper.getStudentById(submit.getStudentId());
            Class cl = classMapper.getClassById(student.getClassId());
            String className = cl.getMajor() + cl.getName();
            ArrayList<Submit> list = map.get(className);
            if (list == null) {
                list = new ArrayList<>();
                list.add(submit);
                map.put(className, list);
            } else {
                list.add(submit);
            }
        }
        model.addAttribute("map", map);
        return "user/teacher/list-submit";
    }
}
