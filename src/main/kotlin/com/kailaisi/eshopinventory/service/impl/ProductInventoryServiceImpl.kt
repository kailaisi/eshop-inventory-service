package com.kailaisi.eshopinventory.service.impl

import com.alibaba.fastjson.JSONObject
import com.kailaisi.eshopinventory.mapper.ProductInventoryMapper
import com.kailaisi.eshopinventory.model.ProductInventory
import com.kailaisi.eshopinventory.service.ProductInventoryService
import com.kailaisi.eshopinventory.util.FastJsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import redis.clients.jedis.BinaryJedisCluster
import redis.clients.jedis.JedisPool

/**
 *
 */
@Service
class ProductInventoryServiceImpl : ProductInventoryService {
    @Autowired
    lateinit var productInventoryMapper: ProductInventoryMapper
    @Autowired
    lateinit var jedisPool: JedisPool

    override fun add(productInventory: ProductInventory) {
        productInventoryMapper.add(productInventory)
        val jedis = jedisPool.resource
        jedis.set("product_inventory_${productInventory.productId}", FastJsonUtil.bean2Json(productInventory))
    }

    override fun delete(id: Long) {
        productInventoryMapper.delete(id)
        val jedis = jedisPool.resource
        val inventory = findById(id)
        jedis.del("product_inventory_${inventory.productId}")
    }

    override fun update(productInventory: ProductInventory) {
        productInventoryMapper.update(productInventory)
        val jedis = jedisPool.resource
        jedis.set("product_inventory_${productInventory.productId}", FastJsonUtil.bean2Json(productInventory))
    }

    override fun findById(id: Long): ProductInventory {
        return productInventoryMapper.findById(id)
    }

    override fun findByProductId(id: Long): ProductInventory {
        var jedis = jedisPool.resource
        var get = jedis.get("product_inventory_$id")
        return if (!get.isNullOrEmpty()) {
            JSONObject.parseObject(get, ProductInventory::class.java)
        } else {
            var price = productInventoryMapper.findByProductId(id)
            price.let {
                jedis.set("product_inventory_$id", JSONObject.toJSONString(it))
            }
            price
        }
    }
}