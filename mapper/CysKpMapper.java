package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysKp;

/**
 * 开坯Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-05-04
 */
public interface CysKpMapper 
{
    /**
     * 查询开坯
     * 
     * @param doublesidedId 开坯主键
     * @return 开坯
     */
    public CysKp selectCysKpByDoublesidedId(String doublesidedId);

    /**
     * 查询开坯列表
     * 
     * @param cysKp 开坯
     * @return 开坯集合
     */
    public List<CysKp> selectCysKpList(CysKp cysKp);

    /**
     * 新增开坯
     * 
     * @param cysKp 开坯
     * @return 结果
     */
    public int insertCysKp(CysKp cysKp);

    /**
     * 修改开坯
     * 
     * @param cysKp 开坯
     * @return 结果
     */
    public int updateCysKp(CysKp cysKp);

    /**
     * 删除开坯
     * 
     * @param doublesidedId 开坯主键
     * @return 结果
     */
    public int deleteCysKpByDoublesidedId(String doublesidedId);

    /**
     * 批量删除开坯
     * 
     * @param doublesidedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysKpByDoublesidedIds(String[] doublesidedIds);
}
