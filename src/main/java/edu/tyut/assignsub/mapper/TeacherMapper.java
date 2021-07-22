package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    boolean insertTeacher(Teacher teacher);

    Teacher getTeacherById(Integer id);

    Teacher getTeacherByIdAndPassword(Integer id, String password);

    List<Teacher> getAll();

    boolean deleteTeacherById(Integer id);

    boolean updateTeacher(Teacher teacher);

}
