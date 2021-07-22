package edu.tyut.assignsub.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.tyut.assignsub.pojo.Teacher;
import edu.tyut.assignsub.service.TeacherService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadTeacherListener  extends AnalysisEventListener<Teacher> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Teacher> list = new ArrayList<>();

    @Resource
    private TeacherService teacherService;


    @Override
    public void invoke(Teacher teacher, AnalysisContext analysisContext) {
        list.add(teacher);
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
        teacherService.save(list);
    }

}
