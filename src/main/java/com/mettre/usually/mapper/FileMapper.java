package com.mettre.usually.mapper;

import com.mettre.usually.pojo.File;
import org.springframework.stereotype.Component;

@Component
public interface FileMapper {

    int deleteByPrimaryKey(Long fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}