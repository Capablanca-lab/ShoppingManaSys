package com.ruoyi.profile.mapper;

import java.util.List;
import com.ruoyi.profile.domain.VemProfile;
import com.ruoyi.product.domain.ProductInfo;
/**
 * 售货机利润Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-17
 */
public interface VemProfileMapper 
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
     * 删除售货机利润
     * 
     * @param vemId 售货机利润主键
     * @return 结果
     */
    public int deleteVemProfileByVemId(Long vemId);

    /**
     * 批量删除售货机利润
     * 
     * @param vemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVemProfileByVemIds(Long[] vemIds);

    /**
     * 批量删除商品信息1
     * 
     * @param vemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductInfoByIds(Long[] vemIds);
    
    /**
     * 批量新增商品信息1
     * 
     * @param productInfoList 商品信息1列表
     * @return 结果
     */
    public int batchProductInfo(List<ProductInfo> productInfoList);
    

    /**
     * 通过售货机利润主键删除商品信息1信息
     * 
     * @param vemId 售货机利润ID
     * @return 结果
     */
    public int deleteProductInfoById(Long vemId);
}
