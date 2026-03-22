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
import com.huaan.mes.md.domain.CysScgdmx;
import com.huaan.mes.md.service.ICysScgdmxService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 生产工单明细Controller
 * 
 * @author yinjinlu
 * @date 2025-06-27
 */
@RestController
@RequestMapping("/md/scgdmx")
public class CysScgdmxController extends BaseController
{
    @Autowired
    private ICysScgdmxService cysScgdmxService;

    /**
     * 查询生产工单明细列表
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysScgdmx cysScgdmx)
    {
        startPage();
        List<CysScgdmx> list = cysScgdmxService.selectCysScgdmxList(cysScgdmx);
        return getDataTable(list);
    }

    /**
     * 导出生产工单明细列表
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:export')")
    @Log(title = "生产工单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysScgdmx cysScgdmx)
    {
        List<CysScgdmx> list = cysScgdmxService.selectCysScgdmxList(cysScgdmx);
        ExcelUtil<CysScgdmx> util = new ExcelUtil<CysScgdmx>(CysScgdmx.class);
        util.exportExcel(response, list, "生产工单明细数据");
    }

    /**
     * 获取生产工单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:query')")
    @GetMapping(value = "/{workId}")
    public AjaxResult getInfo(@PathVariable("workId") Long workId)
    {
        return AjaxResult.success(cysScgdmxService.selectCysScgdmxByWorkId(workId));
    }

    /**
     * 新增生产工单明细
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:add')")
    @Log(title = "生产工单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysScgdmx cysScgdmx)
    {
        return toAjax(cysScgdmxService.insertCysScgdmx(cysScgdmx));
    }

    /**
     * 修改生产工单明细
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:edit')")
    @Log(title = "生产工单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysScgdmx cysScgdmx)
    {
        return toAjax(cysScgdmxService.updateCysScgdmx(cysScgdmx));
    }

    /**
     * 删除生产工单明细
     */
    @PreAuthorize("@ss.hasPermi('md:scgdmx:remove')")
    @Log(title = "生产工单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workIds}")
    public AjaxResult remove(@PathVariable Long[] workIds)
    {
        return toAjax(cysScgdmxService.deleteCysScgdmxByWorkIds(workIds));
    }
}
