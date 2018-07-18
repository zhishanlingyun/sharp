package org.sharp.transfer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpService {

    public static final String Error = "error";

    public static String post(CookieStore cookie,String url,Map<String,String> param){
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookie).build();
        HttpPost post = new HttpPost(url/*"http://dbsv4.jd.com/dbquery/queryData"*/);
        post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        post.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("domain", param.get("domain")/*"my14463sc.mysql.jddb.com"*/));
            params.add(new BasicNameValuePair("sql", param.get("sql")/*"select * from skuprice_256 order by id desc"*/));
            params.add(new BasicNameValuePair("dbName",param.get("dbName")/*"skuprice_1"*/));
            post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpResponse response = client.execute(post);
            if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
                String line = EntityUtils.toString(response.getEntity(),"UTF-8");
                return line;
                //System.out.println(line);
                /*JSONObject obj = JSON.parseObject(line);
                JSONObject data = obj.getJSONObject("data");
                System.out.println(data);
                JSONArray objects = JSONArray.parseArray(data.getString("1"));
                for(int i=0;i<objects.size();i++){
                    Map<String, String> listMap = JSON.parseObject(objects.get(i).toString(), new TypeReference<Map<String,String>>(){});
                    System.out.println(listMap.get("skuId"));
                }*/
            }else {
                return Error;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Error;
        }
    }

    public static String get(CookieStore cookie,String key){
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookie).build();
        HttpPost get = new HttpPost("http://r.promo.jd.com/redis/queryRedis.read");
        //get.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        //get.addHeader("Accept","text/plain, */*; q=0.01");
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("redisKey",key));
            params.add(new BasicNameValuePair("redisGroupCode","1"));
            get.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpResponse resp = client.execute(get);
            //System.out.println(resp);
            if(HttpStatus.SC_OK==resp.getStatusLine().getStatusCode()) {
                String line = EntityUtils.toString(resp.getEntity(), "UTF-8");
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Error;
        }
        return Error;
    }

}
