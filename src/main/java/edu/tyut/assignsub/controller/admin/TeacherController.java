package edu.tyut.assignsub.controller.admin;


import com.alibaba.excel.EasyExcel;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Teacher;
import edu.tyut.assignsub.service.TeacherService;
import edu.tyut.assignsub.utils.UploadTeacherListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController{

    @Resource
    TeacherService teacherService;

    @Resource
    UploadTeacherListener uploadTeacherListener;

    @GetMapping("")
    public String teacherManager(Model model) {
        List<Teacher> teachers = teacherService.getAll();
        model.addAttribute("teachers", teachers);
        return "admin/teacher-manager";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable String id, RedirectAttributes attr) {
        boolean flag = teacherService.deleteTeacherById(Integer.valueOf(id));
        if (flag) {
            attr.addFlashAttribute("message", "删除成功");
        } else {
            attr.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/teacher";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id) {
        Teacher teacher = teacherService.getTeacherById(Integer.valueOf(id));
        model.addAttribute(teacher);
        return "admin/teacher-update";
    }

    @PostMapping("/update")
    public String updateTeacher(Teacher teacher, RedirectAttributes attr) {
        boolean flag = teacherService.updateTeacher(teacher);
        if (flag) {
            attr.addFlashAttribute("message", "修改成功");
        }else{
            attr.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/teacher";
    }


    @PostMapping("/add")
    public String addTeacher(Teacher teacher, RedirectAttributes attr){
        boolean flag = teacherService.insertTeacher(teacher);
        if(flag){
            attr.addFlashAttribute("message", "添加成功");
        }else{
            attr.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/teacher";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/teacher-add";
    }

    @PostMapping("/addByExcel")
    public String addByExcel(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);

        EasyExcel.read(file.getInputStream(), Teacher.class, uploadTeacherListener).sheet().doRead();
        return "redirect:/admin/teacher";
    }
}
