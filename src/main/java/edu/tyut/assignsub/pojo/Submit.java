package edu.tyut.assignsub.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Submit {
    private Integer id;
    private Integer studentId;
    private Integer taskId;
    private String content;
    private String enclosure;
    private Date submitTime;
    private Date updateTime;
}
