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
import com.huaan.mes.md.domain.CysCqp;
import com.huaan.mes.md.service.ICysCqpService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 粗切边Controller
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
@RestController
@RequestMapping("/md/cqp")
public class CysCqpController extends BaseController
{
    @Autowired
    private ICysCqpService cysCqpService;

    /**
     * 查询粗切边列表
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysCqp cysCqp)
    {
        startPage();
        List<CysCqp> list = cysCqpService.selectCysCqpList(cysCqp);
        return getDataTable(list);
    }

    /**
     * 导出粗切边列表
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:export')")
    @Log(title = "粗切边", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysCqp cysCqp)
    {
        List<CysCqp> list = cysCqpService.selectCysCqpList(cysCqp);
        ExcelUtil<CysCqp> util = new ExcelUtil<CysCqp>(CysCqp.class);
        util.exportExcel(response, list, "粗切边数据");
    }

    /**
     * 获取粗切边详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:query')")
    @GetMapping(value = "/{roughcutId}")
    public AjaxResult getInfo(@PathVariable("roughcutId") String roughcutId)
    {
        return AjaxResult.success(cysCqpService.selectCysCqpByRoughcutId(roughcutId));
    }

    /**
     * 新增粗切边
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:add')")
    @Log(title = "粗切边", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysCqp cysCqp)
    {
        return toAjax(cysCqpService.insertCysCqp(cysCqp));
    }

    /**
     * 修改粗切边
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:edit')")
    @Log(title = "粗切边", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysCqp cysCqp)
    {
        return toAjax(cysCqpService.updateCysCqp(cysCqp));
    }

    /**
     * 删除粗切边
     */
    @PreAuthorize("@ss.hasPermi('md:cqp:remove')")
    @Log(title = "粗切边", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roughcutIds}")
    public AjaxResult remove(@PathVariable String[] roughcutIds)
    {
        return toAjax(cysCqpService.deleteCysCqpByRoughcutIds(roughcutIds));
    }
}
