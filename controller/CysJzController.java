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
import com.huaan.mes.md.domain.CysJz;
import com.huaan.mes.md.service.ICysJzService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 精轧Controller
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/md/jz")
public class CysJzController extends BaseController
{
    @Autowired
    private ICysJzService cysJzService;

    /**
     * 查询精轧列表
     */
    @PreAuthorize("@ss.hasPermi('md:jz:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysJz cysJz)
    {
        startPage();
        List<CysJz> list = cysJzService.selectCysJzList(cysJz);
        return getDataTable(list);
    }

    /**
     * 导出精轧列表
     */
    @PreAuthorize("@ss.hasPermi('md:jz:export')")
    @Log(title = "精轧", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysJz cysJz)
    {
        List<CysJz> list = cysJzService.selectCysJzList(cysJz);
        ExcelUtil<CysJz> util = new ExcelUtil<CysJz>(CysJz.class);
        util.exportExcel(response, list, "精轧数据");
    }

    /**
     * 获取精轧详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:jz:query')")
    @GetMapping(value = "/{finishrId}")
    public AjaxResult getInfo(@PathVariable("finishrId") String finishrId)
    {
        return AjaxResult.success(cysJzService.selectCysJzByFinishrId(finishrId));
    }

    /**
     * 新增精轧
     */
    @PreAuthorize("@ss.hasPermi('md:jz:add')")
    @Log(title = "精轧", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysJz cysJz)
    {
        return toAjax(cysJzService.insertCysJz(cysJz));
    }

    /**
     * 修改精轧
     */
    @PreAuthorize("@ss.hasPermi('md:jz:edit')")
    @Log(title = "精轧", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysJz cysJz)
    {
        return toAjax(cysJzService.updateCysJz(cysJz));
    }

    /**
     * 删除精轧
     */
    @PreAuthorize("@ss.hasPermi('md:jz:remove')")
    @Log(title = "精轧", businessType = BusinessType.DELETE)
	@DeleteMapping("/{finishrIds}")
    public AjaxResult remove(@PathVariable String[] finishrIds)
    {
        return toAjax(cysJzService.deleteCysJzByFinishrIds(finishrIds));
    }
}
