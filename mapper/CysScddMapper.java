package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysScdd;

/**
 * 生产订单Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-01-20
 */
public interface CysScddMapper 
{
    /**
     * 查询生产订单
     * 
     * @param porderId 生产订单主键
     * @return 生产订单
     */
    public CysScdd selectCysScddByPorderId(Long porderId);

    /**
     * 查询生产订单列表
     * 
     * @param cysScdd 生产订单
     * @return 生产订单集合
     */
    public List<CysScdd> selectCysScddList(CysScdd cysScdd);

    /**
     * 新增生产订单
     * 
     * @param cysScdd 生产订单
     * @return 结果
     */
    public int insertCysScdd(CysScdd cysScdd);

    /**
     * 修改生产订单
     * 
     * @param cysScdd 生产订单
     * @return 结果
     */
    public int updateCysScdd(CysScdd cysScdd);

    /**
     * 删除生产订单
     * 
     * @param porderId 生产订单主键
     * @return 结果
     */
    public int deleteCysScddByPorderId(Long porderId);

    /**
     * 批量删除生产订单
     * 
     * @param porderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysScddByPorderIds(Long[] porderIds);
}
