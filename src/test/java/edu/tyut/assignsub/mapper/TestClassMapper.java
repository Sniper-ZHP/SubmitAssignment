package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestClassMapper {

    @Resource
    private ClassMapper classMapper;

    @Test
    public void testGetAll() {
        List<Class> cls = classMapper.getAll();
        for (Class cl : cls) {
            System.out.println(cl);
        }
    }

    @Test
    public void testGetClassById() {
        Class cl = classMapper.getClassById(1);
        System.out.println(cl);
    }

    @Test
    public void testDeleteClassById() {
        int i = classMapper.deleteClassById(1234);
        System.out.println(i);
    }

    @Test
    public void testGetClassByStudentId() {
        Class classByStudentId = classMapper.getClassByStudentId(1);
        System.out.println(classByStudentId);
    }

    @Test
    public void testUpdateClass(){
        Class cl = classMapper.getClassById(1);
        cl.setName("软件xx");
        int i = classMapper.updateClass(cl);
        System.out.println(i);
    }

    @Test
    public void testInsertClass(){
        Class cl = new Class();
        cl.setName("1907");
        cl.setMajor("软件");
        boolean b = classMapper.insertClass(cl);
        System.out.println(b);
    }
}

