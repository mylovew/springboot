package com.zwk.springboot.util;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {



  /**
   * http请求方法
   * 
   * @param url url地址
   * @param pro 传入参数
   * @param method 请求方法
   * @return
   */
  public static String httpConnectionRequest(String url, String pro, String method, String encode) {
    try {
      URL reqUrl = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();
      conn.setDoOutput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod(method);
      conn.setInstanceFollowRedirects(true);
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.connect();
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());
      if (pro != null) {
        out.write(pro.getBytes(encode));
        out.flush();
        out.close();
      }
      System.out.println(conn.getResponseCode());
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line.trim());
        }
        reader.close();
        conn.disconnect();
        return sb.toString();
      } else {
        conn.disconnect();
        return null;
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String httpsConnection(String url, String method, String pro, String encode) {
    try {
      URL reqUrl = new URL(url);
      HttpsURLConnection conn = (HttpsURLConnection) reqUrl.openConnection();
      conn.setDoOutput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod(method);
      conn.setInstanceFollowRedirects(true);
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.connect();
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());
      if (pro != null) {
        out.write(pro.getBytes(encode));
        out.flush();
        out.close();
      }
      System.err.println(conn.getResponseCode());
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line.trim());
        }
        reader.close();
        conn.disconnect();
        return sb.toString();
      } else {
        conn.disconnect();
        return null;
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String httpsConnection(String url, String method, String pro, String encode, String contentType) {
    try {
      URL reqUrl = new URL(url);
      HttpsURLConnection conn = (HttpsURLConnection) reqUrl.openConnection();
      conn.setDoOutput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod(method);
      conn.setInstanceFollowRedirects(true);
      conn.setRequestProperty("Content-Type", contentType);
      conn.connect();
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());
      if (pro != null) {
        out.write(pro.getBytes(encode));
        out.flush();
        out.close();
      }
      System.err.println(conn.getResponseCode());
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line.trim());
        }
        reader.close();
        conn.disconnect();
        return sb.toString();
      } else {
        conn.disconnect();
        return null;
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  

  /**
   * 上传图片
   * 
   * @param urlStr
   * @param textMap
   * @param fileMap
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap) {
    String res = "";
    HttpURLConnection conn = null;
    String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
    try {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection) url.openConnection();
      conn.setConnectTimeout(5000);
      conn.setReadTimeout(30000);
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Connection", "Keep-Alive");
      conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
      conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

      OutputStream out = new DataOutputStream(conn.getOutputStream());
      // text
      if (textMap != null) {
        StringBuffer strBuf = new StringBuffer();
        Iterator iter = textMap.entrySet().iterator();
        while (iter.hasNext()) {
          Map.Entry entry = (Map.Entry) iter.next();
          String inputName = (String) entry.getKey();
          String inputValue = (String) entry.getValue();
          if (inputValue == null) {
            continue;
          }
          strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
          strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
          strBuf.append(inputValue);
        }
        out.write(strBuf.toString().getBytes());
      }

      // file
      if (fileMap != null) {
        Iterator iter = fileMap.entrySet().iterator();
        while (iter.hasNext()) {
          Map.Entry entry = (Map.Entry) iter.next();
          String inputName = (String) entry.getKey();
          String inputValue = (String) entry.getValue();
          if (inputValue == null) {
            continue;
          }
          File file = new File(inputValue);
          String filename = file.getName();
          String contentType = new MimetypesFileTypeMap().getContentType(file);
          if (filename.endsWith(".png")) {
            contentType = "image/png";
          }
          if (contentType == null || contentType.equals("")) {
            contentType = "application/octet-stream";
          }

          StringBuffer strBuf = new StringBuffer();
          strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
          strBuf
              .append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
          strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

          out.write(strBuf.toString().getBytes());

          DataInputStream in = new DataInputStream(new FileInputStream(file));
          int bytes = 0;
          byte[] bufferOut = new byte[1024];
          while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
          }
          in.close();
        }
      }

      byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
      out.write(endData);
      out.flush();
      out.close();

      // 读取返回数据
      StringBuffer strBuf = new StringBuffer();
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line = null;
      while ((line = reader.readLine()) != null) {
        strBuf.append(line).append("\n");
      }
      res = strBuf.toString();
      reader.close();
      reader = null;
    } catch (Exception e) {
      System.out.println("发送POST请求出错。" + urlStr);
      e.printStackTrace();
    } finally {
      if (conn != null) {
        conn.disconnect();
        conn = null;
      }
    }
    return res;
  }

  /**
   * map to string
   * 
   * @param proMap
   * @return
   */
  public static String getPro(Map<String, Object> proMap) {
    StringBuffer sb = new StringBuffer();
    Set<String> set = proMap.keySet();
    for (String key : set) {
      sb.append(key + "=" + proMap.get(key) + "&");
    }
    String par = null;
    if (sb.length() > 0) par = sb.toString().substring(0, sb.length() - 1);
    return par;
  }



}
