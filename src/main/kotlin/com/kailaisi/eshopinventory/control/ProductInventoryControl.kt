package com.kailaisi.eshopinventory.control

import com.kailaisi.eshopinventory.model.ProductInventory
import com.kailaisi.eshopinventory.service.ProductInventoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 *描述：商品库存服务
 *<p/>作者：wu
 *<br/>创建时间：2019/5/20 13:09
 */
@Controller
@RequestMapping("product-inventory")
class ProductInventoryControl {
    @Autowired
    lateinit var mService: ProductInventoryService

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun add(@RequestBody productInventory: ProductInventory): String {
        mService.add(productInventory)
        return "success"
    }

    @RequestMapping("/update", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun update(@RequestBody productInventory: ProductInventory): String {
        mService.update(productInventory)
        return "success"
    }

    @RequestMapping("/delete", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun delete(id: Long): String {
        mService.delete(id)
        return "success"
    }

    @RequestMapping("/findById")
    @ResponseBody
    fun findById(id: Long): ProductInventory {
        return mService.findById(id)
    }

    @RequestMapping("/findByProductId")
    @ResponseBody
    fun findByProductId(id: Long): ProductInventory {
        return mService.findByProductId(id)
    }
}