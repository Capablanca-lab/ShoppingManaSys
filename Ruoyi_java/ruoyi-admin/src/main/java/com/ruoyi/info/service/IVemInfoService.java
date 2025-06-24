package com.ruoyi.info.service;

import java.util.List;
import com.ruoyi.info.domain.VemInfo;

/**
 * 售货机管理Service接口
 * 
 * @author admin
 * @date 2024-11-20
 */
public interface IVemInfoService 
{
    /**
     * 查询售货机管理
     * 
     * @param id 售货机管理主键
     * @return 售货机管理
     */
    public VemInfo selectVemInfoById(Long id);

    /**
     * 查询售货机管理列表
     * 
     * @param vemInfo 售货机管理
     * @return 售货机管理集合
     */
    public List<VemInfo> selectVemInfoList(VemInfo vemInfo);

    /**
     * 新增售货机管理
     * 
     * @param vemInfo 售货机管理
     * @return 结果
     */
    public int insertVemInfo(VemInfo vemInfo);

    /**
     * 修改售货机管理
     * 
     * @param vemInfo 售货机管理
     * @return 结果
     */
    public int updateVemInfo(VemInfo vemInfo);

    /**
     * 批量删除售货机管理
     * 
     * @param ids 需要删除的售货机管理主键集合
     * @return 结果
     */
    public int deleteVemInfoByIds(Long[] ids);

    /**
     * 删除售货机管理信息
     * 
     * @param id 售货机管理主键
     * @return 结果
     */
    public int deleteVemInfoById(Long id);
}
