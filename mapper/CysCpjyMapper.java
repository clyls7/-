package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysCpjy;

/**
 * 成品检验Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public interface CysCpjyMapper 
{
    /**
     * 查询成品检验
     * 
     * @param fpiId 成品检验主键
     * @return 成品检验
     */
    public CysCpjy selectCysCpjyByFpiId(String fpiId);

    /**
     * 查询成品检验列表
     * 
     * @param cysCpjy 成品检验
     * @return 成品检验集合
     */
    public List<CysCpjy> selectCysCpjyList(CysCpjy cysCpjy);

    /**
     * 新增成品检验
     * 
     * @param cysCpjy 成品检验
     * @return 结果
     */
    public int insertCysCpjy(CysCpjy cysCpjy);

    /**
     * 修改成品检验
     * 
     * @param cysCpjy 成品检验
     * @return 结果
     */
    public int updateCysCpjy(CysCpjy cysCpjy);

    /**
     * 删除成品检验
     * 
     * @param fpiId 成品检验主键
     * @return 结果
     */
    public int deleteCysCpjyByFpiId(String fpiId);

    /**
     * 批量删除成品检验
     * 
     * @param fpiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysCpjyByFpiIds(String[] fpiIds);
}
