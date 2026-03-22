package com.huaan.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.huaan.common.constant.UserConstants;
import com.huaan.mes.pro.domain.ProTask;
import com.huaan.mes.pro.domain.ProWorkorder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
import com.huaan.mes.md.domain.CysScdd;
import com.huaan.mes.md.service.ICysScddService;
import com.huaan.common.utils.poi.ExcelUtil;
import com.huaan.common.core.page.TableDataInfo;

/**
 * 生产订单Controller
 * 
 * @author yinjinlu
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/md/scdd")
public class CysScddController extends BaseController
{
    @Autowired
    private ICysScddService cysScddService;

    /**
     * 查询生产订单列表
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:list')")
    @GetMapping("/list")
    public TableDataInfo list(CysScdd cysScdd)
    {
        startPage();
        List<CysScdd> list = cysScddService.selectCysScddList(cysScdd);
        return getDataTable(list);
    }

    /**
     * 导出生产订单列表
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:export')")
    @Log(title = "生产订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CysScdd cysScdd)
    {
        List<CysScdd> list = cysScddService.selectCysScddList(cysScdd);
        ExcelUtil<CysScdd> util = new ExcelUtil<CysScdd>(CysScdd.class);
        util.exportExcel(response, list, "生产订单数据");
    }

    /**
     * 获取生产订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:query')")
    @GetMapping(value = "/{porderId}")
    public AjaxResult getInfo(@PathVariable("porderId") Long porderId)
    {
        return AjaxResult.success(cysScddService.selectCysScddByPorderId(porderId));
    }

    /**
     * 新增生产订单
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:add')")
    @Log(title = "生产订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CysScdd cysScdd)
    {
        return toAjax(cysScddService.insertCysScdd(cysScdd));
    }

    /**
     * 修改生产订单
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:edit')")
    @Log(title = "生产订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CysScdd cysScdd)
    {
        return toAjax(cysScddService.updateCysScdd(cysScdd));
    }

    /**
     * 删除生产订单
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:remove')")
    @Log(title = "生产订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{porderIds}")
    public AjaxResult remove(@PathVariable Long[] porderIds)
    {
        return toAjax(cysScddService.deleteCysScddByPorderIds(porderIds));
    }

    /**
     * 完成订单
     * @param porderId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('md:scdd:edit')")
    @Log(title = "生产订单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{porderId}")
    public AjaxResult dofinish(@PathVariable Long porderId){
        CysScdd cysScdd = cysScddService.selectCysScddByPorderId(porderId);
        cysScdd.setStatus(UserConstants.ORDER_STATUS_FINISHED); //更新订单的状态
        cysScddService.updateCysScdd(cysScdd);
        return AjaxResult.success();
    }
}
