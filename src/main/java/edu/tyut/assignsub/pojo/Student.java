package edu.tyut.assignsub.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Student {
    @ExcelProperty("ID")
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("班级ID")
    private Integer classId;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("邮箱")
    private String email;
}
