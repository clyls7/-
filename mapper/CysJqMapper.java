package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysJq;

/**
 * 锯切Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-03-24
 */
public interface CysJqMapper 
{
    /**
     * 查询锯切
     * 
     * @param sawingId 锯切主键
     * @return 锯切
     */
    public CysJq selectCysJqBySawingId(String sawingId);

    /**
     * 查询锯切列表
     * 
     * @param cysJq 锯切
     * @return 锯切集合
     */
    public List<CysJq> selectCysJqList(CysJq cysJq);

    /**
     * 新增锯切
     * 
     * @param cysJq 锯切
     * @return 结果
     */
    public int insertCysJq(CysJq cysJq);

    /**
     * 修改锯切
     * 
     * @param cysJq 锯切
     * @return 结果
     */
    public int updateCysJq(CysJq cysJq);

    /**
     * 删除锯切
     * 
     * @param sawingId 锯切主键
     * @return 结果
     */
    public int deleteCysJqBySawingId(String sawingId);

    /**
     * 批量删除锯切
     * 
     * @param sawingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysJqBySawingIds(String[] sawingIds);
}
