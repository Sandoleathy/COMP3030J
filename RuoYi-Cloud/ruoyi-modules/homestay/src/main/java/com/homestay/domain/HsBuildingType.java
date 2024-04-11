package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿栋类型对象 hs_building_type
 * 
 * @author paru
 * @date 2024-04-09
 */
public class HsBuildingType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 栋类型 */
    @Excel(name = "栋类型")
    private String buildingType;

    /** 是否提供早餐 */
    @Excel(name = "是否提供早餐")
    private Integer breakfast;

    /** 是否允许抽烟 */
    @Excel(name = "是否允许抽烟")
    private Integer allowSmoking;

    /** 是否有浴缸 */
    @Excel(name = "是否有浴缸")
    private Integer bathtub;

    /** 是否有淋浴设施 */
    @Excel(name = "是否有淋浴设施")
    private Integer bathroomAmenities;

    /** 是否有功能性设施 */
    @Excel(name = "是否有功能性设施")
    private Integer functionalAmenities;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBuildingType(String buildingType) 
    {
        this.buildingType = buildingType;
    }

    public String getBuildingType() 
    {
        return buildingType;
    }
    public void setBreakfast(Integer breakfast) 
    {
        this.breakfast = breakfast;
    }

    public Integer getBreakfast() 
    {
        return breakfast;
    }
    public void setAllowSmoking(Integer allowSmoking) 
    {
        this.allowSmoking = allowSmoking;
    }

    public Integer getAllowSmoking() 
    {
        return allowSmoking;
    }
    public void setBathtub(Integer bathtub) 
    {
        this.bathtub = bathtub;
    }

    public Integer getBathtub() 
    {
        return bathtub;
    }
    public void setBathroomAmenities(Integer bathroomAmenities) 
    {
        this.bathroomAmenities = bathroomAmenities;
    }

    public Integer getBathroomAmenities() 
    {
        return bathroomAmenities;
    }
    public void setFunctionalAmenities(Integer functionalAmenities) 
    {
        this.functionalAmenities = functionalAmenities;
    }

    public Integer getFunctionalAmenities() 
    {
        return functionalAmenities;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("buildingType", getBuildingType())
            .append("breakfast", getBreakfast())
            .append("allowSmoking", getAllowSmoking())
            .append("bathtub", getBathtub())
            .append("bathroomAmenities", getBathroomAmenities())
            .append("functionalAmenities", getFunctionalAmenities())
            .toString();
    }
}
