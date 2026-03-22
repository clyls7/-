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
import com.huaan.mes.md.domain.CysRezha;
import com.huaan.mes.md.service.ICysRezhaService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 热轧Controller
 * 
 * @author yinjinlu
 * @date 2025-04-29
 */
@RestController
@RequestMapping("/md/rezha")
public class CysRezhaController extends BaseController
{
    @Autowired
    private ICysRezhaService cysRezhaService;

    /**
     * 查询热轧列表
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysRezha cysRezha)
    {
        startPage();
        List<CysRezha> list = cysRezhaService.selectCysRezhaList(cysRezha);
        return getDataTable(list);
    }

    /**
     * 导出热轧列表
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:export')")
    @Log(title = "热轧", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysRezha cysRezha)
    {
        List<CysRezha> list = cysRezhaService.selectCysRezhaList(cysRezha);
        ExcelUtil<CysRezha> util = new ExcelUtil<CysRezha>(CysRezha.class);
        util.exportExcel(response, list, "热轧数据");
    }

    /**
     * 获取热轧详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:query')")
    @GetMapping(value = "/{hotrollId}")
    public AjaxResult getInfo(@PathVariable("hotrollId") String hotrollId)
    {
        return AjaxResult.success(cysRezhaService.selectCysRezhaByHotrollId(hotrollId));
    }

    /**
     * 新增热轧
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:add')")
    @Log(title = "热轧", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysRezha cysRezha)
    {
        return toAjax(cysRezhaService.insertCysRezha(cysRezha));
    }

    /**
     * 修改热轧
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:edit')")
    @Log(title = "热轧", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysRezha cysRezha)
    {
        return toAjax(cysRezhaService.updateCysRezha(cysRezha));
    }

    /**
     * 删除热轧
     */
    @PreAuthorize("@ss.hasPermi('md:rezha:remove')")
    @Log(title = "热轧", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hotrollIds}")
    public AjaxResult remove(@PathVariable String[] hotrollIds)
    {
        return toAjax(cysRezhaService.deleteCysRezhaByHotrollIds(hotrollIds));
    }
}
