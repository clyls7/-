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
import com.huaan.mes.md.domain.CysFq;
import com.huaan.mes.md.service.ICysFqService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 分切Controller
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
@RestController
@RequestMapping("/md/fq")
public class CysFqController extends BaseController
{
    @Autowired
    private ICysFqService cysFqService;

    /**
     * 查询分切列表
     */
    @PreAuthorize("@ss.hasPermi('md:fq:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysFq cysFq)
    {
        startPage();
        List<CysFq> list = cysFqService.selectCysFqList(cysFq);
        return getDataTable(list);
    }

    /**
     * 导出分切列表
     */
    @PreAuthorize("@ss.hasPermi('md:fq:export')")
    @Log(title = "分切", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysFq cysFq)
    {
        List<CysFq> list = cysFqService.selectCysFqList(cysFq);
        ExcelUtil<CysFq> util = new ExcelUtil<CysFq>(CysFq.class);
        util.exportExcel(response, list, "分切数据");
    }

    /**
     * 获取分切详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:fq:query')")
    @GetMapping(value = "/{fqId}")
    public AjaxResult getInfo(@PathVariable("fqId") String fqId)
    {
        return AjaxResult.success(cysFqService.selectCysFqByFqId(fqId));
    }

    /**
     * 新增分切
     */
    @PreAuthorize("@ss.hasPermi('md:fq:add')")
    @Log(title = "分切", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysFq cysFq)
    {
        return toAjax(cysFqService.insertCysFq(cysFq));
    }

    /**
     * 修改分切
     */
    @PreAuthorize("@ss.hasPermi('md:fq:edit')")
    @Log(title = "分切", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysFq cysFq)
    {
        return toAjax(cysFqService.updateCysFq(cysFq));
    }

    /**
     * 删除分切
     */
    @PreAuthorize("@ss.hasPermi('md:fq:remove')")
    @Log(title = "分切", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fqIds}")
    public AjaxResult remove(@PathVariable String[] fqIds)
    {
        return toAjax(cysFqService.deleteCysFqByFqIds(fqIds));
    }
}
