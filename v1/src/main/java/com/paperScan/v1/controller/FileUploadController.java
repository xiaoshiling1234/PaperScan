package com.paperScan.v1.controller;

import com.paperScan.v1.Exception.ServiceException;
import com.paperScan.v1.pojo.IStatusMessage;
import com.paperScan.v1.pojo.ResponseResult;
import com.paperScan.v1.pojo.dto.MessageDTO;
import com.paperScan.v1.pojo.vo.ImageUpVO;
import com.paperScan.v1.pojo.vo.TextUpVO;
import com.paperScan.v1.service.FileUpAndDownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUpAndDownService fileUpAndDownService;


    @RequestMapping(value = "/setFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setFileUpload(ImageUpVO imageUpVO, @RequestParam("file") MultipartFile file) {
        ResponseResult result = new ResponseResult();
        try {
            Map<String, Object> resultMap = upload(imageUpVO,file);
            if (!IStatusMessage.SystemStatus.SUCCESS.getMessage().equals(resultMap.get("result"))) {
                result.setCode(IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
                result.setMessage((String) resultMap.get("msg"));
                return result;
            }
            result.setData(resultMap);
        } catch (ServiceException e) {
            e.printStackTrace();
            LOGGER.error(">>>>>>图片上传异常，e={}", e.getMessage());
            result.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
            result.setMessage(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/setTextUpload", method = RequestMethod.POST)
    public HttpEntity<?> setTextUpload(@Validated TextUpVO textUpVO) {
        MessageDTO result = new MessageDTO("");
        try {
            LOGGER.info("用户上传文本，参数{}", textUpVO);
            if (fileUpAndDownService.uploadText(textUpVO)){
                result.setMessage("上传文本成功");
            }else {
                result.setMessage("上传文本失败");
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Map<String, Object> upload(ImageUpVO imageUpVO,MultipartFile file) throws ServiceException {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            if (!file.isEmpty()) {
                Map<String, Object> picMap = fileUpAndDownService.uploadPicture(imageUpVO,file);
                if (IStatusMessage.SystemStatus.SUCCESS.getMessage().equals(picMap.get("result"))) {
                    return picMap;
                } else {
                    returnMap.put("result", IStatusMessage.SystemStatus.ERROR.getMessage());
                    returnMap.put("msg", picMap.get("result"));
                }
            } else {
                LOGGER.info(">>>>>>上传图片为空文件");
                returnMap.put("result", IStatusMessage.SystemStatus.ERROR.getMessage());
                returnMap.put("msg", IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getMessage());
        }
        return returnMap;
    }
}
