package edu.tyut.assignsub.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Class {
    private Integer id;
    @ExcelProperty("班级号")
    private String name;
    @ExcelProperty("专业")
    private String major;
}
