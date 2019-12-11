package com.jtest.utility;

import com.jtest.utility.testlog.TestLog;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class THttpClientUtil {

	String url = "http://127.0.0.1:8080/Agent";
	String url_base = "http://127.0.0.1:8080/Agent";

	transient  CloseableHttpResponse response;
	
	public int getStatusCode() {
		return statusCode;
	}
	
	transient int statusCode;
	transient Map<String,String> headers=new HashMap<>();


	public void clearHeader(){
		headers.clear();
	}

	public void addHeader(String key,String value){
		headers.put(key,value);
	}
	public THttpClientUtil() {
	}

	public THttpClientUtil(String url_base ) {
		this.url = url_base;
		this.url_base = url_base;
	}
 
	public String rest(String r_url, String data, String mt) throws IOException {
		this.url = url_base+r_url;
		return post(url,data, mt);
	}

	public String post(String url, String data, String mt) throws IOException {
		this.url = url;
		return post(data, mt);
	}

	public String post(String url, byte[] data, ContentType ct) {
		this.url = url;
		return post(data, ct);
	}

	public String post(String url, HttpEntity entity) {
		this.url = url;
		return post(entity);
	}

	public String httpPut(String url,String data, String mt) {
//		ResponseHandler<String> rh=response->{
//			String result=null;
//			int statusCode = response.getStatusLine().getStatusCode();
//			System.out.println("statusCode=" + statusCode);
//			HttpEntity entity = response.getEntity();
//			if (statusCode >= 200 && statusCode < 300) {
//				result = EntityUtils.toString(entity, "UTF-8");
//				System.out.println(result);
//			}
//			return  result;
//		};
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut put = new HttpPut(url);
		System.out.println("exec request put: " + put.getURI());
		try {
			StringEntity sentity = new StringEntity(data, "UTF-8");
			put.setEntity(sentity);
			response = httpclient.execute(put);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println(result);
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						//System.out.println("put Response : " + result);
						TestLog.log("put Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestLog.log("put result : " + result);
		return result;
	}


	public String httpHead(String url) {

		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHead del = new HttpHead(url);
		System.out.println("exec request delete: " + del.getURI());
		try {

			response = httpclient.execute(del);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println(result);
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						//System.out.println(" Response : " + result);
						TestLog.log(" Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestLog.log("del result : " + result);
		return result;
	}

	public String httpDel(String url) {

		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpDelete del = new HttpDelete(url);
		System.out.println("exec request delete: " + del.getURI());
		try {

			response = httpclient.execute(del);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println(result);
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						System.out.println(" Response : " + result);
						TestLog.log(" Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestLog.log("del result : " + result);
		return result;
	}


	public String post(String data, String mt) throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		for(String key:headers.keySet()){
			httppost.addHeader(key,headers.get(key));
		}
		List formparams = new ArrayList();
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			StringEntity sentity = new StringEntity(data, "UTF-8");
			if(!mt.isEmpty()){
				sentity = new  StringEntity(data, mt,"UTF-8");
			}
			httppost.setEntity(sentity);
			System.out.println("executing post request " + httppost.getURI());
			response = httpclient.execute(httppost);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println( result );
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						//System.out.println("Post Response : " + result);
						TestLog.log("Post Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} finally {
				httpclient.close();

		}
		TestLog.log("Post result : " + result);
		return result;
	}

	public byte[] postHttpEntity(String url,String data, String mt) throws IOException {
		this.url = url;

		byte[] result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		for(String key:headers.keySet()){
			httppost.addHeader(key,headers.get(key));
		}
		List formparams = new ArrayList();
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			StringEntity sentity = new StringEntity(data, "UTF-8");
			if(!mt.isEmpty()){
				sentity = new  StringEntity(data, mt,"UTF-8");
			}
			httppost.setEntity(sentity);
			System.out.println("executing post request " + httppost.getURI());
			response = httpclient.execute(httppost);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toByteArray(entity );
					System.out.println( result );
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						//System.out.println("Post Response : " + result);
						TestLog.log("Post Response : " + result.length);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();

		}
		TestLog.log("Post result : " + result);
		return result;
	}
	public String post(byte[] data, ContentType ct) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		List formparams = new ArrayList();
		UrlEncodedFormEntity uefEntity;
		try {

			ByteArrayEntity sentity =   new  ByteArrayEntity( data, ct );
			httppost.setEntity(sentity);
			System.out.println("executing post request " + httppost.getURI());
			response = httpclient.execute(httppost);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println( result );
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						System.out.println("Post Response : " + result);
						TestLog.log("Post Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestLog.log("Post result : " + result);
		return result;
	}

	public String post(HttpEntity sentity) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		List formparams = new ArrayList();
		UrlEncodedFormEntity uefEntity;
		try {

			httppost.setEntity(sentity);
			System.out.println("executing post request " + httppost.getURI());
			response = httpclient.execute(httppost);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println( result );
					if (entity != null) {
						System.out
								.println("--------------------------------------");
						System.out.println("Post Response : " + result);
						TestLog.log("Post Response : " + result);
						System.out
								.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestLog.log("Post result : " + result);
		return result;
	}

	public String get(String url, String mt) {
		this.url = url;
		return get(mt);
	}

	public String get(String mt) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		for(String key:headers.keySet()){
			httpget.addHeader(key,headers.get(key));
		}	//httpget.setContentType(mt + "; charset=" + cs);
		//List formparams = new ArrayList();
		//UrlEncodedFormEntity uefEntity;
		try {

			System.out.println("executing get request " + httpget.getURI());
			response = httpclient.execute(httpget);
			try {
				statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode=" + statusCode);
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity, "UTF-8");
					if (entity != null) {
						System.out.println("--------------------------------------");
						System.out.println("GET Response : " + result);
						System.out.println("--------------------------------------");
					}
				} else {
					System.out
							.println("response.getStatusLine().getStatusCode() ="
									+ response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String md5Encode(String str) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte bytes[] = md5.digest();
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0xff);
				if (s.length() == 1) {
					buf.append("0");
				}
				buf.append(s);
			}

		} catch (Exception ex) {
		}
		return buf.toString();
	}

	public static void main(String[] args) {

	}
}
