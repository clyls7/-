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
import com.huaan.mes.md.domain.CysFqmx;
import com.huaan.mes.md.service.ICysFqmxService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 分切明细Controller
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/md/fqmx")
public class CysFqmxController extends BaseController
{
    @Autowired
    private ICysFqmxService cysFqmxService;

    /**
     * 查询分切明细列表
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysFqmx cysFqmx)
    {
        startPage();
        List<CysFqmx> list = cysFqmxService.selectCysFqmxList(cysFqmx);
        return getDataTable(list);
    }

    /**
     * 导出分切明细列表
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:export')")
    @Log(title = "分切明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysFqmx cysFqmx)
    {
        List<CysFqmx> list = cysFqmxService.selectCysFqmxList(cysFqmx);
        ExcelUtil<CysFqmx> util = new ExcelUtil<CysFqmx>(CysFqmx.class);
        util.exportExcel(response, list, "分切明细数据");
    }

    /**
     * 获取分切明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:query')")
    @GetMapping(value = "/{fqmxId}")
    public AjaxResult getInfo(@PathVariable("fqmxId") String fqmxId)
    {
        return AjaxResult.success(cysFqmxService.selectCysFqmxByFqmxId(fqmxId));
    }

    /**
     * 新增分切明细
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:add')")
    @Log(title = "分切明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysFqmx cysFqmx)
    {
        return toAjax(cysFqmxService.insertCysFqmx(cysFqmx));
    }

    /**
     * 修改分切明细
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:edit')")
    @Log(title = "分切明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysFqmx cysFqmx)
    {
        return toAjax(cysFqmxService.updateCysFqmx(cysFqmx));
    }

    /**
     * 删除分切明细
     */
    @PreAuthorize("@ss.hasPermi('md:fqmx:remove')")
    @Log(title = "分切明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fqmxIds}")
    public AjaxResult remove(@PathVariable String[] fqmxIds)
    {
        return toAjax(cysFqmxService.deleteCysFqmxByFqmxIds(fqmxIds));
    }
}
