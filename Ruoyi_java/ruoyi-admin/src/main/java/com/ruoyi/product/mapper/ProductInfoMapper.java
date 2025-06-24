package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProductInfo;

/**
 * 商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-15
 */
public interface ProductInfoMapper 
{
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    public ProductInfo selectProductInfoById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param productInfo 商品信息
     * @return 商品信息集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    /**
     * 新增商品信息
     * 
     * @param productInfo 商品信息
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改商品信息
     * 
     * @param productInfo 商品信息
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 删除商品信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteProductInfoById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductInfoByIds(Long[] ids);
}
