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
import com.huaan.mes.md.domain.CysKp;
import com.huaan.mes.md.service.ICysKpService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 开坯Controller
 * 
 * @author yinjinlu
 * @date 2025-05-04
 */
@RestController
@RequestMapping("/md/kp")
public class CysKpController extends BaseController
{
    @Autowired
    private ICysKpService cysKpService;

    /**
     * 查询开坯列表
     */
    @PreAuthorize("@ss.hasPermi('md:kp:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysKp cysKp)
    {
        startPage();
        List<CysKp> list = cysKpService.selectCysKpList(cysKp);
        return getDataTable(list);
    }

    /**
     * 导出开坯列表
     */
    @PreAuthorize("@ss.hasPermi('md:kp:export')")
    @Log(title = "开坯", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysKp cysKp)
    {
        List<CysKp> list = cysKpService.selectCysKpList(cysKp);
        ExcelUtil<CysKp> util = new ExcelUtil<CysKp>(CysKp.class);
        util.exportExcel(response, list, "开坯数据");
    }

    /**
     * 获取开坯详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:kp:query')")
    @GetMapping(value = "/{doublesidedId}")
    public AjaxResult getInfo(@PathVariable("doublesidedId") String doublesidedId)
    {
        return AjaxResult.success(cysKpService.selectCysKpByDoublesidedId(doublesidedId));
    }

    /**
     * 新增开坯
     */
    @PreAuthorize("@ss.hasPermi('md:kp:add')")
    @Log(title = "开坯", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysKp cysKp)
    {
        return toAjax(cysKpService.insertCysKp(cysKp));
    }

    /**
     * 修改开坯
     */
    @PreAuthorize("@ss.hasPermi('md:kp:edit')")
    @Log(title = "开坯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysKp cysKp)
    {
        return toAjax(cysKpService.updateCysKp(cysKp));
    }

    /**
     * 删除开坯
     */
    @PreAuthorize("@ss.hasPermi('md:kp:remove')")
    @Log(title = "开坯", businessType = BusinessType.DELETE)
	@DeleteMapping("/{doublesidedIds}")
    public AjaxResult remove(@PathVariable String[] doublesidedIds)
    {
        return toAjax(cysKpService.deleteCysKpByDoublesidedIds(doublesidedIds));
    }
}
