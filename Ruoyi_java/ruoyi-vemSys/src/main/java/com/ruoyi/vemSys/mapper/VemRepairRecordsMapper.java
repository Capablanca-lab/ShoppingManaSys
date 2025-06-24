package com.ruoyi.vemSys.mapper;

import java.util.List;
import com.ruoyi.vemSys.domain.VemRepairRecords;

/**
 * 售货机维修记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-12
 */
public interface VemRepairRecordsMapper 
{
    /**
     * 查询售货机维修记录
     * 
     * @param recordId 售货机维修记录主键
     * @return 售货机维修记录
     */
    public VemRepairRecords selectVemRepairRecordsByRecordId(Long recordId);

    /**
     * 查询售货机维修记录列表
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 售货机维修记录集合
     */
    public List<VemRepairRecords> selectVemRepairRecordsList(VemRepairRecords vemRepairRecords);

    /**
     * 新增售货机维修记录
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 结果
     */
    public int insertVemRepairRecords(VemRepairRecords vemRepairRecords);

    /**
     * 修改售货机维修记录
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 结果
     */
    public int updateVemRepairRecords(VemRepairRecords vemRepairRecords);

    /**
     * 删除售货机维修记录
     * 
     * @param recordId 售货机维修记录主键
     * @return 结果
     */
    public int deleteVemRepairRecordsByRecordId(Long recordId);

    /**
     * 批量删除售货机维修记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVemRepairRecordsByRecordIds(Long[] recordIds);
}
