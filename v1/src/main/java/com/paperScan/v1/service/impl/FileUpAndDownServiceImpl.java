package com.paperScan.v1.service.impl;

import com.paperScan.v1.Exception.ServiceException;
import com.paperScan.v1.config.MessageProperties;
import com.paperScan.v1.dao.mapper.FileMapper;
import com.paperScan.v1.pojo.IStatusMessage;
import com.paperScan.v1.pojo.dto.ScrollImaPathListDTO;
import com.paperScan.v1.pojo.dto.Top3AirticleDown;
import com.paperScan.v1.pojo.vo.*;
import com.paperScan.v1.service.FileUpAndDownService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileUpAndDownServiceImpl implements FileUpAndDownService {

    @Autowired
    private MessageProperties config; //用来获取file-message.properties配置文件中的信息
    @Autowired
    private FileMapper fileMapper;
    @Override
    public Map<String, Object> uploadPicture(ImageUpVO imageUpVO,MultipartFile file) throws ServiceException {
        try {
            Map<String, Object> resMap = new HashMap<>();
            String[] IMAGE_TYPE = config.getImageType().split(",");
            String path = null;
            boolean flag = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                    System.out.println("验证通过"+file.getOriginalFilename()+" "+type);
                    flag = true;
                    break;
                }
            }
            System.out.println("传入文件名字"+file.getOriginalFilename());
            if (flag) {
                resMap.put("result", IStatusMessage.SystemStatus.SUCCESS.getMessage());
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获得文件类型
                String fileType = file.getContentType();
                System.out.println(fileType);
                // 获得文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                System.out.println(imageName);
//                if (imageName.equals("form-data")){
//                    imageName="jpg";
//                }
                // 原名称
                String oldFileName = file.getOriginalFilename();
                // 新名称
                String newFileName = uuid + "." + imageName;
                // 年月日文件夹
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                // 进行压缩(大于4M)
                if (file.getSize() > config.getFileSize()) {
                    // 重新生成
                    String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                    newFileName = newUUID + "." + imageName;
                    path = config.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                    // 如果目录不存在则创建目录
                    File oldFile = new File(path);
                    if (!oldFile.exists()) {
                        oldFile.mkdirs();
                    }
                    file.transferTo(oldFile);
                    // 压缩图片
                    Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + newUUID + "." + imageName);
                } else {
                    path = config.getUpPath() + "/" + basedir + "/" + uuid + "." + imageName;
                    // 如果目录不存在则创建目录
                    File uploadFile = new File(path);
                    if (!uploadFile.exists()) {
                        uploadFile.mkdirs();
                    }
                    file.transferTo(uploadFile);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + uuid + "." + imageName);
                }
                resMap.put("oldFileName", oldFileName);
                resMap.put("newFileName", newFileName);
                resMap.put("fileSize", file.getSize());

            } else {
                resMap.put("result", "图片格式不正确,支持png|jpg|jpeg");
            }
            ImageUpVOWithUrl imageUpVOWithUrl = new ImageUpVOWithUrl();
            BeanUtils.copyProperties(imageUpVO, imageUpVOWithUrl);
            imageUpVOWithUrl.setUrl(resMap.get("path").toString());
            System.out.println(imageUpVO);
            System.out.println(imageUpVOWithUrl);
            fileMapper.resourceCreate(imageUpVOWithUrl);
            fileMapper.resourceImageCreate(imageUpVOWithUrl);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean uploadText(TextUpVO textUpVO) {
        return fileMapper.resourceTextCreate(textUpVO)>0?true:false;
    }

    @Override
    public Map<String, Object> scrollPictureUp(ScrollImageUpVO scrollImageUpVO, MultipartFile file) throws ServiceException {
        try {
            //判断图片后缀是否正确
            Map<String, Object> resMap = new HashMap<>();
            String[] IMAGE_TYPE = config.getImageType().split(",");
            String path = null;
            boolean flag = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                resMap.put("result", IStatusMessage.SystemStatus.SUCCESS.getMessage());
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获得文件类型
                String fileType = file.getContentType();
                System.out.println(fileType);
                // 获得文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                // 原名称
                String oldFileName = file.getOriginalFilename();
                // 新名称
                String newFileName = uuid + "." + imageName;
                // 年月日文件夹
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                // 进行压缩(大于4M)
                if (file.getSize() > config.getFileSize()) {
                    // 重新生成
                    String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                    newFileName = newUUID + "." + imageName;
                    path = config.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                    // 如果目录不存在则创建目录
                    File oldFile = new File(path);
                    if (!oldFile.exists()) {
                        oldFile.mkdirs();
                    }
                    file.transferTo(oldFile);
                    // 压缩图片
                    Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + newUUID + "." + imageName);
                } else {
                    path = config.getUpPath() + "/" + basedir + "/" + uuid + "." + imageName;
                    // 如果目录不存在则创建目录
                    File uploadFile = new File(path);
                    if (!uploadFile.exists()) {
                        uploadFile.mkdirs();
                    }
                    file.transferTo(uploadFile);
                    // 显示路径
                    resMap.put("path", "/" + basedir + "/" + uuid + "." + imageName);
                }
                resMap.put("oldFileName", oldFileName);
                resMap.put("newFileName", newFileName);
                resMap.put("fileSize", file.getSize());

            } else {
                resMap.put("result", "图片格式不正确,支持png|jpg|jpeg");
            }
            ScrollImageUpVOWithUrl scrollImageUpVOWithUrl = new ScrollImageUpVOWithUrl();
            BeanUtils.copyProperties(scrollImageUpVO, scrollImageUpVOWithUrl);
            scrollImageUpVOWithUrl.setUrl(resMap.get("path").toString());
            System.out.println(scrollImageUpVO);
            System.out.println(scrollImageUpVOWithUrl);
            fileMapper.scrollUpdate(scrollImageUpVOWithUrl);
            fileMapper.scrollCreate(scrollImageUpVOWithUrl);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<ScrollImaPathListDTO> scrollPictureDown() {
        List<ScrollImaPathListDTO> scrollImaPathList=fileMapper.scrollPictureDown();
        return scrollImaPathList;
    }

    @Override
    public List<Top3AirticleDown> top3AirticleDown() {
        return fileMapper.top3AirticleDown();
    }
}