package com.ruoyi.profile.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.profile.mapper.VemProfileMapper;
import com.ruoyi.profile.domain.VemProfile;
import com.ruoyi.profile.service.IVemProfileService;
import com.ruoyi.product.domain.ProductInfo;

/**
 * 售货机利润Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-17
 */
@Service
public class VemProfileServiceImpl implements IVemProfileService 
{
    @Autowired
    private VemProfileMapper vemProfileMapper;

    /**
     * 查询售货机利润
     * 
     * @param vemId 售货机利润主键
     * @return 售货机利润
     */
    @Override
    public VemProfile selectVemProfileByVemId(Long vemId)
    {
        return vemProfileMapper.selectVemProfileByVemId(vemId);
    }

    /**
     * 查询售货机利润列表
     * 
     * @param vemProfile 售货机利润
     * @return 售货机利润
     */
    @Override
    public List<VemProfile> selectVemProfileList(VemProfile vemProfile)
    {
        return vemProfileMapper.selectVemProfileList(vemProfile);
    }

    /**
     * 新增售货机利润
     * 
     * @param vemProfile 售货机利润
     * @return 结果
     */
    @Transactional
    @Override
    public int insertVemProfile(VemProfile vemProfile)
    {
        int rows = vemProfileMapper.insertVemProfile(vemProfile);
        insertProductInfo(vemProfile);
        return rows;
    }

    /**
     * 修改售货机利润
     * 
     * @param vemProfile 售货机利润
     * @return 结果
     */
    @Transactional
    @Override
    public int updateVemProfile(VemProfile vemProfile)
    {
        vemProfileMapper.deleteProductInfoById(vemProfile.getVemId());
        insertProductInfo(vemProfile);
        return vemProfileMapper.updateVemProfile(vemProfile);
    }

    /**
     * 批量删除售货机利润
     * 
     * @param vemIds 需要删除的售货机利润主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteVemProfileByVemIds(Long[] vemIds)
    {
        vemProfileMapper.deleteProductInfoByIds(vemIds);
        return vemProfileMapper.deleteVemProfileByVemIds(vemIds);
    }

    /**
     * 删除售货机利润信息
     * 
     * @param vemId 售货机利润主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteVemProfileByVemId(Long vemId)
    {
        vemProfileMapper.deleteProductInfoById(vemId);
        return vemProfileMapper.deleteVemProfileByVemId(vemId);
    }

    /**
     * 新增商品信息1信息
     * 
     * @param vemProfile 售货机利润对象
     */
    public void insertProductInfo(VemProfile vemProfile)
    {
        List<ProductInfo> productInfoList = vemProfile.getProductInfoList();
        Long vemId = vemProfile.getVemId();
        if (StringUtils.isNotNull(productInfoList))
        {
            List<ProductInfo> list = new ArrayList<ProductInfo>();
            for (ProductInfo productInfo : productInfoList)
            {
                productInfo.setId(vemId);
                list.add(productInfo);
            }
            if (list.size() > 0)
            {
                vemProfileMapper.batchProductInfo(list);
            }
        }
    }
}
