package com.mettre.usually.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.mettre.usually.base.ResultUtil;
import com.mettre.usually.base.ReturnType;
import com.mettre.usually.enum_.ResultEnum;
import com.mettre.usually.exception.CustomerException;
import com.mettre.usually.mapper.FileMapper;
import com.mettre.usually.pojo.File;
import com.mettre.usually.service.FileService;
import com.mettre.usually.utils.QiniuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.img.location}")
    private String location;

    @Autowired
    private QiniuUtil qiniuUtil;


    @Autowired
    public FileMapper fileMapper;

    @Override
    public int deleteByPrimaryKey(Long fileId) {
        return 0;
    }

    @Override
    public int insert(MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StrUtil.isBlank(multipartFile.getOriginalFilename())) {
            throw new CustomerException(ResultEnum.IMG_NOT_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new CustomerException(ResultEnum.IMG_FORMAT_ERROR);
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);

        //获取路径
        String return_path = "upload_image";
        String filePath = location + return_path;
        logger.info("图片保存路径={}", location);
        String file_name = null;
        try {
            file_name = qiniuUtil.saveImg(multipartFile, filePath);
            if (StrUtil.isBlank(file_name)) {
                throw new CustomerException(ResultEnum.IMG_NOT_EMPTY);
            }
            File file = new File(return_path + java.io.File.separator + file_name);
            int type = fileMapper.insert(file);
            return ReturnType.ReturnType(type, ResultEnum.IMG_INSERT_ERROR);
        } catch (IOException e) {
            throw new CustomerException(ResultEnum.IMG_NOT_EMPTY);
        }
    }

    @Override
    public int insertSelective(File record) {
        return 0;
    }

    @Override
    public File selectByPrimaryKey(Long fileId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(File record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(File record) {
        return 0;
    }
}
