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
import com.huaan.mes.md.domain.CysGz;
import com.huaan.mes.md.service.ICysGzService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 改制Controller
 * 
 * @author yinjinlu
 * @date 2025-05-12
 */
@RestController
@RequestMapping("/md/gz")
public class CysGzController extends BaseController
{
    @Autowired
    private ICysGzService cysGzService;

    /**
     * 查询改制列表
     */
    @PreAuthorize("@ss.hasPermi('md:gz:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysGz cysGz)
    {
        startPage();
        List<CysGz> list = cysGzService.selectCysGzList(cysGz);
        return getDataTable(list);
    }

    /**
     * 导出改制列表
     */
    @PreAuthorize("@ss.hasPermi('md:gz:export')")
    @Log(title = "改制", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysGz cysGz)
    {
        List<CysGz> list = cysGzService.selectCysGzList(cysGz);
        ExcelUtil<CysGz> util = new ExcelUtil<CysGz>(CysGz.class);
        util.exportExcel(response, list, "改制数据");
    }

    /**
     * 获取改制详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:gz:query')")
    @GetMapping(value = "/{gzId}")
    public AjaxResult getInfo(@PathVariable("gzId") String gzId)
    {
        return AjaxResult.success(cysGzService.selectCysGzByGzId(gzId));
    }

    /**
     * 新增改制
     */
    @PreAuthorize("@ss.hasPermi('md:gz:add')")
    @Log(title = "改制", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysGz cysGz)
    {
        return toAjax(cysGzService.insertCysGz(cysGz));
    }

    /**
     * 修改改制
     */
    @PreAuthorize("@ss.hasPermi('md:gz:edit')")
    @Log(title = "改制", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysGz cysGz)
    {
        return toAjax(cysGzService.updateCysGz(cysGz));
    }

    /**
     * 删除改制
     */
    @PreAuthorize("@ss.hasPermi('md:gz:remove')")
    @Log(title = "改制", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gzIds}")
    public AjaxResult remove(@PathVariable String[] gzIds)
    {
        return toAjax(cysGzService.deleteCysGzByGzIds(gzIds));
    }
}
