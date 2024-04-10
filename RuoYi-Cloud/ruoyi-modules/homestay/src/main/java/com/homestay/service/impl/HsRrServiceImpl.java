package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsRrMapper;
import com.homestay.domain.HsRr;
import com.homestay.service.IHsRrService;

/**
 * 订单客户联合Service业务层处理
 * 
 * @author paru
 * @date 2024-04-10
 */
@Service
public class HsRrServiceImpl implements IHsRrService 
{
    @Autowired
    private HsRrMapper hsRrMapper;

    /**
     * 查询订单客户联合
     * 
     * @param id 订单客户联合主键
     * @return 订单客户联合
     */
    @Override
    public HsRr selectHsRrById(Long id)
    {
        return hsRrMapper.selectHsRrById(id);
    }

    /**
     * 查询订单客户联合列表
     * 
     * @param hsRr 订单客户联合
     * @return 订单客户联合
     */
    @Override
    public List<HsRr> selectHsRrList(HsRr hsRr)
    {
        return hsRrMapper.selectHsRrList(hsRr);
    }

    /**
     * 新增订单客户联合
     * 
     * @param hsRr 订单客户联合
     * @return 结果
     */
    @Override
    public int insertHsRr(HsRr hsRr)
    {
        return hsRrMapper.insertHsRr(hsRr);
    }

    /**
     * 修改订单客户联合
     * 
     * @param hsRr 订单客户联合
     * @return 结果
     */
    @Override
    public int updateHsRr(HsRr hsRr)
    {
        return hsRrMapper.updateHsRr(hsRr);
    }

    /**
     * 批量删除订单客户联合
     * 
     * @param ids 需要删除的订单客户联合主键
     * @return 结果
     */
    @Override
    public int deleteHsRrByIds(Long[] ids)
    {
        return hsRrMapper.deleteHsRrByIds(ids);
    }

    /**
     * 删除订单客户联合信息
     * 
     * @param id 订单客户联合主键
     * @return 结果
     */
    @Override
    public int deleteHsRrById(Long id)
    {
        return hsRrMapper.deleteHsRrById(id);
    }
}
