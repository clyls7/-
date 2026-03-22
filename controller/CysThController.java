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
import com.huaan.mes.md.domain.CysTh;
import com.huaan.mes.md.service.ICysThService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 退火Controller
 * 
 * @author yinjinlu
 * @date 2025-01-16
 */
@RestController
@RequestMapping("/md/th")
public class CysThController extends BaseController
{
    @Autowired
    private ICysThService cysThService;

    /**
     * 查询退火列表
     */
    @PreAuthorize("@ss.hasPermi('md:th:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysTh cysTh)
    {
        startPage();
        List<CysTh> list = cysThService.selectCysThList(cysTh);
        return getDataTable(list);
    }

    /**
     * 导出退火列表
     */
    @PreAuthorize("@ss.hasPermi('md:th:export')")
    @Log(title = "退火", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysTh cysTh)
    {
        List<CysTh> list = cysThService.selectCysThList(cysTh);
        ExcelUtil<CysTh> util = new ExcelUtil<CysTh>(CysTh.class);
        util.exportExcel(response, list, "退火数据");
    }

    /**
     * 获取退火详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:th:query')")
    @GetMapping(value = "/{annealingId}")
    public AjaxResult getInfo(@PathVariable("annealingId") String annealingId)
    {
        return AjaxResult.success(cysThService.selectCysThByAnnealingId(annealingId));
    }

    /**
     * 新增退火
     */
    @PreAuthorize("@ss.hasPermi('md:th:add')")
    @Log(title = "退火", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysTh cysTh)
    {
        return toAjax(cysThService.insertCysTh(cysTh));
    }

    /**
     * 修改退火
     */
    @PreAuthorize("@ss.hasPermi('md:th:edit')")
    @Log(title = "退火", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysTh cysTh)
    {
        return toAjax(cysThService.updateCysTh(cysTh));
    }

    /**
     * 删除退火
     */
    @PreAuthorize("@ss.hasPermi('md:th:remove')")
    @Log(title = "退火", businessType = BusinessType.DELETE)
	@DeleteMapping("/{annealingIds}")
    public AjaxResult remove(@PathVariable String[] annealingIds)
    {
        return toAjax(cysThService.deleteCysThByAnnealingIds(annealingIds));
    }
}
