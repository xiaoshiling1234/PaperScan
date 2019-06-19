package com.paperScan.v1.service;

import com.paperScan.v1.Exception.ServiceException;
import com.paperScan.v1.pojo.vo.ImageUpVO;
import com.paperScan.v1.pojo.vo.TextUpVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUpAndDownService {
    Map<String, Object> uploadPicture(ImageUpVO imageUpVO, MultipartFile file) throws ServiceException;

    Boolean uploadText(TextUpVO textUpVO);

}
