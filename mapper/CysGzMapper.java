package com.huaan.mes.md.mapper;

import java.util.List;
import com.huaan.mes.md.domain.CysGz;

/**
 * 改制Mapper接口
 * 
 * @author yinjinlu
 * @date 2025-05-12
 */
public interface CysGzMapper 
{
    /**
     * 查询改制
     * 
     * @param gzId 改制主键
     * @return 改制
     */
    public CysGz selectCysGzByGzId(String gzId);

    /**
     * 查询改制列表
     * 
     * @param cysGz 改制
     * @return 改制集合
     */
    public List<CysGz> selectCysGzList(CysGz cysGz);

    /**
     * 新增改制
     * 
     * @param cysGz 改制
     * @return 结果
     */
    public int insertCysGz(CysGz cysGz);

    /**
     * 修改改制
     * 
     * @param cysGz 改制
     * @return 结果
     */
    public int updateCysGz(CysGz cysGz);

    /**
     * 删除改制
     * 
     * @param gzId 改制主键
     * @return 结果
     */
    public int deleteCysGzByGzId(String gzId);

    /**
     * 批量删除改制
     * 
     * @param gzIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCysGzByGzIds(String[] gzIds);
}
