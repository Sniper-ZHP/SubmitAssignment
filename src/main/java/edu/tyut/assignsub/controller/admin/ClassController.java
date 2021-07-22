package edu.tyut.assignsub.controller.admin;

import com.alibaba.excel.EasyExcel;
import edu.tyut.assignsub.mapper.ClassMapper;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Student;
import edu.tyut.assignsub.service.ClassService;
import edu.tyut.assignsub.utils.UploadClassListener;
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
@RequestMapping("/admin/class")
public class ClassController {

    @Resource
    ClassService classService;

    @Resource
    UploadClassListener uploadClassListener;

    @GetMapping("")
    public String classManager(Model model) {
        List<Class> all = classService.getAll();
        model.addAttribute("classes", all);
        return "admin/class-manager";
    }

    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable String id, RedirectAttributes attr) {
        boolean flag = classService.deleteClassById(Integer.valueOf(id));
        if (flag) {
            attr.addFlashAttribute("message", "删除成功");
        } else {
            attr.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/class";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id) {
        Class aClass = classService.getClassById(Integer.valueOf(id));
        model.addAttribute(aClass);
        return "admin/class-update";
    }

    @PostMapping("/update")
    public String updateClass(Class aclass, RedirectAttributes attr) {
        boolean flag = classService.updateClass(aclass);
        if (flag) {
            attr.addFlashAttribute("message", "修改成功");
        }else{
            attr.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/class";
    }

    @PostMapping("/add")
    public String addClass(Class cl, RedirectAttributes attr){
        boolean flag = classService.insertClass(cl);
        if(flag){
            attr.addFlashAttribute("message", "添加成功");
        }else{
            attr.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/class";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/class-add";
    }

    @PostMapping("/addByExcel")
    public String addByExcel(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);

        EasyExcel.read(file.getInputStream(), Class.class, uploadClassListener).sheet().doRead();
        return "redirect:/admin/class";
    }
}