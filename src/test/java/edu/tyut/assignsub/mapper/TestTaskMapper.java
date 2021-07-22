package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestTaskMapper {

    @Resource
    private TaskMapper taskMapper;

    @Test
    public void testInsertTask() {
        Task task = new Task();
        task.setId(1);
        task.setClassId(1);
        task.setContent("名字");
        task.setCreateTime(new Date());
        task.setTeacherId(123456);
        task.setDeadline(new Date());
        boolean b = taskMapper.insertTask(task);
        System.out.println(b);
    }

    @Test
    public void testGetTaskByClassId() {
        List<Task> task = taskMapper.getTaskByClassId(1);
        System.out.println(task);
    }

    @Test
    public void testGetTaskById() {
        Task task = taskMapper.getTaskById(1);
        System.out.println(task);
    }

    @Test
    public void testGetTaskByTeacherId() {
        System.out.println(taskMapper);
        List<Task> task = taskMapper.getTaskByTeacherId(1);
        System.out.println(task);
    }

    @Test
    public void testDeleteTaskById() {
        boolean i = taskMapper.deleteTaskById(1);
        System.out.println(i);
    }

    @Test
    public void testUpdateTask() {
        Task task = taskMapper.getTaskById(1);
        task.setContent("xxx");
        taskMapper.updateTaskById(task);
    }


}
