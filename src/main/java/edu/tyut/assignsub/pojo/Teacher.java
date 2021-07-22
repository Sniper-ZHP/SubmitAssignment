package edu.tyut.assignsub.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Teacher {
    @ExcelProperty("职工号")
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("学院")
    private String college;
    @ExcelProperty("密码")
    private String password;

}
