package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysSx;

/**
 * 酸洗Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public interface CysSxMapper 
{
    /**
     * 查询酸洗
     * 
     * @param picklingId 酸洗主键
     * @return 酸洗
     */
    public CysSx selectCysSxByPicklingId(String picklingId);

    /**
     * 查询酸洗列表
     * 
     * @param cysSx 酸洗
     * @return 酸洗集合
     */
    public List<CysSx> selectCysSxList(CysSx cysSx);

    /**
     * 新增酸洗
     * 
     * @param cysSx 酸洗
     * @return 结果
     */
    public int insertCysSx(CysSx cysSx);

    /**
     * 修改酸洗
     * 
     * @param cysSx 酸洗
     * @return 结果
     */
    public int updateCysSx(CysSx cysSx);

    /**
     * 删除酸洗
     * 
     * @param picklingId 酸洗主键
     * @return 结果
     */
    public int deleteCysSxByPicklingId(String picklingId);

    /**
     * 批量删除酸洗
     * 
     * @param picklingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysSxByPicklingIds(String[] picklingIds);
}
