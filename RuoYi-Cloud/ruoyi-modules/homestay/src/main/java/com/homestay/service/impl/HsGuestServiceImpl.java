package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsGuestMapper;
import com.homestay.domain.HsGuest;
import com.homestay.service.IHsGuestService;

/**
 * 民宿客户表Service业务层处理
 * 
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsGuestServiceImpl implements IHsGuestService 
{
    @Autowired
    private HsGuestMapper hsGuestMapper;

    /**
     * 查询民宿客户表
     * 
     * @param id 民宿客户表主键
     * @return 民宿客户表
     */
    @Override
    public HsGuest selectHsGuestById(Long id)
    {
        return hsGuestMapper.selectHsGuestById(id);
    }

    /**
     * 查询民宿客户表列表
     * 
     * @param hsGuest 民宿客户表
     * @return 民宿客户表
     */
    @Override
    public List<HsGuest> selectHsGuestList(HsGuest hsGuest)
    {
        return hsGuestMapper.selectHsGuestList(hsGuest);
    }

    /**
     * 新增民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    @Override
    public int insertHsGuest(HsGuest hsGuest)
    {
        return hsGuestMapper.insertHsGuest(hsGuest);
    }

    /**
     * 修改民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    @Override
    public int updateHsGuest(HsGuest hsGuest)
    {
        return hsGuestMapper.updateHsGuest(hsGuest);
    }

    /**
     * 批量删除民宿客户表
     * 
     * @param ids 需要删除的民宿客户表主键
     * @return 结果
     */
    @Override
    public int deleteHsGuestByIds(Long[] ids)
    {
        return hsGuestMapper.deleteHsGuestByIds(ids);
    }

    /**
     * 删除民宿客户表信息
     * 
     * @param id 民宿客户表主键
     * @return 结果
     */
    @Override
    public int deleteHsGuestById(Long id)
    {
        return hsGuestMapper.deleteHsGuestById(id);
    }
}
