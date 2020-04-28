package com.github.aliyunvod.demo.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.kms.model.v20160120.DecryptRequest;
import com.aliyuncs.kms.model.v20160120.DecryptResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频播放时调用的解密服务
 * 
 * @author pudilan
 *
 */
@RestController
public class DecryptController {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private DefaultAcsClient client;


  @RequestMapping("/decrypt")
  @ResponseBody
  public byte[] decrypt() {
    String token = request.getParameter("MtsHlsUriToken");
    String ciphertext = request.getParameter("Ciphertext");
    boolean validRe = validateToken(token);
    if (!validRe) {
      return null;
    }
    if (null == ciphertext) {
      return null;
    }
    // 从KMS中解密出来，并Base64 decode
    return decrypt(ciphertext);
  }

  /**
   * 校验令牌有效性
   * 
   * @param token
   * @return
   */
  private boolean validateToken(String token) {
    if (null == token || "".equals(token)) {
      return false;
    }
    // TODO 业务方实现令牌有效性校验
    return true;
  }

  /**
   * 调用KMS decrypt接口解密，并将明文base64decode
   * 
   * @param ciphertext
   * @return
   */
  private byte[] decrypt(String ciphertext) {
    DecryptRequest request = new DecryptRequest();
    request.setCiphertextBlob(ciphertext);
    request.setProtocol(ProtocolType.HTTPS);
    try {
      DecryptResponse response = client.getAcsResponse(request);
      String plaintext = response.getPlaintext();
      // 注意：需要base64 decode
      return Base64.decodeBase64(plaintext);
    } catch (ClientException e) {
      e.printStackTrace();
      return null;
    }
  }

}
