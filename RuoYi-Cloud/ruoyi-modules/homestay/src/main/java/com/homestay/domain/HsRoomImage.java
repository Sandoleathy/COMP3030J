package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 房间picture对象 hs_room_image
 * 
 * @author paru
 * @date 2024-04-14
 */
public class HsRoomImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** image description */
    @Excel(name = "image description")
    private String imageDesc;

    /** picture */
    @Excel(name = "picture")
    private String image;

    /** room id */
    @Excel(name = "room id")
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
