package com.ruoyi.info.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.info.mapper.VemInfoMapper;
import com.ruoyi.info.domain.VemInfo;
import com.ruoyi.info.service.IVemInfoService;

/**
 * 售货机管理Service业务层处理
 * 
 * @author admin
 * @date 2024-11-20
 */
@Service
public class VemInfoServiceImpl implements IVemInfoService 
{
    @Autowired
    private VemInfoMapper vemInfoMapper;

    /**
     * 查询售货机管理
     * 
     * @param id 售货机管理主键
     * @return 售货机管理
     */
    @Override
    public VemInfo selectVemInfoById(Long id)
    {
        return vemInfoMapper.selectVemInfoById(id);
    }

    /**
     * 查询售货机管理列表
     * 
     * @param vemInfo 售货机管理
     * @return 售货机管理
     */
    @Override
    public List<VemInfo> selectVemInfoList(VemInfo vemInfo)
    {
        return vemInfoMapper.selectVemInfoList(vemInfo);
    }

    /**
     * 新增售货机管理
     * 
     * @param vemInfo 售货机管理
     * @return 结果
     */
    @Override
    public int insertVemInfo(VemInfo vemInfo)
    {
        return vemInfoMapper.insertVemInfo(vemInfo);
    }

    /**
     * 修改售货机管理
     * 
     * @param vemInfo 售货机管理
     * @return 结果
     */
    @Override
    public int updateVemInfo(VemInfo vemInfo)
    {
        return vemInfoMapper.updateVemInfo(vemInfo);
    }

    /**
     * 批量删除售货机管理
     * 
     * @param ids 需要删除的售货机管理主键
     * @return 结果
     */
    @Override
    public int deleteVemInfoByIds(Long[] ids)
    {
        return vemInfoMapper.deleteVemInfoByIds(ids);
    }

    /**
     * 删除售货机管理信息
     * 
     * @param id 售货机管理主键
     * @return 结果
     */
    @Override
    public int deleteVemInfoById(Long id)
    {
        return vemInfoMapper.deleteVemInfoById(id);
    }
}
