package com.github.aliyunvod.demo.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VodService {

  @Autowired
  private DefaultAcsClient client;

  public GetVideoPlayAuthResponse getVideoPlayAuth(String vid)
      throws ServerException, ClientException {
    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
    request.setVideoId(vid);
    return client.getAcsResponse(request);
  }

}
