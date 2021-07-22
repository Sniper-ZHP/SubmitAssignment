package edu.tyut.assignsub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.tyut.assignsub.mapper")
public class AssignmentSubmissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentSubmissionApplication.class, args);
    }

}
