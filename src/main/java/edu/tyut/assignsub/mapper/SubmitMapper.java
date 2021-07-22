package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Submit;

import java.util.List;

public interface SubmitMapper {
    boolean insertSubmit(Submit submit);

    List<Submit> getSubmitByStudentId(Integer id);

    Submit getSubmitById(Integer id);

    List<Submit> getSubmitByTaskId(Integer id);

    boolean deleteSubmitById(Integer id);

    boolean updateSubmit(Submit submit);
}
