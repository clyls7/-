package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysCbfx;

/**
 * 成本分析Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-27
 */
public interface CysCbfxMapper 
{
    /**
     * 查询成本分析
     * 
     * @param costaId 成本分析主键
     * @return 成本分析
     */
    public CysCbfx selectCysCbfxByCostaId(Long costaId);

    /**
     * 查询成本分析列表
     * 
     * @param cysCbfx 成本分析
     * @return 成本分析集合
     */
    public List<CysCbfx> selectCysCbfxList(CysCbfx cysCbfx);

    /**
     * 新增成本分析
     * 
     * @param cysCbfx 成本分析
     * @return 结果
     */
    public int insertCysCbfx(CysCbfx cysCbfx);

    /**
     * 修改成本分析
     * 
     * @param cysCbfx 成本分析
     * @return 结果
     */
    public int updateCysCbfx(CysCbfx cysCbfx);

    /**
     * 删除成本分析
     * 
     * @param costaId 成本分析主键
     * @return 结果
     */
    public int deleteCysCbfxByCostaId(Long costaId);

    /**
     * 批量删除成本分析
     * 
     * @param costaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysCbfxByCostaIds(Long[] costaIds);
}
