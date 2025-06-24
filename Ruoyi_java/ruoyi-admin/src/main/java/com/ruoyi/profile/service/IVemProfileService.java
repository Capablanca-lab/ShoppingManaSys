package com.ruoyi.profile.service;

import java.util.List;
import com.ruoyi.profile.domain.VemProfile;

/**
 * 售货机利润Service接口
 * 
 * @author ruoyi
 * @date 2025-02-17
 */
public interface IVemProfileService 
{
    /**
     * 查询售货机利润
     * 
     * @param vemId 售货机利润主键
     * @return 售货机利润
     */
    public VemProfile selectVemProfileByVemId(Long vemId);

    /**
     * 查询售货机利润列表
     * 
     * @param vemProfile 售货机利润
     * @return 售货机利润集合
     */
    public List<VemProfile> selectVemProfileList(VemProfile vemProfile);

    /**
     * 新增售货机利润
     * 
     * @param vemProfile 售货机利润
     * @return 结果
     */
    public int insertVemProfile(VemProfile vemProfile);

    /**
     * 修改售货机利润
     * 
     * @param vemProfile 售货机利润
     * @return 结果
     */
    public int updateVemProfile(VemProfile vemProfile);

    /**
     * 批量删除售货机利润
     * 
     * @param vemIds 需要删除的售货机利润主键集合
     * @return 结果
     */
    public int deleteVemProfileByVemIds(Long[] vemIds);

    /**
     * 删除售货机利润信息
     * 
     * @param vemId 售货机利润主键
     * @return 结果
     */
    public int deleteVemProfileByVemId(Long vemId);
}
