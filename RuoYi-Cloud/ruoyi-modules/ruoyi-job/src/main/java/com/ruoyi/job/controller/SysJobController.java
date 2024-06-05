package com.ruoyi.job.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.job.TaskException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.job.domain.SysJob;
import com.ruoyi.job.service.ISysJobService;
import com.ruoyi.job.util.CronUtils;
import com.ruoyi.job.util.ScheduleUtils;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController
{
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询scheduled tasks列表
     */
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob)
    {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出scheduled tasks列表
     */
    @RequiresPermissions("monitor:job:export")
    @Log(title = "scheduled tasks", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        util.exportExcel(response, list, "scheduled tasks");
    }

    /**
     * 获取scheduled tasks详细信息
     */
    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobService.selectJobById(jobId));
    }

    /**
     * 新增scheduled tasks
     */
    @RequiresPermissions("monitor:job:add")
    @Log(title = "scheduled tasks", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, Cron expression is incorrect");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, target string does not allow 'rmi' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS }))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, target string does not allow 'ldap(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.HTTP, Constants.HTTPS }))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, target string does not allow 'http(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, there is a violation in the target string");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return error("Add new task'" + job.getJobName() + "'Failed, the target string is not in the whitelist");
        }
        job.setCreateBy(SecurityUtils.getUsername());
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改scheduled tasks
     */
    @RequiresPermissions("monitor:job:edit")
    @Log(title = "scheduled tasks", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, Cron expression is incorrect");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, target string does not allow 'rmi' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS }))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, target string does not allow 'ldap(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.HTTP, Constants.HTTPS }))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, target string does not allow 'http(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, there is a violation in the target string");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return error("Modify task'" + job.getJobName() + "'Failed, the target string is not in the whitelist");
        }
        job.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(jobService.updateJob(job));
    }

    /**
     * scheduled tasks状态修改
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @Log(title = "scheduled tasks", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * scheduled tasks立即执行一次
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @Log(title = "scheduled tasks", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException
    {
        boolean result = jobService.run(job);
        return result ? success() : error("The task does not exist or has expired!");
    }

    /**
     * 删除scheduled tasks
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "scheduled tasks", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobIds);
        return success();
    }
}
