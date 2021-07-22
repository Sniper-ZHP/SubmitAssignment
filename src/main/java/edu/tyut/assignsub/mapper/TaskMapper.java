package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Task;

import java.util.List;

public interface TaskMapper {

    Task getTaskById(Integer id);

    List<Task> getTaskByClassId(Integer id);

    List<Task> getTaskByTeacherId(Integer id);

    boolean insertTask(Task task);

    boolean deleteTaskById(Integer id);

    boolean updateTaskById(Task task);
}
