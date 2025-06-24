package com.ruoyi.vemSys.mapper;

import java.util.List;
import com.ruoyi.vemSys.domain.VemScrapRecords;

/**
 * 售货机报废Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
public interface VemScrapRecordsMapper 
{
    /**
     * 查询售货机报废
     * 
     * @param scrapId 售货机报废主键
     * @return 售货机报废
     */
    public VemScrapRecords selectVemScrapRecordsByScrapId(Long scrapId);

    /**
     * 查询售货机报废列表
     * 
     * @param vemScrapRecords 售货机报废
     * @return 售货机报废集合
     */
    public List<VemScrapRecords> selectVemScrapRecordsList(VemScrapRecords vemScrapRecords);

    /**
     * 新增售货机报废
     * 
     * @param vemScrapRecords 售货机报废
     * @return 结果
     */
    public int insertVemScrapRecords(VemScrapRecords vemScrapRecords);

    public int updateVemInfoRecords(VemScrapRecords vemScrapRecords);
    /**
     * 修改售货机报废
     * 
     * @param vemScrapRecords 售货机报废
     * @return 结果
     */
    public int updateVemScrapRecords(VemScrapRecords vemScrapRecords);

    /**
     * 删除售货机报废
     * 
     * @param scrapId 售货机报废主键
     * @return 结果
     */
    public int deleteVemScrapRecordsByScrapId(Long scrapId);

    /**
     * 批量删除售货机报废
     * 
     * @param scrapIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVemScrapRecordsByScrapIds(Long[] scrapIds);
}
