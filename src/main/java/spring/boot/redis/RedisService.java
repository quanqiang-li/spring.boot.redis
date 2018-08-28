package spring.boot.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	// 需要泛型指定key和value类型
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// 等效于RedisTemplate<String, Object>，key是字符串的便捷模版
	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 设置字符串
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param value
	 * @param timeout
	 *            -1 永不过期
	 * @param unit
	 *            must not be {@literal null}.
	 * @see <a href="http://redis.io/commands/setex">Redis Documentation:
	 *      SETEX</a>
	 */
	public void setString(String key, String value, long timeout, TimeUnit unit) {
		if (timeout == -1) {
			stringRedisTemplate.opsForValue().set(key, value);
		} else {
			stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
		}
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 在list右侧插入数据
	 * @param key
	 * @param value
	 * @param timeout -1 永不过期
	 * @param unit
	 */
	public void rightPushOfList(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForList().rightPush(key, value);
		if (timeout != -1) {
			stringRedisTemplate.expire(key, timeout, unit);
		}
	}
	
	/**
	 * 从list左侧移除并返回第一个数据
	 * @param key
	 * @param timeout -1永不超时,最好有个超时时间
	 * @param unit
	 */
	public String leftPopOfList(String key,long timeout, TimeUnit unit){
		if(timeout != -1){
			return stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
		}else{
			return stringRedisTemplate.opsForList().leftPop(key);
		}
	}
}
