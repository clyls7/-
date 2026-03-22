package com.huaan.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huaan.common.annotation.Log;
import com.huaan.common.core.controller.BaseController;
import com.huaan.common.core.domain.AjaxResult;
import com.huaan.common.enums.BusinessType;
import com.huaan.mes.md.domain.CysScgd;
import com.huaan.mes.md.service.ICysScgdService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 生产工单Controller
 * 
 * @author yinjinlu
 * @date 2025-06-27
 */
@RestController
@RequestMapping("/md/scgd")
public class CysScgdController extends BaseController
{
    @Autowired
    private ICysScgdService cysScgdService;

    /**
     * 查询生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysScgd cysScgd)
    {
        startPage();
        List<CysScgd> list = cysScgdService.selectCysScgdList(cysScgd);
        return getDataTable(list);
    }

    /**
     * 导出生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysScgd cysScgd)
    {
        List<CysScgd> list = cysScgdService.selectCysScgdList(cysScgd);
        ExcelUtil<CysScgd> util = new ExcelUtil<CysScgd>(CysScgd.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    /**
     * 获取生产工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:query')")
    @GetMapping(value = "/{workId}")
    public AjaxResult getInfo(@PathVariable("workId") Long workId)
    {
        return AjaxResult.success(cysScgdService.selectCysScgdByWorkId(workId));
    }

    /**
     * 新增生产工单
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysScgd cysScgd)
    {
        return toAjax(cysScgdService.insertCysScgd(cysScgd));
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysScgd cysScgd)
    {
        return toAjax(cysScgdService.updateCysScgd(cysScgd));
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('md:scgd:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workIds}")
    public AjaxResult remove(@PathVariable Long[] workIds)
    {
        return toAjax(cysScgdService.deleteCysScgdByWorkIds(workIds));
    }
}
