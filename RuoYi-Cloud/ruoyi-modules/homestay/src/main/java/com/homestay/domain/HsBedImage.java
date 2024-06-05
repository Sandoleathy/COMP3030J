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

    /** image description */
    @Excel(name = "image description")
    private String imageDesc;

    /** base64 pictures */
    @Excel(name = "base64 pictures")
    private String imageData;

    /** bed type id */
    @Excel(name = "bed type id")
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
