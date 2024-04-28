package com.homestay.mapper;

import java.util.Date;
import java.util.List;
import com.homestay.domain.HsFinance;
import com.homestay.dto.SelectFinanceDTO;
import feign.Param;

/**
 * 民宿财务表Mapper接口
 * 
 * @author paru
 * @date 2024-04-24
 */
public interface HsFinanceMapper 
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
     * 删除民宿财务表
     * 
     * @param id 民宿财务表主键
     * @return 结果
     */
    public int deleteHsFinanceById(Long id);

    /**
     * 批量删除民宿财务表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsFinanceByIds(Long[] ids);


    /**
     * 查询指定日期的收入和支出
     *
     * @param date 指定的日期
     * @return 当日的财务记录列表
     */
    public List<HsFinance> selectFinanceByDate(Date date);

    /**
     * 查询指定时间段的财务数据
     *
     * @param selectFinanceDTO 日期选择器
     * @return 指定时间段内的财务记录列表
     */
    public List<HsFinance> selectFinancesBetweenDates(SelectFinanceDTO selectFinanceDTO);
}
