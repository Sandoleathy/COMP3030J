package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 房间图片对象 hs_room_image
 * 
 * @author paru
 * @date 2024-04-14
 */
public class HsRoomImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片描述 */
    @Excel(name = "图片描述")
    private String imageDesc;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 房间id */
    @Excel(name = "房间id")
    private Long roomId;

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
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setRoomId(Long roomId)
    {
        this.roomId = roomId;
    }

    public Long getRoomId()
    {
        return roomId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imageDesc", getImageDesc())
            .append("image", getImage())
            .append("roomId", getRoomId())
            .toString();
    }
}
