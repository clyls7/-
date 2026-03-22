package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysSmx;

/**
 * 铣面Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-04-30
 */
public interface CysSmxMapper 
{
    /**
     * 查询铣面
     * 
     * @param doublesidedId 铣面主键
     * @return 铣面
     */
    public CysSmx selectCysSmxByDoublesidedId(String doublesidedId);

    /**
     * 查询铣面列表
     * 
     * @param cysSmx 铣面
     * @return 铣面集合
     */
    public List<CysSmx> selectCysSmxList(CysSmx cysSmx);

    /**
     * 新增铣面
     * 
     * @param cysSmx 铣面
     * @return 结果
     */
    public int insertCysSmx(CysSmx cysSmx);

    /**
     * 修改铣面
     * 
     * @param cysSmx 铣面
     * @return 结果
     */
    public int updateCysSmx(CysSmx cysSmx);

    /**
     * 删除铣面
     * 
     * @param doublesidedId 铣面主键
     * @return 结果
     */
    public int deleteCysSmxByDoublesidedId(String doublesidedId);

    /**
     * 批量删除铣面
     * 
     * @param doublesidedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysSmxByDoublesidedIds(String[] doublesidedIds);
}
