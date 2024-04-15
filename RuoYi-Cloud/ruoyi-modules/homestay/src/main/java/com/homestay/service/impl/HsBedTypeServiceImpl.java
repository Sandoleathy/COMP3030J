package com.homestay.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.homestay.domain.HsBedImage;
import com.homestay.dto.AddBedTypeDTO;
import com.homestay.dto.SelectBedTypeDTO;
import com.homestay.mapper.HsBedImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsBedTypeMapper;
import com.homestay.domain.HsBedType;
import com.homestay.service.IHsBedTypeService;

/**
 * 民宿床类型Service业务层处理
 *
 * @author paru
 * @date 2024-04-10
 */
@Service
public class HsBedTypeServiceImpl implements IHsBedTypeService {
    @Autowired
    private HsBedTypeMapper hsBedTypeMapper;

    @Autowired
    private HsBedImageMapper hsBedImageMapper;

    /**
     * 查询民宿床类型
     *
     * @param id 民宿床类型主键
     * @return 民宿床类型
     */
    @Override
    public HsBedType selectHsBedTypeById(Long id) {
        return hsBedTypeMapper.selectHsBedTypeById(id);
    }

    /**
     * 查询民宿床类型列表
     *
     * @param selectBedTypeDTO 民宿床类型DTO
     * @return 民宿床类型
     */
    @Override
    public List<AddBedTypeDTO> selectHsBedTypeList(SelectBedTypeDTO selectBedTypeDTO) {
        HsBedType hsBedType = new HsBedType();
        hsBedType.setBedName(selectBedTypeDTO.getBedName());
        hsBedType.setMaxPeople(selectBedTypeDTO.getMaxPeople());
        hsBedType.setWidth(selectBedTypeDTO.getWidth());
        hsBedType.setLength(selectBedTypeDTO.getLength());

        HsBedImage hsBedImage = new HsBedImage();
        hsBedImage.setImageDesc(selectBedTypeDTO.getImageDesc());
        hsBedImage.setBedId(selectBedTypeDTO.getBedId());

        List<HsBedType> hsBedTypes = hsBedTypeMapper.selectHsBedTypeList(hsBedType);
        List<HsBedImage> hsImages = hsBedImageMapper.selectHsBedImageList(hsBedImage);
        List<AddBedTypeDTO> addBedTypeDTOS = new ArrayList<>();
        for (HsBedType bedType : hsBedTypes) {
            List<HsBedImage> images = new ArrayList<>();
            for (HsBedImage image : hsImages) {
                if (image.getBedId().equals(bedType.getId())) {
                    images.add(image);
                }
            }
            addBedTypeDTOS.add(new AddBedTypeDTO(bedType, images));
        }
        return addBedTypeDTOS;
    }

    /**
     * 新增民宿床类型
     *
     * @param addBedTypeDTO 民宿床类型DTO
     * @return 结果
     */
    @Override
    public int insertHsBedType(AddBedTypeDTO addBedTypeDTO) {
        HsBedType hsBedType = addBedTypeDTO.getHsBedType();
        long heBedTypeId = hsBedTypeMapper.insertHsBedType(hsBedType);
        List<HsBedImage> hsBedImages = addBedTypeDTO.getHsBedImages();
        for (HsBedImage hsBedImage : hsBedImages) {
            hsBedImage.setBedId(heBedTypeId);
            hsBedImageMapper.insertHsBedImage(hsBedImage);
        }
        return (int) heBedTypeId;
    }

    /**
     * 修改民宿床类型
     *
     * @param hsBedType 民宿床类型
     * @return 结果
     */
    @Override
    public int updateHsBedType(HsBedType hsBedType) {
        return hsBedTypeMapper.updateHsBedType(hsBedType);
    }

    /**
     * 批量删除民宿床类型
     *
     * @param ids 需要删除的民宿床类型主键
     * @return 结果
     */
    @Override
    public int[] deleteHsBedTypeByIds(Long[] ids) {
        int[] result = new int[ids.length];
        int i = 0;
        for (Long id : ids) {
            result[i] = deleteHsBedTypeById(id);
            i++;
        }
        return result;
    }

    /**
     * 删除民宿床类型信息
     *
     * @param id 民宿床类型主键
     * @return 结果
     */
    @Override
    public int deleteHsBedTypeById(Long id) {
        List<HsBedImage> hsBedImages = hsBedImageMapper.selectHsBedImageByBedId(id);
        for (HsBedImage hsBedImage : hsBedImages) {
            hsBedImageMapper.deleteHsBedImageById(hsBedImage.getId());
        }
        return hsBedTypeMapper.deleteHsBedTypeById(id);
    }
}
