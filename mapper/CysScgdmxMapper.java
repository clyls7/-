package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysScgdmx;

/**
 * 生产工单明细Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-06-27
 */
public interface CysScgdmxMapper 
{
    /**
     * 查询生产工单明细
     * 
     * @param workId 生产工单明细主键
     * @return 生产工单明细
     */
    public CysScgdmx selectCysScgdmxByWorkId(Long workId);

    /**
     * 查询生产工单明细列表
     * 
     * @param cysScgdmx 生产工单明细
     * @return 生产工单明细集合
     */
    public List<CysScgdmx> selectCysScgdmxList(CysScgdmx cysScgdmx);

    /**
     * 新增生产工单明细
     * 
     * @param cysScgdmx 生产工单明细
     * @return 结果
     */
    public int insertCysScgdmx(CysScgdmx cysScgdmx);

    /**
     * 修改生产工单明细
     * 
     * @param cysScgdmx 生产工单明细
     * @return 结果
     */
    public int updateCysScgdmx(CysScgdmx cysScgdmx);

    /**
     * 删除生产工单明细
     * 
     * @param workId 生产工单明细主键
     * @return 结果
     */
    public int deleteCysScgdmxByWorkId(Long workId);

    /**
     * 批量删除生产工单明细
     * 
     * @param workIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysScgdmxByWorkIds(Long[] workIds);
}
