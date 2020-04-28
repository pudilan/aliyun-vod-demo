package com.github.aliyunvod.demo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("aliyun.vod")
public class AliyunVodProperties {

  private String region;

  private String accessKeyId;

  private String accessKeySecret;

  // 点播给用户在KMS(秘钥管理服务)中的Service Key，可在用户秘钥管理服务对应的区域看到描述为vod的service key
  private String serviceKey;

  // 转码模板id
  private String templateGroupId;

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  public String getServiceKey() {
    return serviceKey;
  }

  public void setServiceKey(String serviceKey) {
    this.serviceKey = serviceKey;
  }

  public String getTemplateGroupId() {
    return templateGroupId;
  }

  public void setTemplateGroupId(String templateGroupId) {
    this.templateGroupId = templateGroupId;
  }

}
