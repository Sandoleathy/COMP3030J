package com.homestay.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.homestay.domain.HsRoomImage;
import com.homestay.dto.AddRoomDTO;
import com.homestay.dto.SelectRoomDTO;
import com.homestay.mapper.HsRoomImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsRoomMapper;
import com.homestay.domain.HsRoom;
import com.homestay.service.IHsRoomService;

/**
 * 民宿房间Service业务层处理
 *
 * @author paru
 * @date 2024-04-12
 */
@Service
public class HsRoomServiceImpl implements IHsRoomService {
    @Autowired
    private HsRoomMapper hsRoomMapper;

    @Autowired
    private HsRoomImageMapper hsRoomImageMapper;

    /**
     * 查询民宿房间
     *
     * @param id 民宿房间主键
     * @return 民宿房间
     */
    @Override
    public HsRoom selectHsRoomById(Long id) {
        return hsRoomMapper.selectHsRoomById(id);
    }

    /**
     * 查询民宿房间列表
     *
     * @param selectRoomDTO 民宿房间DTO
     * @return 民宿房间
     */
    @Override
    public List<AddRoomDTO> selectHsRoomList(SelectRoomDTO selectRoomDTO) {
        HsRoom hsRoom = new HsRoom();
        hsRoom.setRoomType(selectRoomDTO.getRoomType());
        hsRoom.setRoomNumber(selectRoomDTO.getRoomNumber());
        hsRoom.setBuildingType(selectRoomDTO.getBuildingType());
        hsRoom.setBedType(selectRoomDTO.getBedType());
        hsRoom.setRoomPrice(selectRoomDTO.getRoomPrice());

        HsRoomImage hsRoomImage = new HsRoomImage();
        hsRoomImage.setImageDesc(selectRoomDTO.getImageDesc());
        hsRoomImage.setRoomId(selectRoomDTO.getRoomId());

        List<HsRoom> hsRooms = hsRoomMapper.selectHsRoomList(hsRoom);
        List<HsRoomImage> hsRoomImages = hsRoomImageMapper.selectHsRoomImageList(hsRoomImage);
        List<AddRoomDTO> addRoomDTOS = new ArrayList<>();

        for (HsRoom room : hsRooms) {
            List<HsRoomImage> images = new ArrayList<>();
            for (HsRoomImage image : hsRoomImages) {
                if (image.getRoomId() == room.getId()) {
                    images.add(image);
                }
            }
            addRoomDTOS.add(new AddRoomDTO(room, images));
        }

        return addRoomDTOS;
    }

    /**
     * 新增民宿房间
     *
     * @param hsRoom 民宿房间
     * @return 结果
     */
    @Override
    public int insertHsRoom(HsRoom hsRoom) {
        return hsRoomMapper.insertHsRoom(hsRoom);
    }

    /**
     * 修改民宿房间
     *
     * @param hsRoom 民宿房间
     * @return 结果
     */
    @Override
    public int updateHsRoom(HsRoom hsRoom) {
        return hsRoomMapper.updateHsRoom(hsRoom);
    }

    /**
     * 批量删除民宿房间
     *
     * @param ids 需要删除的民宿房间主键
     * @return 结果
     */
    @Override
    public int[] deleteHsRoomByIds(Long[] ids) {
        int[] result = new int[ids.length];
        int i = 0;
        for (Long id : ids) {
            result[i] = deleteHsRoomById(id);
            i++;
        }
        return result;
    }

    /**
     * 删除民宿房间信息
     *
     * @param id 民宿房间主键
     * @return 结果
     */
    @Override
    public int deleteHsRoomById(Long id) {
        List<HsRoomImage> images = hsRoomImageMapper.selectHsRoomImageByRoomId(id);
        for (HsRoomImage hsRoomImage : images) {
            hsRoomImageMapper.deleteHsRoomImageById(hsRoomImage.getId());
        }
        return hsRoomMapper.deleteHsRoomById(id);
    }
}
