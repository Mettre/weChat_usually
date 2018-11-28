package com.mettre.usually.service;

import com.mettre.usually.pojo.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    int deleteByPrimaryKey(Long fileId);

    int insert(MultipartFile multipartFile);

    int insertSelective(File record);

    File selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}
