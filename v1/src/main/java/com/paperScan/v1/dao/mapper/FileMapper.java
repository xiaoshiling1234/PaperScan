package com.paperScan.v1.dao.mapper;

import com.paperScan.v1.pojo.dto.ScrollImaPathListDTO;
import com.paperScan.v1.pojo.dto.Top3AirticleDown;
import com.paperScan.v1.pojo.vo.ImageUpVOWithUrl;
import com.paperScan.v1.pojo.vo.ScrollImageUpVOWithUrl;
import com.paperScan.v1.pojo.vo.TextUpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:查询直播数据库
 * DATE：2019/5/15 10:08
 * @author xiaobing
 */
@Mapper
public interface FileMapper {
    /**
     * 资源创建
     * @param imageUpVOWithUrl
     * @return
     */
    int resourceCreate(ImageUpVOWithUrl imageUpVOWithUrl);

    /**
     * 图片上传
     * @param imageUpVOWithUrl
     * @return
     */
    int resourceImageCreate(ImageUpVOWithUrl imageUpVOWithUrl);

    /**
     * 文档上传
     * @param textUpVO
     * @return
     */
    int resourceTextCreate(TextUpVO textUpVO);

    /**
     * 更新滚图
     * @param scrollImageUpVOWithUrl
     * @return
     */
    int scrollUpdate(ScrollImageUpVOWithUrl scrollImageUpVOWithUrl);

    /**
     * 滚图生成
     * @param scrollImageUpVOWithUrl
     * @return
     */
    int scrollCreate(ScrollImageUpVOWithUrl scrollImageUpVOWithUrl);

    /**
     * 滚图下载
     * @return
     */
    List<ScrollImaPathListDTO> scrollPictureDown();

    List<Top3AirticleDown> top3AirticleDown();
}
