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
import com.huaan.mes.md.domain.CysRz;
import com.huaan.mes.md.service.ICysRzService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 熔铸Controller
 * 
 * @author yinjinlu
 * @date 2025-06-17
 */
@RestController
@RequestMapping("/md/rz")
public class CysRzController extends BaseController
{
    @Autowired
    private ICysRzService cysRzService;

    /**
     * 查询熔铸列表
     */
    @PreAuthorize("@ss.hasPermi('md:rz:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysRz cysRz)
    {
        startPage();
        List<CysRz> list = cysRzService.selectCysRzList(cysRz);
        return getDataTable(list);
    }

    /**
     * 导出熔铸列表
     */
    @PreAuthorize("@ss.hasPermi('md:rz:export')")
    @Log(title = "熔铸", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysRz cysRz)
    {
        List<CysRz> list = cysRzService.selectCysRzList(cysRz);
        ExcelUtil<CysRz> util = new ExcelUtil<CysRz>(CysRz.class);
        util.exportExcel(response, list, "熔铸数据");
    }

    /**
     * 获取熔铸详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:rz:query')")
    @GetMapping(value = "/{casId}")
    public AjaxResult getInfo(@PathVariable("casId") String casId)
    {
        return AjaxResult.success(cysRzService.selectCysRzByCasId(casId));
    }

    /**
     * 新增熔铸
     */
    @PreAuthorize("@ss.hasPermi('md:rz:add')")
    @Log(title = "熔铸", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysRz cysRz)
    {
        return toAjax(cysRzService.insertCysRz(cysRz));
    }

    /**
     * 修改熔铸
     */
    @PreAuthorize("@ss.hasPermi('md:rz:edit')")
    @Log(title = "熔铸", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysRz cysRz)
    {
        return toAjax(cysRzService.updateCysRz(cysRz));
    }

    /**
     * 删除熔铸
     */
    @PreAuthorize("@ss.hasPermi('md:rz:remove')")
    @Log(title = "熔铸", businessType = BusinessType.DELETE)
	@DeleteMapping("/{casIds}")
    public AjaxResult remove(@PathVariable String[] casIds)
    {
        return toAjax(cysRzService.deleteCysRzByCasIds(casIds));
    }
}
