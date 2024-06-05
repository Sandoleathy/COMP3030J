package com.homestay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.homestay.domain.HsStaff;
import com.homestay.dto.SelectFinanceDTO;
import com.homestay.service.IHsStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.homestay.domain.HsFinance;
import com.homestay.service.IHsFinanceService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * B&B financial statementController
 *
 * @author paru
 * @date 2024-04-24
 */
@RestController
@RequestMapping("/finance")
public class HsFinanceController extends BaseController {
    @Autowired
    private IHsFinanceService hsFinanceService;

    @Autowired
    private IHsStaffService hsStaffService;

    /**
     * 查询B&B financial statement列表
     */
    @RequiresPermissions("homestay:finance:list")
    @GetMapping("/list")
    public TableDataInfo list(HsFinance hsFinance) {
        startPage();
        List<HsFinance> list = hsFinanceService.selectHsFinanceList(hsFinance);
        return getDataTable(list);
    }

    /**
     * 导出B&B financial statement列表
     */
    @RequiresPermissions("homestay:finance:export")
    @Log(title = "B&B financial statement", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsFinance hsFinance) {
        List<HsFinance> list = hsFinanceService.selectHsFinanceList(hsFinance);
        ExcelUtil<HsFinance> util = new ExcelUtil<HsFinance>(HsFinance.class);
        util.exportExcel(response, list, "B&B financial statement数据");
    }

    /**
     * 获取B&B financial statement详细信息
     */
    @RequiresPermissions("homestay:finance:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsFinanceService.selectHsFinanceById(id));
    }

    /**
     * 新增B&B financial statement
     */
    @RequiresPermissions("homestay:finance:add")
    @Log(title = "B&B financial statement", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsFinance hsFinance) {
        return toAjax(hsFinanceService.insertHsFinance(hsFinance));
    }

    /**
     * 修改B&B financial statement
     */
    @RequiresPermissions("homestay:finance:edit")
    @Log(title = "B&B financial statement", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsFinance hsFinance) {
        return toAjax(hsFinanceService.updateHsFinance(hsFinance));
    }

    /**
     * 删除B&B financial statement
     */
    @RequiresPermissions("homestay:finance:remove")
    @Log(title = "B&B financial statement", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(hsFinanceService.deleteHsFinanceByIds(ids));
    }

    /**
     * 获取B&B financial statement某日的详细信息
     */
    @RequiresPermissions("homestay:finance:queryByDate")
    @GetMapping("/queryByDate")
    public AjaxResult getInfoByDate(Date date) {
        return success(hsFinanceService.selectFinanceByDate(date));
    }

    /**
     * 获取B&B financial statement某个时间段的详细信息
     */
    @RequiresPermissions("homestay:finance:queryByPeriod")
    @GetMapping("/queryByPeriod")
    public AjaxResult getInfoByPeriod(SelectFinanceDTO selectFinanceDTO) {
        return success(hsFinanceService.selectFinancesBetweenDates(selectFinanceDTO));
    }

    /**
     * Pay employees wages
     */
    @RequiresPermissions("homestay:finance:pay")
    @Log(title = "B&B financial statement", businessType = BusinessType.INSERT)
    @PostMapping("/pay/{ids}")
    public AjaxResult pay(@PathVariable Long[] ids) {
        HsFinance hsFinance = new HsFinance();
        float outcome = 0;
        if (ids[0] == 0) {
            outcome = hsStaffService.selectAllSalary();
        } else {
            outcome = hsStaffService.selectSalaryByIds(ids);

        }
        hsFinance.setType("expenditure");
        hsFinance.setNum(new BigDecimal(outcome));
        hsFinance.setDescription("Pay employees wages");
        hsFinance.setTime(new Date());
        return success(hsFinanceService.insertHsFinance(hsFinance));
    }
}
