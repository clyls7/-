package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysJz;

/**
 * 精轧Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public interface CysJzMapper 
{
    /**
     * 查询精轧
     * 
     * @param finishrId 精轧主键
     * @return 精轧
     */
    public CysJz selectCysJzByFinishrId(String finishrId);

    /**
     * 查询精轧列表
     * 
     * @param cysJz 精轧
     * @return 精轧集合
     */
    public List<CysJz> selectCysJzList(CysJz cysJz);

    /**
     * 新增精轧
     * 
     * @param cysJz 精轧
     * @return 结果
     */
    public int insertCysJz(CysJz cysJz);

    /**
     * 修改精轧
     * 
     * @param cysJz 精轧
     * @return 结果
     */
    public int updateCysJz(CysJz cysJz);

    /**
     * 删除精轧
     * 
     * @param finishrId 精轧主键
     * @return 结果
     */
    public int deleteCysJzByFinishrId(String finishrId);

    /**
     * 批量删除精轧
     * 
     * @param finishrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysJzByFinishrIds(String[] finishrIds);
}
