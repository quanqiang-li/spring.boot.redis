package spring.boot.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
	

	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RedisService redis = context.getBean(RedisService.class);
		String key = "my:key:String";
		String value = "hello 中国";
		TimeUnit unit = TimeUnit.MILLISECONDS;
		long timeout = 5;
		redis.setString(key, value, timeout, unit);
		String string = redis.getString(key);
		System.out.println(string);
		redis.rightPushOfList("my:key:list", value, timeout, unit);
		String leftPopOfList = redis.leftPopOfList("my:key:list", timeout, unit);
		System.out.println(leftPopOfList);
		try {
			Thread.sleep(1000*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		context.close();
	}
}
