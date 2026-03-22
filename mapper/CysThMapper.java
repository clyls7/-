package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysTh;

/**
 * 退火Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-01-16
 */
public interface CysThMapper 
{
    /**
     * 查询退火
     * 
     * @param annealingId 退火主键
     * @return 退火
     */
    public CysTh selectCysThByAnnealingId(String annealingId);

    /**
     * 查询退火列表
     * 
     * @param cysTh 退火
     * @return 退火集合
     */
    public List<CysTh> selectCysThList(CysTh cysTh);

    /**
     * 新增退火
     * 
     * @param cysTh 退火
     * @return 结果
     */
    public int insertCysTh(CysTh cysTh);

    /**
     * 修改退火
     * 
     * @param cysTh 退火
     * @return 结果
     */
    public int updateCysTh(CysTh cysTh);

    /**
     * 删除退火
     * 
     * @param annealingId 退火主键
     * @return 结果
     */
    public int deleteCysThByAnnealingId(String annealingId);

    /**
     * 批量删除退火
     * 
     * @param annealingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysThByAnnealingIds(String[] annealingIds);
}
