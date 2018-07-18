package org.sharp.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExportTest {

    @Test
    public void foo(){
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("sso.jd.com", "076b0a18dd9748e5aab4ce0376cc076f");
        cookie.setVersion(0);
        cookie.setDomain("dbsv4.jd.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        HttpPost post = new HttpPost("http://dbsv4.jd.com/dbquery/queryData");
        post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        post.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("domain", "my14463sc.mysql.jddb.com"));
            params.add(new BasicNameValuePair("sql", "select * from skuprice_256 order by id desc"));
            params.add(new BasicNameValuePair("dbName","skuprice_1"));
            post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpResponse response = client.execute(post);
            if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
                String line = EntityUtils.toString(response.getEntity(),"UTF-8");
                //System.out.println(line);
                JSONObject obj = JSON.parseObject(line);
                JSONObject data = obj.getJSONObject("data");
                System.out.println(data);
                JSONArray objects = JSONArray.parseArray(data.getString("1"));
                for(int i=0;i<objects.size();i++){
                    Map<String, String> listMap = JSON.parseObject(objects.get(i).toString(), new TypeReference<Map<String,String>>(){});
                    System.out.println(listMap.get("skuId"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
