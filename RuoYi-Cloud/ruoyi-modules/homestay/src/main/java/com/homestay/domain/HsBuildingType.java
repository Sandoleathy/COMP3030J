package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿Building type对象 hs_building_type
 * 
 * @author paru
 * @date 2024-04-09
 */
public class HsBuildingType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Building type */
    @Excel(name = "Building type")
    private String buildingType;

    /** Is breakfast provided? */
    @Excel(name = "Is breakfast provided?")
    private Integer breakfast;

    /** Is smoking allowed? */
    @Excel(name = "Is smoking allowed?")
    private Integer allowSmoking;

    /** Is there a bathtub */
    @Excel(name = "Is there a bathtub")
    private Integer bathtub;

    /** Are there shower facilities? */
    @Excel(name = "Are there shower facilities?")
    private Integer bathroomAmenities;

    /** area */
    @Excel(name = "area")
    private Integer area;

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
    public void setArea(Integer area)
    {
        this.area = area;
    }

    public Integer getArea()
    {
        return area;
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
            .append("area", getArea())
            .toString();
    }
}
