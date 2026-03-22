package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysFq;

/**
 * 分切Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public interface CysFqMapper 
{
    /**
     * 查询分切
     * 
     * @param fqId 分切主键
     * @return 分切
     */
    public CysFq selectCysFqByFqId(String fqId);

    /**
     * 查询分切列表
     * 
     * @param cysFq 分切
     * @return 分切集合
     */
    public List<CysFq> selectCysFqList(CysFq cysFq);

    /**
     * 新增分切
     * 
     * @param cysFq 分切
     * @return 结果
     */
    public int insertCysFq(CysFq cysFq);

    /**
     * 修改分切
     * 
     * @param cysFq 分切
     * @return 结果
     */
    public int updateCysFq(CysFq cysFq);

    /**
     * 删除分切
     * 
     * @param fqId 分切主键
     * @return 结果
     */
    public int deleteCysFqByFqId(String fqId);

    /**
     * 批量删除分切
     * 
     * @param fqIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysFqByFqIds(String[] fqIds);
}
