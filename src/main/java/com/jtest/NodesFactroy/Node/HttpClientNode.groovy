package com.jtest.NodesFactroy.Node

import com.jtest.NodesFactroy.NodeConfig.NodeConfig
import com.google.gson.Gson
import com.jtest.testframe.ITestImpl
import com.jtest.utility.THttpClientUtil
import org.apache.http.HttpEntity
import org.apache.http.entity.ContentType
import org.springframework.web.client.RestTemplate

/**
 * Created by Administrator on 2016-04-02.
 */
class HttpClientNode extends NodeConfig {
  //String name;
  //String type;
  RestTemplate restTemplate=new RestTemplate();
  String url;
  String contentType;
  String data;
  transient THttpClientUtil httpClientUtil = new THttpClientUtil();
  transient ITestImpl testimpl = new ITestImpl();

  public void clearHeader(){
    httpClientUtil.clear();
  }

  public void addHeader(String key,String value){
    httpClientUtil.addHeader(key,value);
  }

  public byte[]  postForObject(String url, String data) {
     return restTemplate.postForObject(url, data, byte[].class);
  }


  public byte[] postHttpEntity(String url, String data, String mt) {
    httpClientUtil.postHttpEntity(url, data,mt);
  }
  public String post(String url, String data, String mt) {
    httpClientUtil.post(url, data, mt);
  }

  public String post(String url, byte[] data, ContentType ct) {
    httpClientUtil.post(url, data, ct);
  }

  public String post(String url, HttpEntity entity) {
    httpClientUtil.post(url, entity);
  }


  public String post() {
     httpClientUtil.post(url, data, contentType);
  }

  public String rest(String url, String data, String mt) {
    httpClientUtil=new THttpClientUtil(url);
    httpClientUtil.rest(url, data, mt);
  }

  public String get(String url) {
    httpClientUtil.get(url, contentType);
  }


  public void checkStatusCode(int exp){
    testimpl.checkEQ(exp,httpClientUtil.getStatusCode());
  }

  String toString() {
    return new Gson().toJson(this);
  }
}
