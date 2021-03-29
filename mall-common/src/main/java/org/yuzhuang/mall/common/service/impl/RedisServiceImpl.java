package org.yuzhuang.mall.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.yuzhuang.mall.common.service.RedisService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhuangzhuang
 * @create 2021/03/05 10:58
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> template;


    @Override
    public void set(String key, Object value, long timeout) {
        template.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
        template.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public Boolean del(String key) {
        return template.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return template.delete(keys);
    }

    @Override
    public Boolean setExpire(String key, long timeout) {
        return template.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return template.getExpire(key);
    }

    @Override
    public Boolean hasKey(String key) {
        return template.hasKey(key);
    }

    @Override
    public Long incr(String key, long delta) {
        return template.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return template.opsForValue().decrement(key, delta);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return template.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long timeout) {
        template.opsForHash().put(key, hashKey, value);
        return setExpire(key, timeout);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        template.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return template.opsForHash().entries(key);
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long timeout) {
        template.opsForHash().putAll(key, map);
        return setExpire(key, timeout);
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {
        template.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
        template.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return template.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return template.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return template.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> sMembers(String key) {
        return template.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return template.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(String key, long timeout, Object... values) {
        Long count = template.opsForSet().add(key, values);
        setExpire(key, timeout);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return template.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(String key) {
        return template.opsForSet().size(key);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return template.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return template.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return template.opsForList().size(key);
    }

    @Override
    public Object lIndex(String key, long index) {
        return template.opsForList().index(key, index);
    }

    @Override
    public Long lPush(String key, Object value) {
        return template.opsForList().leftPush(key, value);
    }

    @Override
    public Long lPush(String key, Object value, long timeout) {
        Long count = template.opsForList().leftPush(key, value);
        setExpire(key, timeout);
        return count;
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return template.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long lPushAll(String key, Long timeout, Object... values) {
        Long count = template.opsForList().leftPushAll(key, values);
        setExpire(key, timeout);
        return count;
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return template.opsForList().remove(key, count, value);
    }
}
