package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsRoomMapper;
import com.homestay.domain.HsRoom;
import com.homestay.service.IHsRoomService;

/**
 * 民宿房间Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class HsRoomServiceImpl implements IHsRoomService 
{
    @Autowired
    private HsRoomMapper hsRoomMapper;

    /**
     * 查询民宿房间
     * 
     * @param id 民宿房间主键
     * @return 民宿房间
     */
    @Override
    public HsRoom selectHsRoomById(Long id)
    {
        return hsRoomMapper.selectHsRoomById(id);
    }

    /**
     * 查询民宿房间列表
     * 
     * @param hsRoom 民宿房间
     * @return 民宿房间
     */
    @Override
    public List<HsRoom> selectHsRoomList(HsRoom hsRoom)
    {
        return hsRoomMapper.selectHsRoomList(hsRoom);
    }

    /**
     * 新增民宿房间
     * 
     * @param hsRoom 民宿房间
     * @return 结果
     */
    @Override
    public int insertHsRoom(HsRoom hsRoom)
    {
        return hsRoomMapper.insertHsRoom(hsRoom);
    }

    /**
     * 修改民宿房间
     * 
     * @param hsRoom 民宿房间
     * @return 结果
     */
    @Override
    public int updateHsRoom(HsRoom hsRoom)
    {
        return hsRoomMapper.updateHsRoom(hsRoom);
    }

    /**
     * 批量删除民宿房间
     * 
     * @param ids 需要删除的民宿房间主键
     * @return 结果
     */
    @Override
    public int deleteHsRoomByIds(Long[] ids)
    {
        return hsRoomMapper.deleteHsRoomByIds(ids);
    }

    /**
     * 删除民宿房间信息
     * 
     * @param id 民宿房间主键
     * @return 结果
     */
    @Override
    public int deleteHsRoomById(Long id)
    {
        return hsRoomMapper.deleteHsRoomById(id);
    }
}
