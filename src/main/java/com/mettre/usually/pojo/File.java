package com.mettre.usually.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class File {

    private Long fileId;

    private String fileLink;

    private Date creationTime;

    public File(String fileLink) {
        this.fileLink = fileLink;
        this.creationTime = new Date();
    }
}