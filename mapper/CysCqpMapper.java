package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysCqp;

/**
 * 粗切边Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
public interface CysCqpMapper 
{
    /**
     * 查询粗切边
     * 
     * @param roughcutId 粗切边主键
     * @return 粗切边
     */
    public CysCqp selectCysCqpByRoughcutId(String roughcutId);

    /**
     * 查询粗切边列表
     * 
     * @param cysCqp 粗切边
     * @return 粗切边集合
     */
    public List<CysCqp> selectCysCqpList(CysCqp cysCqp);

    /**
     * 新增粗切边
     * 
     * @param cysCqp 粗切边
     * @return 结果
     */
    public int insertCysCqp(CysCqp cysCqp);

    /**
     * 修改粗切边
     * 
     * @param cysCqp 粗切边
     * @return 结果
     */
    public int updateCysCqp(CysCqp cysCqp);

    /**
     * 删除粗切边
     * 
     * @param roughcutId 粗切边主键
     * @return 结果
     */
    public int deleteCysCqpByRoughcutId(String roughcutId);

    /**
     * 批量删除粗切边
     * 
     * @param roughcutIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysCqpByRoughcutIds(String[] roughcutIds);
}
