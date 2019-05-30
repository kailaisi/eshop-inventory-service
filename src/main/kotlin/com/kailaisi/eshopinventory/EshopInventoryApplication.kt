package com.kailaisi.eshopinventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import redis.clients.jedis.JedisPool
import org.apache.tomcat.jni.Socket.pool
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPoolConfig


@SpringBootApplication
@EnableEurekaClient
class EshopInventoryApplication {
    @Bean
    fun jedis(): JedisPool {
        val config = JedisPoolConfig()
        config.maxTotal = 100
        config.maxIdle = 5
        config.maxWaitMillis = 1000 * 100
        config.testOnBorrow = true
        return JedisPool(config, "localhost", 6379)
    }
}

fun main(args: Array<String>) {
    runApplication<EshopInventoryApplication>(*args)
}
