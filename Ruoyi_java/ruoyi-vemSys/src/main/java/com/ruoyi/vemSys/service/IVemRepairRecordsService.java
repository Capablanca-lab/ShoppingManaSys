package com.ruoyi.vemSys.service;

import java.util.List;
import com.ruoyi.vemSys.domain.VemRepairRecords;

/**
 * 售货机维修记录Service接口
 * 
 * @author ruoyi
 * @date 2025-03-12
 */
public interface IVemRepairRecordsService 
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
     * 批量删除售货机维修记录
     * 
     * @param recordIds 需要删除的售货机维修记录主键集合
     * @return 结果
     */
    public int deleteVemRepairRecordsByRecordIds(Long[] recordIds);

    /**
     * 删除售货机维修记录信息
     * 
     * @param recordId 售货机维修记录主键
     * @return 结果
     */
    public int deleteVemRepairRecordsByRecordId(Long recordId);
}
