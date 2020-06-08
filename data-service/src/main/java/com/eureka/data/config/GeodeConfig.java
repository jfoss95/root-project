package com.eureka.data.config;

import java.util.Properties;

import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.cache.GemfireCacheManager;

import com.eureka.data.model.UserInfo;
import com.eureka.data.repo.UserCredentialsRepository;

@Configuration
@EnableCaching
public class GeodeConfig {
	
	@Autowired
	UserCredentialsRepository userCredentialsrepo;
	/*
	@Bean
	Properties gemfireProperties() {
	    Properties gemfireProperties = new Properties();
	    gemfireProperties.setProperty("name","data-service");
		gemfireProperties.setProperty("mcast-port", "0");
		gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }

	@Bean
	CacheFactoryBean gemfireCache() {
	    CacheFactoryBean gemfireCache = new CacheFactoryBean();
	    gemfireCache.setClose(true);
	    gemfireCache.setProperties(gemfireProperties());
	    return gemfireCache;
	}

	@Bean
	GemfireCacheManager cacheManager() {
		return new GemfireCacheManager();
	}
	
	@Bean(name="user")
	LocalRegionFactoryBean<Long, UserInfo> usersRegion(final GemFireCache cache) {
		LocalRegionFactoryBean<Long, UserInfo> usersRegion = new LocalRegionFactoryBean<Long, UserInfo>();
		usersRegion.setCache(cache);
		usersRegion.setClose(false);
		usersRegion.setName("user");
		usersRegion.setPersistent(false);
		usersRegion.setDataPolicy(DataPolicy.PRELOADED);
		return usersRegion;
	}
	*/
}
