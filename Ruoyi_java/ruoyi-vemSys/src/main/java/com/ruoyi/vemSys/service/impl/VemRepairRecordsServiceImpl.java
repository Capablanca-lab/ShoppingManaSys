package com.ruoyi.vemSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vemSys.mapper.VemRepairRecordsMapper;
import com.ruoyi.vemSys.domain.VemRepairRecords;
import com.ruoyi.vemSys.service.IVemRepairRecordsService;

/**
 * 售货机维修记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-12
 */
@Service
public class VemRepairRecordsServiceImpl implements IVemRepairRecordsService 
{
    @Autowired
    private VemRepairRecordsMapper vemRepairRecordsMapper;

    /**
     * 查询售货机维修记录
     * 
     * @param recordId 售货机维修记录主键
     * @return 售货机维修记录
     */
    @Override
    public VemRepairRecords selectVemRepairRecordsByRecordId(Long recordId)
    {
        return vemRepairRecordsMapper.selectVemRepairRecordsByRecordId(recordId);
    }

    /**
     * 查询售货机维修记录列表
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 售货机维修记录
     */
    @Override
    public List<VemRepairRecords> selectVemRepairRecordsList(VemRepairRecords vemRepairRecords)
    {
        return vemRepairRecordsMapper.selectVemRepairRecordsList(vemRepairRecords);
    }

    /**
     * 新增售货机维修记录
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 结果
     */
    @Override
    public int insertVemRepairRecords(VemRepairRecords vemRepairRecords)
    {
        return vemRepairRecordsMapper.insertVemRepairRecords(vemRepairRecords);
    }

    /**
     * 修改售货机维修记录
     * 
     * @param vemRepairRecords 售货机维修记录
     * @return 结果
     */
    @Override
    public int updateVemRepairRecords(VemRepairRecords vemRepairRecords)
    {
        return vemRepairRecordsMapper.updateVemRepairRecords(vemRepairRecords);
    }

    /**
     * 批量删除售货机维修记录
     * 
     * @param recordIds 需要删除的售货机维修记录主键
     * @return 结果
     */
    @Override
    public int deleteVemRepairRecordsByRecordIds(Long[] recordIds)
    {
        return vemRepairRecordsMapper.deleteVemRepairRecordsByRecordIds(recordIds);
    }

    /**
     * 删除售货机维修记录信息
     * 
     * @param recordId 售货机维修记录主键
     * @return 结果
     */
    @Override
    public int deleteVemRepairRecordsByRecordId(Long recordId)
    {
        return vemRepairRecordsMapper.deleteVemRepairRecordsByRecordId(recordId);
    }
}
