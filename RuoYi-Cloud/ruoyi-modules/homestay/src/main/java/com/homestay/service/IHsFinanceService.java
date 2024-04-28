package com.homestay.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.homestay.domain.HsFinance;
import com.homestay.dto.FinanceDTO;
import com.homestay.dto.SelectFinanceDTO;

/**
 * 民宿财务表Service接口
 * 
 * @author paru
 * @date 2024-04-24
 */
public interface IHsFinanceService 
{
    /**
     * 查询民宿财务表
     * 
     * @param id 民宿财务表主键
     * @return 民宿财务表
     */
    public HsFinance selectHsFinanceById(Long id);

    /**
     * 查询民宿财务表列表
     * 
     * @param hsFinance 民宿财务表
     * @return 民宿财务表集合
     */
    public List<HsFinance> selectHsFinanceList(HsFinance hsFinance);

    /**
     * 新增民宿财务表
     * 
     * @param hsFinance 民宿财务表
     * @return 结果
     */
    public int insertHsFinance(HsFinance hsFinance);

    /**
     * 修改民宿财务表
     * 
     * @param hsFinance 民宿财务表
     * @return 结果
     */
    public int updateHsFinance(HsFinance hsFinance);

    /**
     * 批量删除民宿财务表
     * 
     * @param ids 需要删除的民宿财务表主键集合
     * @return 结果
     */
    public int deleteHsFinanceByIds(Long[] ids);

    /**
     * 删除民宿财务表信息
     * 
     * @param id 民宿财务表主键
     * @return 结果
     */
    public int deleteHsFinanceById(Long id);

    /**
     * 查询指定日期的收入和支出
     *
     * @param date 指定的日期
     * @return 当日的财务记录列表
     */
    public FinanceDTO selectFinanceByDate(Date date);

    /**
     * 查询指定时间段的财务数据
     *
     * @param selectFinanceDTO 日期DTO
     * @return 指定时间段内的财务记录列表
     */
    public FinanceDTO selectFinancesBetweenDates(SelectFinanceDTO selectFinanceDTO);
}
