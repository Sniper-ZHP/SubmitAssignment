package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Student;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestStudentMapper {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void testGetClassNameByStudentId(){
        String className = studentMapper.getClassNameByStudentId(2019005458);
        System.out.println(className);
    }

    @Test
    public void testGetStudent(){
        Student s1 = studentMapper.getStudentByIdAndPassword(2019005458, "991201");
        System.out.println(s1);
        Student s2 = studentMapper.getStudentByIdAndPassword(2156341, "11");
        System.out.println(s2);
    }

    @Test
    public void testInsertStudent(){
        Student student = new Student();
        student.setName("张浩鹏");
        student.setId(2019005458);
        student.setPassword("991201");
        student.setGender("男");
        student.setEmail("859173376@qq.com");
        boolean b = studentMapper.insertStudent(student);
        System.out.println(b);
    }

    @Test
    public void testGetStudentById(){
        Student student = studentMapper.getStudentById(2019005458);
        System.out.println(student);
    }

    @Test
    public void testGetAll(){
        List<Student> students = studentMapper.getAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdateStudent(){
        Student student = studentMapper.getStudentById(2019005458);
        student.setPassword("000407");
        studentMapper.updateStudent(student);
    }

    @Test
    public void testDeleteStudentById(){
        int i = studentMapper.deleteById(2019005458);
        System.out.println(i);
    }
}
