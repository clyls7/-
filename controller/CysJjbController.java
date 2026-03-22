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
import com.huaan.mes.md.domain.CysJjb;
import com.huaan.mes.md.service.ICysJjbService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 精剪边Controller
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
@RestController
@RequestMapping("/md/jjb")
public class CysJjbController extends BaseController
{
    @Autowired
    private ICysJjbService cysJjbService;

    /**
     * 查询精剪边列表
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysJjb cysJjb)
    {
        startPage();
        List<CysJjb> list = cysJjbService.selectCysJjbList(cysJjb);
        return getDataTable(list);
    }

    /**
     * 导出精剪边列表
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:export')")
    @Log(title = "精剪边", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysJjb cysJjb)
    {
        List<CysJjb> list = cysJjbService.selectCysJjbList(cysJjb);
        ExcelUtil<CysJjb> util = new ExcelUtil<CysJjb>(CysJjb.class);
        util.exportExcel(response, list, "精剪边数据");
    }

    /**
     * 获取精剪边详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:query')")
    @GetMapping(value = "/{trimId}")
    public AjaxResult getInfo(@PathVariable("trimId") String trimId)
    {
        return AjaxResult.success(cysJjbService.selectCysJjbByTrimId(trimId));
    }

    /**
     * 新增精剪边
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:add')")
    @Log(title = "精剪边", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysJjb cysJjb)
    {
        return toAjax(cysJjbService.insertCysJjb(cysJjb));
    }

    /**
     * 修改精剪边
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:edit')")
    @Log(title = "精剪边", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysJjb cysJjb)
    {
        return toAjax(cysJjbService.updateCysJjb(cysJjb));
    }

    /**
     * 删除精剪边
     */
    @PreAuthorize("@ss.hasPermi('md:jjb:remove')")
    @Log(title = "精剪边", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trimIds}")
    public AjaxResult remove(@PathVariable String[] trimIds)
    {
        return toAjax(cysJjbService.deleteCysJjbByTrimIds(trimIds));
    }
}
