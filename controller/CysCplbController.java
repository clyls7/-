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
import com.huaan.mes.md.domain.CysCplb;
import com.huaan.mes.md.service.ICysCplbService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 成品列Controller
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/md/cplb")
public class CysCplbController extends BaseController
{
    @Autowired
    private ICysCplbService cysCplbService;

    /**
     * 查询成品列列表
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysCplb cysCplb)
    {
        startPage();
        List<CysCplb> list = cysCplbService.selectCysCplbList(cysCplb);
        return getDataTable(list);
    }

    /**
     * 导出成品列列表
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:export')")
    @Log(title = "成品列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysCplb cysCplb)
    {
        List<CysCplb> list = cysCplbService.selectCysCplbList(cysCplb);
        ExcelUtil<CysCplb> util = new ExcelUtil<CysCplb>(CysCplb.class);
        util.exportExcel(response, list, "成品列数据");
    }

    /**
     * 获取成品列详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:query')")
    @GetMapping(value = "/{cplbId}")
    public AjaxResult getInfo(@PathVariable("cplbId") String cplbId)
    {
        return AjaxResult.success(cysCplbService.selectCysCplbByCplbId(cplbId));
    }

    /**
     * 新增成品列
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:add')")
    @Log(title = "成品列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysCplb cysCplb)
    {
        return toAjax(cysCplbService.insertCysCplb(cysCplb));
    }

    /**
     * 修改成品列
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:edit')")
    @Log(title = "成品列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysCplb cysCplb)
    {
        return toAjax(cysCplbService.updateCysCplb(cysCplb));
    }

    /**
     * 删除成品列
     */
    @PreAuthorize("@ss.hasPermi('md:cplb:remove')")
    @Log(title = "成品列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cplbIds}")
    public AjaxResult remove(@PathVariable String[] cplbIds)
    {
        return toAjax(cysCplbService.deleteCysCplbByCplbIds(cplbIds));
    }
}
