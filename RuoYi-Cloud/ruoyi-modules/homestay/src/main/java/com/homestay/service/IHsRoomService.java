package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsRoom;
import com.homestay.dto.AddRoomDTO;
import com.homestay.dto.SelectRoomDTO;

/**
 * 民宿房间Service接口
 * 
 * @author paru
 * @date 2024-04-12
 */
public interface IHsRoomService 
{
    /**
     * 查询民宿房间
     * 
     * @param id 民宿房间主键
     * @return 民宿房间
     */
    public HsRoom selectHsRoomById(Long id);

    /**
     * 查询民宿房间列表
     * 
     * @param selectRoomDTO 民宿房间DTO
     * @return 民宿房间集合
     */
    public List<AddRoomDTO> selectHsRoomList(SelectRoomDTO selectRoomDTO);

    /**
     * 新增民宿房间
     * 
     * @param addRoomDTO 民宿房间DTO
     * @return 结果
     */
    public int insertHsRoom(HsRoom hsRoom);

    /**
     * 修改民宿房间
     * 
     * @param hsRoom 民宿房间
     * @return 结果
     */
    public int updateHsRoom(HsRoom hsRoom);

    /**
     * 批量删除民宿房间
     * 
     * @param ids 需要删除的民宿房间主键集合
     * @return 结果
     */
    public int[] deleteHsRoomByIds(Long[] ids);

    /**
     * 删除民宿房间信息
     * 
     * @param id 民宿房间主键
     * @return 结果
     */
    public int deleteHsRoomById(Long id);
}
