package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 床图片对象 hs_bed_image
 * 
 * @author paru
 * @date 2024-04-14
 */
public class HsBedImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片描述 */
    @Excel(name = "图片描述")
    private String imageDesc;

    /** base64图片 */
    @Excel(name = "base64图片")
    private String imageData;

    /** 床类型id */
    @Excel(name = "床类型id")
    private Long bedId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImageDesc(String imageDesc) 
    {
        this.imageDesc = imageDesc;
    }

    public String getImageDesc() 
    {
        return imageDesc;
    }
    public void setImageData(String imageData) 
    {
        this.imageData = imageData;
    }

    public String getImageData() 
    {
        return imageData;
    }
    public void setBedId(Long bedId) 
    {
        this.bedId = bedId;
    }

    public Long getBedId() 
    {
        return bedId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imageDesc", getImageDesc())
            .append("imageData", getImageData())
            .append("bedId", getBedId())
            .toString();
    }
}
