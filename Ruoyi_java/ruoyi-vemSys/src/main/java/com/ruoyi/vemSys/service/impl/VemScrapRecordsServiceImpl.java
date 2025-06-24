package com.ruoyi.vemSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vemSys.mapper.VemScrapRecordsMapper;
import com.ruoyi.vemSys.domain.VemScrapRecords;
import com.ruoyi.vemSys.service.IVemScrapRecordsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 售货机报废Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
@Service
public class VemScrapRecordsServiceImpl implements IVemScrapRecordsService 
{
    @Autowired
    private VemScrapRecordsMapper vemScrapRecordsMapper;

    /**
     * 查询售货机报废
     * 
     * @param scrapId 售货机报废主键
     * @return 售货机报废
     */
    @Override
    public VemScrapRecords selectVemScrapRecordsByScrapId(Long scrapId)
    {
        return vemScrapRecordsMapper.selectVemScrapRecordsByScrapId(scrapId);
    }

    /**
     * 查询售货机报废列表
     * 
     * @param vemScrapRecords 售货机报废
     * @return 售货机报废
     */
    @Override
    public List<VemScrapRecords> selectVemScrapRecordsList(VemScrapRecords vemScrapRecords)
    {
        return vemScrapRecordsMapper.selectVemScrapRecordsList(vemScrapRecords);
    }

    /**
     * 新增售货机报废
     * 
     * @param vemScrapRecords 售货机报废
     * @return 结果
     */
    @Override
    @Transactional//事务
    public int insertVemScrapRecords(VemScrapRecords vemScrapRecords)
    {
//        vemScrapRecords.setCreateTime(DateUtils.getNowDate());
//        return vemScrapRecordsMapper.insertVemScrapRecords(vemScrapRecords) + vemScrapRecordsMapper.updateVemInfoRecords(vemScrapRecords) -1;
        try {
            vemScrapRecords.setCreateTime(DateUtils.getNowDate());
            int insertResult = vemScrapRecordsMapper.insertVemScrapRecords(vemScrapRecords);
            int updateResult = vemScrapRecordsMapper.updateVemInfoRecords(vemScrapRecords);

            return insertResult + updateResult - 1;
        } catch (Exception e) {
            // 触发事务回滚,业务操作失败
            return 0;
        }
    }

    /**
     * 修改售货机报废
     * 
     * @param vemScrapRecords 售货机报废
     * @return 结果
     */
    @Override
    public int updateVemScrapRecords(VemScrapRecords vemScrapRecords)
    {
        vemScrapRecords.setUpdateTime(DateUtils.getNowDate());
        return vemScrapRecordsMapper.updateVemScrapRecords(vemScrapRecords);
    }

    /**
     * 批量删除售货机报废
     * 
     * @param scrapIds 需要删除的售货机报废主键
     * @return 结果
     */
    @Override
    public int deleteVemScrapRecordsByScrapIds(Long[] scrapIds)
    {
        return vemScrapRecordsMapper.deleteVemScrapRecordsByScrapIds(scrapIds);
    }

    /**
     * 删除售货机报废信息
     * 
     * @param scrapId 售货机报废主键
     * @return 结果
     */
    @Override
    public int deleteVemScrapRecordsByScrapId(Long scrapId)
    {
        return vemScrapRecordsMapper.deleteVemScrapRecordsByScrapId(scrapId);
    }
}
