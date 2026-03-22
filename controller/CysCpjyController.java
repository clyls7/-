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
import com.huaan.mes.md.domain.CysCpjy;
import com.huaan.mes.md.service.ICysCpjyService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 成品检验Controller
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/md/cpjy")
public class CysCpjyController extends BaseController
{
    @Autowired
    private ICysCpjyService cysCpjyService;

    /**
     * 查询成品检验列表
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysCpjy cysCpjy)
    {
        startPage();
        List<CysCpjy> list = cysCpjyService.selectCysCpjyList(cysCpjy);
        return getDataTable(list);
    }

    /**
     * 导出成品检验列表
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:export')")
    @Log(title = "成品检验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysCpjy cysCpjy)
    {
        List<CysCpjy> list = cysCpjyService.selectCysCpjyList(cysCpjy);
        ExcelUtil<CysCpjy> util = new ExcelUtil<CysCpjy>(CysCpjy.class);
        util.exportExcel(response, list, "成品检验数据");
    }

    /**
     * 获取成品检验详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:query')")
    @GetMapping(value = "/{fpiId}")
    public AjaxResult getInfo(@PathVariable("fpiId") String fpiId)
    {
        return AjaxResult.success(cysCpjyService.selectCysCpjyByFpiId(fpiId));
    }

    /**
     * 新增成品检验
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:add')")
    @Log(title = "成品检验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysCpjy cysCpjy)
    {
        return toAjax(cysCpjyService.insertCysCpjy(cysCpjy));
    }

    /**
     * 修改成品检验
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:edit')")
    @Log(title = "成品检验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysCpjy cysCpjy)
    {
        return toAjax(cysCpjyService.updateCysCpjy(cysCpjy));
    }

    /**
     * 删除成品检验
     */
    @PreAuthorize("@ss.hasPermi('md:cpjy:remove')")
    @Log(title = "成品检验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fpiIds}")
    public AjaxResult remove(@PathVariable String[] fpiIds)
    {
        return toAjax(cysCpjyService.deleteCysCpjyByFpiIds(fpiIds));
    }
}
