package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> getAll();

    Student getStudentById(Integer id);

    Student getStudentByIdAndPassword(Integer id, String password);

    int deleteById(Integer id);

    boolean insertStudent(Student student);

    int updateStudent(Student student);

    String getClassNameByStudentId(Integer id);
}
