package com.huaan.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.mes.report.WipReport;
import com.ruoyi.system.service.mes.report.IWipReportService;

@RestController
@RequestMapping("/mes/report/wip")
public class WipReportController extends BaseController {
    @Autowired
    private IWipReportService wipReportService;

    @PreAuthorize("@ss.hasPermi('mes:report:wip:list')")
    @GetMapping("/list")
    public TableDataInfo list(WipReport wipReport) {
        startPage();
        List<WipReport> list = wipReportService.selectWipReportList(wipReport);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:report:wip:export')")
    @Log(title = "在制报表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, WipReport wipReport) {
        List<WipReport> list = wipReportService.selectWipReportList(wipReport);
        ExcelUtil<WipReport> util = new ExcelUtil<>(WipReport.class);
        util.exportExcel(response, list, "在制报表数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:report:wip:list')")
    @GetMapping("/process-rules")
    public AjaxResult processRules() {
        return AjaxResult.success("前端静态维护工序流转说明即可，如需后端化可迁移为字典/配置表");
    }
}
