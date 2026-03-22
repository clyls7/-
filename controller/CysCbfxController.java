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
import com.huaan.mes.md.domain.CysCbfx;
import com.huaan.mes.md.service.ICysCbfxService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 成本分析Controller
 * 
 * @author yinjinlu
 * @date 2025-04-27
 */
@RestController
@RequestMapping("/md/cbfx")
public class CysCbfxController extends BaseController
{
    @Autowired
    private ICysCbfxService cysCbfxService;

    /**
     * 查询成本分析列表
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysCbfx cysCbfx)
    {
        startPage();
        List<CysCbfx> list = cysCbfxService.selectCysCbfxList(cysCbfx);
        return getDataTable(list);
    }

    /**
     * 导出成本分析列表
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:export')")
    @Log(title = "成本分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysCbfx cysCbfx)
    {
        List<CysCbfx> list = cysCbfxService.selectCysCbfxList(cysCbfx);
        ExcelUtil<CysCbfx> util = new ExcelUtil<CysCbfx>(CysCbfx.class);
        util.exportExcel(response, list, "成本分析数据");
    }

    /**
     * 获取成本分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:query')")
    @GetMapping(value = "/{costaId}")
    public AjaxResult getInfo(@PathVariable("costaId") Long costaId)
    {
        return AjaxResult.success(cysCbfxService.selectCysCbfxByCostaId(costaId));
    }

    /**
     * 新增成本分析
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:add')")
    @Log(title = "成本分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysCbfx cysCbfx)
    {
        return toAjax(cysCbfxService.insertCysCbfx(cysCbfx));
    }

    /**
     * 修改成本分析
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:edit')")
    @Log(title = "成本分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysCbfx cysCbfx)
    {
        return toAjax(cysCbfxService.updateCysCbfx(cysCbfx));
    }

    /**
     * 删除成本分析
     */
    @PreAuthorize("@ss.hasPermi('md:cbfx:remove')")
    @Log(title = "成本分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{costaIds}")
    public AjaxResult remove(@PathVariable Long[] costaIds)
    {
        return toAjax(cysCbfxService.deleteCysCbfxByCostaIds(costaIds));
    }
}
