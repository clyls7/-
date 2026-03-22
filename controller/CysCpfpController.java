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
import com.huaan.mes.md.domain.CysCpfp;
import com.huaan.mes.md.service.ICysCpfpService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 产品分配Controller
 * 
 * @author yinjinlu
 * @date 2025-09-08
 */
@RestController
@RequestMapping("/md/cpfp")
public class CysCpfpController extends BaseController
{
    @Autowired
    private ICysCpfpService cysCpfpService;

    /**
     * 查询产品分配列表
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysCpfp cysCpfp)
    {
        startPage();
        List<CysCpfp> list = cysCpfpService.selectCysCpfpList(cysCpfp);
        return getDataTable(list);
    }

    /**
     * 导出产品分配列表
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:export')")
    @Log(title = "产品分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysCpfp cysCpfp)
    {
        List<CysCpfp> list = cysCpfpService.selectCysCpfpList(cysCpfp);
        ExcelUtil<CysCpfp> util = new ExcelUtil<CysCpfp>(CysCpfp.class);
        util.exportExcel(response, list, "产品分配数据");
    }

    /**
     * 获取产品分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:query')")
    @GetMapping(value = "/{paId}")
    public AjaxResult getInfo(@PathVariable("paId") String paId)
    {
        return AjaxResult.success(cysCpfpService.selectCysCpfpByPaId(paId));
    }

    /**
     * 新增产品分配
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:add')")
    @Log(title = "产品分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysCpfp cysCpfp)
    {
        return toAjax(cysCpfpService.insertCysCpfp(cysCpfp));
    }

    /**
     * 修改产品分配
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:edit')")
    @Log(title = "产品分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysCpfp cysCpfp)
    {
        return toAjax(cysCpfpService.updateCysCpfp(cysCpfp));
    }

    /**
     * 删除产品分配
     */
    @PreAuthorize("@ss.hasPermi('md:cpfp:remove')")
    @Log(title = "产品分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paIds}")
    public AjaxResult remove(@PathVariable String[] paIds)
    {
        return toAjax(cysCpfpService.deleteCysCpfpByPaIds(paIds));
    }
}
