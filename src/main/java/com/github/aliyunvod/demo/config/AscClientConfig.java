package com.github.aliyunvod.demo.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.github.aliyunvod.demo.config.properties.AliyunVodProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AscClientConfig {

  @Autowired
  private AliyunVodProperties aliyunVodProperties;

  @Bean
  public DefaultAcsClient defaultAcsClient() {
    // KMS的区域，必须与视频对应区域
    String region = aliyunVodProperties.getRegion();
    // 访问KMS的授权AK信息
    String accessKeyId = aliyunVodProperties.getAccessKeyId();
    String accessKeySecret = aliyunVodProperties.getAccessKeySecret();
    return new DefaultAcsClient(DefaultProfile.getProfile(region, accessKeyId, accessKeySecret));
  }

}
