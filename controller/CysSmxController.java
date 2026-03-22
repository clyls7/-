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
import com.huaan.mes.md.domain.CysSmx;
import com.huaan.mes.md.service.ICysSmxService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 铣面Controller
 * 
 * @author yinjinlu
 * @date 2025-04-30
 */
@RestController
@RequestMapping("/md/smx")
public class CysSmxController extends BaseController
{
    @Autowired
    private ICysSmxService cysSmxService;

    /**
     * 查询铣面列表
     */
    @PreAuthorize("@ss.hasPermi('md:smx:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysSmx cysSmx)
    {
        startPage();
        List<CysSmx> list = cysSmxService.selectCysSmxList(cysSmx);
        return getDataTable(list);
    }

    /**
     * 导出铣面列表
     */
    @PreAuthorize("@ss.hasPermi('md:smx:export')")
    @Log(title = "铣面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysSmx cysSmx)
    {
        List<CysSmx> list = cysSmxService.selectCysSmxList(cysSmx);
        ExcelUtil<CysSmx> util = new ExcelUtil<CysSmx>(CysSmx.class);
        util.exportExcel(response, list, "铣面数据");
    }

    /**
     * 获取铣面详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:smx:query')")
    @GetMapping(value = "/{doublesidedId}")
    public AjaxResult getInfo(@PathVariable("doublesidedId") String doublesidedId)
    {
        return AjaxResult.success(cysSmxService.selectCysSmxByDoublesidedId(doublesidedId));
    }

    /**
     * 新增铣面
     */
    @PreAuthorize("@ss.hasPermi('md:smx:add')")
    @Log(title = "铣面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysSmx cysSmx)
    {
        return toAjax(cysSmxService.insertCysSmx(cysSmx));
    }

    /**
     * 修改铣面
     */
    @PreAuthorize("@ss.hasPermi('md:smx:edit')")
    @Log(title = "铣面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysSmx cysSmx)
    {
        return toAjax(cysSmxService.updateCysSmx(cysSmx));
    }

    /**
     * 删除铣面
     */
    @PreAuthorize("@ss.hasPermi('md:smx:remove')")
    @Log(title = "铣面", businessType = BusinessType.DELETE)
	@DeleteMapping("/{doublesidedIds}")
    public AjaxResult remove(@PathVariable String[] doublesidedIds)
    {
        return toAjax(cysSmxService.deleteCysSmxByDoublesidedIds(doublesidedIds));
    }
}
