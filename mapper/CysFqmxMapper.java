package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysFqmx;

/**
 * 分切明细Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public interface CysFqmxMapper 
{
    /**
     * 查询分切明细
     * 
     * @param fqmxId 分切明细主键
     * @return 分切明细
     */
    public CysFqmx selectCysFqmxByFqmxId(String fqmxId);

    /**
     * 查询分切明细列表
     * 
     * @param cysFqmx 分切明细
     * @return 分切明细集合
     */
    public List<CysFqmx> selectCysFqmxList(CysFqmx cysFqmx);

    /**
     * 新增分切明细
     * 
     * @param cysFqmx 分切明细
     * @return 结果
     */
    public int insertCysFqmx(CysFqmx cysFqmx);

    /**
     * 修改分切明细
     * 
     * @param cysFqmx 分切明细
     * @return 结果
     */
    public int updateCysFqmx(CysFqmx cysFqmx);

    /**
     * 删除分切明细
     * 
     * @param fqmxId 分切明细主键
     * @return 结果
     */
    public int deleteCysFqmxByFqmxId(String fqmxId);

    /**
     * 批量删除分切明细
     * 
     * @param fqmxIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysFqmxByFqmxIds(String[] fqmxIds);
}
