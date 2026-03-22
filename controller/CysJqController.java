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
import com.huaan.mes.md.domain.CysJq;
import com.huaan.mes.md.service.ICysJqService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 锯切Controller
 * 
 * @author yinjinlu
 * @date 2025-03-24
 */
@RestController
@RequestMapping("/md/jq")
public class CysJqController extends BaseController
{
    @Autowired
    private ICysJqService cysJqService;

    /**
     * 查询锯切列表
     */
    @PreAuthorize("@ss.hasPermi('md:jq:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysJq cysJq)
    {
        startPage();
        List<CysJq> list = cysJqService.selectCysJqList(cysJq);
        return getDataTable(list);
    }

    /**
     * 导出锯切列表
     */
    @PreAuthorize("@ss.hasPermi('md:jq:export')")
    @Log(title = "锯切", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysJq cysJq)
    {
        List<CysJq> list = cysJqService.selectCysJqList(cysJq);
        ExcelUtil<CysJq> util = new ExcelUtil<CysJq>(CysJq.class);
        util.exportExcel(response, list, "锯切数据");
    }

    /**
     * 获取锯切详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:jq:query')")
    @GetMapping(value = "/{sawingId}")
    public AjaxResult getInfo(@PathVariable("sawingId") String sawingId)
    {
        return AjaxResult.success(cysJqService.selectCysJqBySawingId(sawingId));
    }

    /**
     * 新增锯切
     */
    @PreAuthorize("@ss.hasPermi('md:jq:add')")
    @Log(title = "锯切", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysJq cysJq)
    {
        return toAjax(cysJqService.insertCysJq(cysJq));
    }

    /**
     * 修改锯切
     */
    @PreAuthorize("@ss.hasPermi('md:jq:edit')")
    @Log(title = "锯切", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysJq cysJq)
    {
        return toAjax(cysJqService.updateCysJq(cysJq));
    }

    /**
     * 删除锯切
     */
    @PreAuthorize("@ss.hasPermi('md:jq:remove')")
    @Log(title = "锯切", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sawingIds}")
    public AjaxResult remove(@PathVariable String[] sawingIds)
    {
        return toAjax(cysJqService.deleteCysJqBySawingIds(sawingIds));
    }
}
