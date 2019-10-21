package com.mettre.usually.controller;

import cn.hutool.core.util.StrUtil;
import com.mettre.base.Result;
import com.mettre.base.ResultUtil;
import com.mettre.enum_.ResultEnum;
import com.mettre.usually.service.FileService;
import com.mettre.usually.utils.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Slf4j
@RestController
@Api(description = "文件上传")
public class UploadController {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${spring.img.location}")
    private String location;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    public FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/img/upload", method = RequestMethod.PUT)
    @ApiOperation(value = "本地上传文件")
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile multipartFile) {

        return new ResultUtil<>().setData(fileService.insert(multipartFile));
    }

    @RequestMapping(value = "/qiniu/upload", method = RequestMethod.POST)
    @ApiOperation(value = "七牛云文件上传")
    public Result<Object> upload(@RequestParam("file") MultipartFile file) {

        // IP限流 在线Demo所需 5分钟限1个请求
//        String token1 = redisRaterLimiter.acquireTokenFromBucket("upload:"+IpInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token1)) {
//            throw new XbootException("上传那么多干嘛，等等再传吧");
//        }

        String imagePath = null;
        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            //上传七牛云服务器
            imagePath = qiniuUtil.qiniuInputStreamUpload(inputStream, fileName);
            if (StrUtil.isBlank(imagePath)) {
                return new ResultUtil<Object>().setErrorResultEnum(ResultEnum.IMG_QINIUYUN_EMPTY);
            }
            if (imagePath.contains("error")) {
                return new ResultUtil<Object>().setErrorMsg(imagePath);
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new ResultUtil<Object>().setErrorMsg(e.toString());
        }

        return new ResultUtil<Object>().setData(imagePath);
    }
}
