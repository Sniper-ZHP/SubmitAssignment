package edu.tyut.assignsub.service;

import edu.tyut.assignsub.mapper.ClassMapper;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.mapper.TeacherMapper;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Student;
import edu.tyut.assignsub.pojo.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class TeacherService {
    @Resource
    TeacherMapper teacherMapper;


    public void save(List<Teacher> list) {
        for (Teacher teacher : list) {
            System.out.println(teacher);
            teacherMapper.insertTeacher(teacher);
        }
    }

    public List<Teacher> getAll() {
        return teacherMapper.getAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherMapper.getTeacherById(id);
    }

    public boolean deleteTeacherById(Integer id) {
        boolean i = teacherMapper.deleteTeacherById(id);
        if (i) return true;
        else return false;
    }

    public boolean updateTeacher(Teacher teacher) {
        boolean i = teacherMapper.updateTeacher(teacher);
        if (i) return true;
        else return false;
    }

    public boolean insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }
}
