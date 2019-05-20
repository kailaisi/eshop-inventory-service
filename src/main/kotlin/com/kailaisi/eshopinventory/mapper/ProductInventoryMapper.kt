package com.kailaisi.eshopinventory.mapper

import com.kailaisi.eshopinventory.model.ProductInventory
import org.apache.ibatis.annotations.*

/**
 *描述：商品价格
 *<p/>作者：wu
 *<br/>创建时间：2019/5/20 11:40
 */
@Mapper
interface ProductInventoryMapper {
    @Insert("insert into product_inventory(value,product_id) values(#{value},#{productId})")
    fun add(productInventory: ProductInventory)

    @Delete("delete from product_inventory where id=#{id}")
    fun delete(id: Long)

    @Update("update product_inventory set value=#{value} ,product_id=#{productId} where id=#{id}")
    fun update(productInventory: ProductInventory)

    @Select("select * from product_inventory where id=#{id}")
    @Results(
            Result(column = "product_id", property = "productId")
    )
    fun findById(id: Long): ProductInventory
}