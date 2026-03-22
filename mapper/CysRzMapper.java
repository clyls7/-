package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysRz;

/**
 * 熔铸Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-06-17
 */
public interface CysRzMapper 
{
    /**
     * 查询熔铸
     * 
     * @param casId 熔铸主键
     * @return 熔铸
     */
    public CysRz selectCysRzByCasId(String casId);

    /**
     * 查询熔铸列表
     * 
     * @param cysRz 熔铸
     * @return 熔铸集合
     */
    public List<CysRz> selectCysRzList(CysRz cysRz);

    /**
     * 新增熔铸
     * 
     * @param cysRz 熔铸
     * @return 结果
     */
    public int insertCysRz(CysRz cysRz);

    /**
     * 修改熔铸
     * 
     * @param cysRz 熔铸
     * @return 结果
     */
    public int updateCysRz(CysRz cysRz);

    /**
     * 删除熔铸
     * 
     * @param casId 熔铸主键
     * @return 结果
     */
    public int deleteCysRzByCasId(String casId);

    /**
     * 批量删除熔铸
     * 
     * @param casIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysRzByCasIds(String[] casIds);
}
