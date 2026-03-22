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
import com.huaan.mes.md.domain.CysSx;
import com.huaan.mes.md.service.ICysSxService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 酸洗Controller
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/md/sx")
public class CysSxController extends BaseController
{
    @Autowired
    private ICysSxService cysSxService;

    /**
     * 查询酸洗列表
     */
    @PreAuthorize("@ss.hasPermi('md:sx:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysSx cysSx)
    {
        startPage();
        List<CysSx> list = cysSxService.selectCysSxList(cysSx);
        return getDataTable(list);
    }

    /**
     * 导出酸洗列表
     */
    @PreAuthorize("@ss.hasPermi('md:sx:export')")
    @Log(title = "酸洗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysSx cysSx)
    {
        List<CysSx> list = cysSxService.selectCysSxList(cysSx);
        ExcelUtil<CysSx> util = new ExcelUtil<CysSx>(CysSx.class);
        util.exportExcel(response, list, "酸洗数据");
    }

    /**
     * 获取酸洗详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:sx:query')")
    @GetMapping(value = "/{picklingId}")
    public AjaxResult getInfo(@PathVariable("picklingId") String picklingId)
    {
        return AjaxResult.success(cysSxService.selectCysSxByPicklingId(picklingId));
    }

    /**
     * 新增酸洗
     */
    @PreAuthorize("@ss.hasPermi('md:sx:add')")
    @Log(title = "酸洗", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysSx cysSx)
    {
        return toAjax(cysSxService.insertCysSx(cysSx));
    }

    /**
     * 修改酸洗
     */
    @PreAuthorize("@ss.hasPermi('md:sx:edit')")
    @Log(title = "酸洗", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysSx cysSx)
    {
        return toAjax(cysSxService.updateCysSx(cysSx));
    }

    /**
     * 删除酸洗
     */
    @PreAuthorize("@ss.hasPermi('md:sx:remove')")
    @Log(title = "酸洗", businessType = BusinessType.DELETE)
	@DeleteMapping("/{picklingIds}")
    public AjaxResult remove(@PathVariable String[] picklingIds)
    {
        return toAjax(cysSxService.deleteCysSxByPicklingIds(picklingIds));
    }
}
