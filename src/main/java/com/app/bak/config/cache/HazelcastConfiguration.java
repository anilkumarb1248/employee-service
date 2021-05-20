package com.app.bak.config.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;

@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config hazelCastConfig() {
		Config config = new Config();
		config.setInstanceName("hazelcast-instance");
		config.addMapConfig(getMapConfig("users"));
		config.addMapConfig(getMapConfig("employees"));
		return config;
	}

	public MapConfig getMapConfig(String cacheName) {
		MapConfig mapConfig = new MapConfig();
		mapConfig.setName(cacheName);

		EvictionConfig evictionConfig = new EvictionConfig();
		evictionConfig.setSize(200);
		evictionConfig.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE);
		evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);

		mapConfig.setEvictionConfig(evictionConfig);
		mapConfig.setTimeToLiveSeconds(300);
		mapConfig.setMaxIdleSeconds(60);
		return mapConfig;
	}

	// We can also implement the Config like this, best way
//	@Bean
	public Config hazelCastConfig1() {
		return new Config()
				.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig()
								.setName("users")
								.setTimeToLiveSeconds(20)
								.setEvictionConfig(new EvictionConfig()
													.setSize(200)
													.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
													.setEvictionPolicy(EvictionPolicy.LRU)
										)

		);
	}
}
