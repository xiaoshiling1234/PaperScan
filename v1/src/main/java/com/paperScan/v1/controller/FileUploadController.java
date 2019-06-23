package com.paperScan.v1.controller;

import com.paperScan.v1.Exception.ServiceException;
import com.paperScan.v1.pojo.IStatusMessage;
import com.paperScan.v1.pojo.ResponseResult;
import com.paperScan.v1.pojo.dto.AdrressListDTO;
import com.paperScan.v1.pojo.dto.MessageDTO;
import com.paperScan.v1.pojo.dto.ScrollImaPathListDTO;
import com.paperScan.v1.pojo.dto.Top3AirticleDown;
import com.paperScan.v1.pojo.vo.ImageUpVO;
import com.paperScan.v1.pojo.vo.ScrollImageUpVO;
import com.paperScan.v1.pojo.vo.TextUpVO;
import com.paperScan.v1.service.FileUpAndDownService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @ApiOperation(value = "图片上传", nickname = "图片上传", response = ResponseResult.class)
    @RequestMapping(value = "/setFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setFileUpload(@Validated ImageUpVO imageUpVO, @RequestParam("file") MultipartFile file) {
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

    @ApiOperation(value = "文本上传", nickname = "文本上传", response = MessageDTO.class)
    @RequestMapping(value = "/setTextUpload", method = RequestMethod.POST)
    public HttpEntity<?> setTextUpload(TextUpVO textUpVO) {
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

    @ApiOperation(value = "滚图上传", nickname = "滚图上传", response = ResponseResult.class)
    @RequestMapping(value = "/scrollPictureUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult scrollPictureUp(@Validated ScrollImageUpVO scrollImageUpVO, @RequestParam("file") MultipartFile file) {
        ResponseResult result = new ResponseResult();
        try {
            Map<String, Object> resultMap = fileUpAndDownService.scrollPictureUp(scrollImageUpVO,file);
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

    /**
     * 滚图下载
     * @param
     * @return
     */
    @ApiOperation(value = "滚图下载", nickname = "滚图下载", response = ScrollImaPathListDTO.class)
    @RequestMapping(value = "/scrollPictureDown", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> scrollPictureDown() {
        LOGGER.info("滚图下载");
        List<ScrollImaPathListDTO> scrollImaPathList = fileUpAndDownService.scrollPictureDown();
        return new ResponseEntity<>(scrollImaPathList, HttpStatus.OK);
    }

    /**
     * top3推荐文档下载
     * @param
     * @return
     */
    @ApiOperation(value = "top3推荐文档下载", nickname = "top3推荐文档下载", response = Top3AirticleDown.class)
    @RequestMapping(value = "/top3AirticleDown", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> top3AirticleDown() {
        LOGGER.info("top3推荐文档下载");
        List<Top3AirticleDown> top3AirticleDown = fileUpAndDownService.top3AirticleDown();
        return new ResponseEntity<>(top3AirticleDown, HttpStatus.OK);
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
