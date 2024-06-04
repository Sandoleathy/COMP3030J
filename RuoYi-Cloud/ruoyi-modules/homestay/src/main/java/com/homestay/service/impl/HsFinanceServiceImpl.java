package com.homestay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.homestay.dto.FinanceDTO;
import com.homestay.dto.SelectFinanceDTO;
import com.homestay.mapper.HsStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsFinanceMapper;
import com.homestay.domain.HsFinance;
import com.homestay.service.IHsFinanceService;

/**
 * 民宿财务表Service业务层处理
 *
 * @author paru
 * @date 2024-04-24
 */
@Service
public class HsFinanceServiceImpl implements IHsFinanceService {
    @Autowired
    private HsFinanceMapper hsFinanceMapper;

    /**
     * 查询民宿财务表
     *
     * @param id 民宿财务表主键
     * @return 民宿财务表
     */
    @Override
    public HsFinance selectHsFinanceById(Long id) {
        return hsFinanceMapper.selectHsFinanceById(id);
    }

    /**
     * 查询民宿财务表列表
     *
     * @param hsFinance 民宿财务表
     * @return 民宿财务表
     */
    @Override
    public List<HsFinance> selectHsFinanceList(HsFinance hsFinance) {
        return hsFinanceMapper.selectHsFinanceList(hsFinance);
    }

    /**
     * 新增民宿财务表
     *
     * @param hsFinance 民宿财务表
     * @return 结果
     */
    @Override
    public int insertHsFinance(HsFinance hsFinance) {
        return hsFinanceMapper.insertHsFinance(hsFinance);
    }

    /**
     * 修改民宿财务表
     *
     * @param hsFinance 民宿财务表
     * @return 结果
     */
    @Override
    public int updateHsFinance(HsFinance hsFinance) {
        return hsFinanceMapper.updateHsFinance(hsFinance);
    }

    /**
     * 批量删除民宿财务表
     *
     * @param ids 需要删除的民宿财务表主键
     * @return 结果
     */
    @Override
    public int deleteHsFinanceByIds(Long[] ids) {
        return hsFinanceMapper.deleteHsFinanceByIds(ids);
    }

    /**
     * 删除民宿财务表信息
     *
     * @param id 民宿财务表主键
     * @return 结果
     */
    @Override
    public int deleteHsFinanceById(Long id) {
        return hsFinanceMapper.deleteHsFinanceById(id);
    }

    @Override
    public FinanceDTO selectFinanceByDate(Date date) {
        Map<String, Float> results = new HashMap<>();
        List<HsFinance> finances = hsFinanceMapper.selectFinanceByDate(date);
        float income = 0;
        float outcome = 0;
        for (HsFinance hsFinance : finances) {
            if (hsFinance.getType().equals("income")) {
                income += hsFinance.getNum().floatValue();
            }
            if (hsFinance.getType().equals("expenditure")) {
                outcome += hsFinance.getNum().floatValue();
            }
        }
        results.put("净income", income - outcome);
        results.put("income", income);
        results.put("expenditure", outcome);
        return new FinanceDTO(results, finances);
    }

    @Override
    public FinanceDTO selectFinancesBetweenDates(SelectFinanceDTO selectFinanceDTO) {
        Map<String, Float> results = new HashMap<>();
        List<HsFinance> finances = hsFinanceMapper.selectFinancesBetweenDates(selectFinanceDTO);
        float income = 0;
        float outcome = 0;
        for (HsFinance hsFinance : finances) {
            if (hsFinance.getType().equals("income")) {
                income += hsFinance.getNum().floatValue();
            }
            if (hsFinance.getType().equals("expenditure")) {
                outcome += hsFinance.getNum().floatValue();
            }
        }
        results.put("净income", income - outcome);
        results.put("income", income);
        results.put("expenditure", outcome);
        return new FinanceDTO(results, finances);
    }

}
