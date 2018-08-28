package spring.boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	/**
	 * RedisTemplate只有配置了才可以注入，而StringRedisTemplate可以直接自动注入
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> createTemplate(){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
	
}
