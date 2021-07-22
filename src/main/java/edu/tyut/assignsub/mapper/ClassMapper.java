package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Class;

import java.util.List;

public interface ClassMapper {
    boolean insertClass(Class cl);

    List<Class> getAll();

    Class getClassById(Integer id);

    int deleteClassById(Integer id);

    int updateClass(Class cl);

    Class getClassByStudentId(Integer id);
}
