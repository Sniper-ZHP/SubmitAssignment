package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestTeacherMapper {

    @Resource
    private TeacherMapper teacherMapper;

    @Test
    public void testInsertTeacher(){
        System.out.println(teacherMapper);
        Teacher teacher = new Teacher();
        teacher.setName("张浩鹏");
        teacher.setId(2019005458);
        teacher.setGender("男");
        teacher.setCollege("太原理工大学");
        boolean b = teacherMapper.insertTeacher(teacher);
        System.out.println(b);
    }

    @Test
    public void testGetTeacherById() {
        Teacher teacher = teacherMapper.getTeacherById(2019005458);
        System.out.println(teacher);
    }

    @Test
    public void testGetAll() {
        List<Teacher> teacher = teacherMapper.getAll();
        for (Teacher teacher1 : teacher) {
            System.out.println(teacher1);
        }
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = teacherMapper.getTeacherById(2019005458);
        teacher.setGender("女");
        teacherMapper.updateTeacher(teacher);

    }

    @Test
    public void testDeleteById() {
        boolean i = teacherMapper.deleteTeacherById(2019005458);
        System.out.println(i);
    }
}
