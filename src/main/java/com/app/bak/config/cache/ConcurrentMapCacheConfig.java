package com.app.bak.config.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

//@Configuration // Commenting to work with other caches
public class ConcurrentMapCacheConfig {

//	@Bean
//	public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
//		return new ConcurrentMapCacheManagerCustomizer();
//	}
//
//	class ConcurrentMapCacheManagerCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
//
//		@Override
//		public void customize(ConcurrentMapCacheManager cacheManager) {
//			cacheManager.setAllowNullValues(false);
//		}
//
//	}

//	@Bean
//	public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
//		return new CacheManagerCustomizer<ConcurrentMapCacheManager>() {
//			@Override
//			public void customize(ConcurrentMapCacheManager cacheManager) {
//				cacheManager.setAllowNullValues(false);
//			}
//		};
//	}

	@Bean
	public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
		return (ConcurrentMapCacheManager cacheManager) -> {
			cacheManager.setAllowNullValues(false);
		};
	}

}
