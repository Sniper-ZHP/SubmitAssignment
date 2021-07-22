package edu.tyut.assignsub.service;

import edu.tyut.assignsub.mapper.ClassMapper;
import edu.tyut.assignsub.mapper.StudentMapper;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Class;
import edu.tyut.assignsub.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ClassService {

    @Resource
    ClassMapper classMapper;

    public boolean insertClass(Class cl){
        return classMapper.insertClass(cl);
    }

    public void save(List<Class> list) {
        for (Class cl : list) {
            classMapper.insertClass(cl);
        }
    }

    public List<Class> getAll() {
        return classMapper.getAll();
    }

    public Class getClassById(Integer id){
        return classMapper.getClassById(id);
    }

    public boolean deleteClassById(Integer id) {
        int i = classMapper.deleteClassById(id);
        if (i > 0) return true;
        else return false;
    }

    public boolean updateClass(Class aclass){
        int i = classMapper.updateClass(aclass);
        if(i > 0) return true;
        else return false;
    }
}
