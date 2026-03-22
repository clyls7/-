package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysJjb;

/**
 * 精剪边Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
public interface CysJjbMapper 
{
    /**
     * 查询精剪边
     * 
     * @param trimId 精剪边主键
     * @return 精剪边
     */
    public CysJjb selectCysJjbByTrimId(String trimId);

    /**
     * 查询精剪边列表
     * 
     * @param cysJjb 精剪边
     * @return 精剪边集合
     */
    public List<CysJjb> selectCysJjbList(CysJjb cysJjb);

    /**
     * 新增精剪边
     * 
     * @param cysJjb 精剪边
     * @return 结果
     */
    public int insertCysJjb(CysJjb cysJjb);

    /**
     * 修改精剪边
     * 
     * @param cysJjb 精剪边
     * @return 结果
     */
    public int updateCysJjb(CysJjb cysJjb);

    /**
     * 删除精剪边
     * 
     * @param trimId 精剪边主键
     * @return 结果
     */
    public int deleteCysJjbByTrimId(String trimId);

    /**
     * 批量删除精剪边
     * 
     * @param trimIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysJjbByTrimIds(String[] trimIds);
}
