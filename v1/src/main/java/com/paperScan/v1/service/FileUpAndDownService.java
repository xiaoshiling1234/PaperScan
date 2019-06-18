package com.paperScan.v1.service;

import com.paperScan.v1.Exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUpAndDownService {
    Map<String, Object> uploadPicture(MultipartFile file) throws ServiceException;
}
