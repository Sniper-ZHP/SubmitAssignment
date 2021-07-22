package edu.tyut.assignsub.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.tyut.assignsub.pojo.Student;
import edu.tyut.assignsub.service.StudentService;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadStudentListener extends AnalysisEventListener<Student> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Student> list = new ArrayList<>();

    @Resource
    private StudentService studentService;

    public UploadStudentListener(){
        studentService = new StudentService();
    }


    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        list.add(student);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
    private void saveData() {
        studentService.save(list);
    }

}
