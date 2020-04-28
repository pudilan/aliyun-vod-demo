package com.github.aliyunvod.demo.controller;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsResponse;
import com.github.aliyunvod.demo.service.SubmitTranscodeJobsService;
import com.github.aliyunvod.demo.service.VodService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @Autowired
  private SubmitTranscodeJobsService submitTranscodeJobsService;

  @Autowired
  private VodService vodService;

  /**
   * 提交转码任务
   * 
   * @param videoId
   * @return
   * @throws Exception
   */
  @PostMapping("/submitTranscodeJobs")
  public SubmitTranscodeJobsResponse submitTranscodeJobs(String videoId) throws Exception {
    SubmitTranscodeJobsResponse response = null;;
    try {
      response = submitTranscodeJobsService.submitTranscodeJobs(videoId);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return response;
  }

  @GetMapping("/getVideoPlayAuth")
  public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId)
      throws ServerException, ClientException {
    return vodService.getVideoPlayAuth(videoId);
  }

  /**
   * 事件回调，处理视频上传完成，转码完成等回调事件
   * 
   * @param params
   */
  @PostMapping("/callback")
  public void callback(@RequestBody Map<String, Object> params) {
    System.out.println("----------------callback--------------");
    System.out.println(params);
    String eventType = String.valueOf(params.get("EventType"));
    switch (eventType) {
      case "FileUploadComplete":
        // 视频上传完成，提交转码任务
        String videoId = String.valueOf(params.get("VideoId"));
        try {
          submitTranscodeJobsService.submitTranscodeJobs(videoId);
        } catch (Exception e) {
          // TODO 异常处理
          e.printStackTrace();
        }
        break;
      // TODO 其它事件
      default:
        break;
    }
    System.out.println("----------------callback--------------");
  }

}
