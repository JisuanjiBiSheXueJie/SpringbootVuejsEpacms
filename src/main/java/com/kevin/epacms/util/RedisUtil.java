package com.kevin.epacms.util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @since 2022/11/11
 */
@Slf4j
@Component
public class RedisUtil {
    private final StringRedisTemplate stringRedisTemplate;

    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 存string类型数据到redis中
     *
     * @param key   键
     * @param value 值
     * @param time  时间
     * @param unit  单位
     */
    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /**
     * 根据key获取值
     *
     * @param redisKey 键
     * @return {@link String}
     */
    public String get(String redisKey) {
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 获取剩余时间
     *
     * @param key     键
     * @param seconds 单位（秒）
     * @return {@link Long}
     */
    public Long getExpire(String key, TimeUnit seconds) {
        return stringRedisTemplate.getExpire(key, seconds);
    }

    /**
     * 删除指定键
     *
     * @param key 键
     */
    public boolean deleteKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }
}
