package com.kailaisi.eshopinventory.service

import com.kailaisi.eshopinventory.model.ProductInventory

/**
 *描述：
 *<p/>作者：wu
 *<br/>创建时间：2019/5/20 11:53
 */
interface ProductInventoryService {
    fun add(productInventory: ProductInventory)
    fun delete(id: Long)
    fun update(productInventory: ProductInventory)
    fun findById(id: Long): ProductInventory
}
