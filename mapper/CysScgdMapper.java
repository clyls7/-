package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysScgd;

/**
 * 生产工单Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-06-27
 */
public interface CysScgdMapper 
{
    /**
     * 查询生产工单
     * 
     * @param workId 生产工单主键
     * @return 生产工单
     */
    public CysScgd selectCysScgdByWorkId(Long workId);

    /**
     * 查询生产工单列表
     * 
     * @param cysScgd 生产工单
     * @return 生产工单集合
     */
    public List<CysScgd> selectCysScgdList(CysScgd cysScgd);

    /**
     * 新增生产工单
     * 
     * @param cysScgd 生产工单
     * @return 结果
     */
    public int insertCysScgd(CysScgd cysScgd);

    /**
     * 修改生产工单
     * 
     * @param cysScgd 生产工单
     * @return 结果
     */
    public int updateCysScgd(CysScgd cysScgd);

    /**
     * 删除生产工单
     * 
     * @param workId 生产工单主键
     * @return 结果
     */
    public int deleteCysScgdByWorkId(Long workId);

    /**
     * 批量删除生产工单
     * 
     * @param workIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysScgdByWorkIds(Long[] workIds);
}
