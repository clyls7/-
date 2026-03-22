package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysCplb;

/**
 * 成品列Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public interface CysCplbMapper 
{
    /**
     * 查询成品列
     * 
     * @param cplbId 成品列主键
     * @return 成品列
     */
    public CysCplb selectCysCplbByCplbId(String cplbId);

    /**
     * 查询成品列列表
     * 
     * @param cysCplb 成品列
     * @return 成品列集合
     */
    public List<CysCplb> selectCysCplbList(CysCplb cysCplb);

    /**
     * 新增成品列
     * 
     * @param cysCplb 成品列
     * @return 结果
     */
    public int insertCysCplb(CysCplb cysCplb);

    /**
     * 修改成品列
     * 
     * @param cysCplb 成品列
     * @return 结果
     */
    public int updateCysCplb(CysCplb cysCplb);

    /**
     * 删除成品列
     * 
     * @param cplbId 成品列主键
     * @return 结果
     */
    public int deleteCysCplbByCplbId(String cplbId);

    /**
     * 批量删除成品列
     * 
     * @param cplbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysCplbByCplbIds(String[] cplbIds);
}
