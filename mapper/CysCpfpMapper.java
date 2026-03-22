package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysCpfp;

/**
 * 产品分配Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-09-08
 */
public interface CysCpfpMapper 
{
    /**
     * 查询产品分配
     * 
     * @param paId 产品分配主键
     * @return 产品分配
     */
    public CysCpfp selectCysCpfpByPaId(String paId);

    /**
     * 查询产品分配列表
     * 
     * @param cysCpfp 产品分配
     * @return 产品分配集合
     */
    public List<CysCpfp> selectCysCpfpList(CysCpfp cysCpfp);

    /**
     * 新增产品分配
     * 
     * @param cysCpfp 产品分配
     * @return 结果
     */
    public int insertCysCpfp(CysCpfp cysCpfp);

    /**
     * 修改产品分配
     * 
     * @param cysCpfp 产品分配
     * @return 结果
     */
    public int updateCysCpfp(CysCpfp cysCpfp);

    /**
     * 删除产品分配
     * 
     * @param paId 产品分配主键
     * @return 结果
     */
    public int deleteCysCpfpByPaId(String paId);

    /**
     * 批量删除产品分配
     * 
     * @param paIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysCpfpByPaIds(String[] paIds);
}
