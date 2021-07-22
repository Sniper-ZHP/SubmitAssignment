package edu.tyut.assignsub.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.pojo.Student;
import edu.tyut.assignsub.service.StudentService;
import edu.tyut.assignsub.utils.UploadStudentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/student")
public class StudentController {

    @Resource
    StudentService studentService;

    @Resource
    UploadStudentListener uploadStudentListener;

    @GetMapping("")
    public String studentManager(Model model) {
        List<Student> students = studentService.getAll();
        HashMap<Integer, String> allClass = studentService.getAllClass();
        model.addAttribute("students", students);
        model.addAttribute("allClass", allClass);
        return "admin/student-manager";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes attr) {
        boolean flag = studentService.deleteStudentById(Integer.valueOf(id));
        if (flag) {
            attr.addFlashAttribute("message", "删除成功");
        } else {
            attr.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/student";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id) {
        Student student = studentService.getStudentById(Integer.valueOf(id));
        model.addAttribute(student);
        return "admin/student-update";
    }

    @PostMapping("/update")
    public String updateStudent(Student student, RedirectAttributes attr) {
        boolean flag = studentService.updateStudent(student);
        if (flag) {
            attr.addFlashAttribute("message", "修改成功");
        }else{
            attr.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/student";
    }

    @PostMapping("/add")
    public String addStudent(Student student, RedirectAttributes attr){
        boolean flag = studentService.insertStudent(student);
        if(flag){
            attr.addFlashAttribute("message", "添加成功");
        }else{
            attr.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/student";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/student-add";
    }

    @PostMapping("/addByExcel")
    public String addByExcel(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);

        EasyExcel.read(file.getInputStream(), Student.class, uploadStudentListener).sheet().doRead();
        return "redirect:/admin/student";
    }
}
