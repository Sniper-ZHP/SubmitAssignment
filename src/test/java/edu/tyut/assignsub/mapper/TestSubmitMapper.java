package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Submit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestSubmitMapper {

    @Resource
    private SubmitMapper submitMapper;

    @Test
    public void testInsertSubmit(){
        System.out.println(submitMapper);
        Submit submit = new Submit();
        java.util.Date date = new java.util.Date();
        java.util.Date date1 = new java.util.Date();
        submit.setStudentId(2019005458);
        submit.setTaskId(1);
        submit.setContent("设置内容");
        submit.setEnclosure("设置附件");
        submit.setSubmitTime(date);
        submit.setUpdateTime(date1);
        boolean flag = submitMapper.insertSubmit(submit);
        System.out.println(flag);
    }

    @Test
    public void testGetSubmitByStudentId(){
        List<Submit> submit = submitMapper.getSubmitByStudentId(2019005458);
        System.out.println(submit);
    }

    @Test
    public void testGetSubmitById(){
        Submit submit = submitMapper.getSubmitById(2);
        System.out.println(submit);
    }

    @Test
    public void testGetSubmitByTaskId(){
        List<Submit> submit = submitMapper.getSubmitByTaskId(1);
        System.out.println(submit);
    }

    @Test
    public void testDeleteSubmitById(){
        boolean flag = submitMapper.deleteSubmitById(4);
        System.out.println(flag);
    }

    @Test
    public void testUpdateSubmit(){
        Submit submit = submitMapper.getSubmitById(2);
        submit.setContent("更新内容");
        submit.setEnclosure("更新附件");
        boolean flag = submitMapper.updateSubmit(submit);
        System.out.println(flag);
    }
}
