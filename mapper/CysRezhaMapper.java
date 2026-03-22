package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysRezha;

/**
 * 热轧Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-29
 */
public interface CysRezhaMapper 
{
    /**
     * 查询热轧
     * 
     * @param hotrollId 热轧主键
     * @return 热轧
     */
    public CysRezha selectCysRezhaByHotrollId(String hotrollId);

    /**
     * 查询热轧列表
     * 
     * @param cysRezha 热轧
     * @return 热轧集合
     */
    public List<CysRezha> selectCysRezhaList(CysRezha cysRezha);

    /**
     * 新增热轧
     * 
     * @param cysRezha 热轧
     * @return 结果
     */
    public int insertCysRezha(CysRezha cysRezha);

    /**
     * 修改热轧
     * 
     * @param cysRezha 热轧
     * @return 结果
     */
    public int updateCysRezha(CysRezha cysRezha);

    /**
     * 删除热轧
     * 
     * @param hotrollId 热轧主键
     * @return 结果
     */
    public int deleteCysRezhaByHotrollId(String hotrollId);

    /**
     * 批量删除热轧
     * 
     * @param hotrollIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysRezhaByHotrollIds(String[] hotrollIds);
}
