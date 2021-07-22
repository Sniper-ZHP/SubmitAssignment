package edu.tyut.assignsub.service;

import edu.tyut.assignsub.mapper.ClassMapper;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    @Resource
    StudentMapper studentMapper;

    @Resource
    ClassMapper classMapper;


    public void save(List<Student> list) {
        for (Student student : list) {
            studentMapper.insertStudent(student);
        }
    }

    public List<Student> getAll() {
        return studentMapper.getAll();
    }

    public HashMap<Integer, String> getAllClass() {
        List<Class> classes = classMapper.getAll();
        HashMap<Integer, String> map = new HashMap<>();
        for (Class aClass : classes) {
            map.put(aClass.getId(), aClass.getMajor() + aClass.getName());
        }
        return map;
    }

    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    public boolean deleteStudentById(Integer id) {
        int i = studentMapper.deleteById(id);
        if (i > 0) return true;
        else return false;
    }

    public boolean updateStudent(Student student) {
        int i = studentMapper.updateStudent(student);
        if (i > 0) return true;
        else return false;
    }

    public boolean insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }
}
