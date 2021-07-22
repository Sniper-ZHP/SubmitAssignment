package edu.tyut.assignsub.service;

import com.aliyun.oss.OSSClient;
import edu.tyut.assignsub.configuration.AliyunConfig;
import edu.tyut.assignsub.pojo.FileUploadResult;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class FileUploadService {

    @Autowired
    private OSSClient ossClient;

    @Resource
    private AliyunConfig aliyunConfig;


    /**
     * 上传文件
     *
     * @param file
     * @return FileUploadResult
     */
    public FileUploadResult upload(MultipartFile file) {
        FileUploadResult fileUploadResult = new FileUploadResult();
        String fileName = file.getOriginalFilename();
        String filePath = getFilePath(fileName);

        try {
            ossClient.putObject(aliyunConfig.getBucketName(),
                    filePath, new ByteArrayInputStream(file.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        fileUploadResult.setStatus("done");
        fileUploadResult.setResponse("success");
        fileUploadResult.setName(this.aliyunConfig.getUrlPrefix() + "/" + filePath);
        fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
        return fileUploadResult;
    }

    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "AssignmentSubmission/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + sourceFileName;
    }


}
