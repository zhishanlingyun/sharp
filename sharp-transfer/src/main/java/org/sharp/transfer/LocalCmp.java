package org.sharp.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import jodd.io.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalCmp {

    private Logger log = LoggerFactory.getLogger(getClass());

    private CookieStore cookieStore;
    private String Path = "D:\\cmp";
    private static final int TB_COUNT = 256;

    public LocalCmp(){
        cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("sso.jd.com", "076b0a18dd9748e5aab4ce0376cc076f");
        cookie.setVersion(0);
        cookie.setDomain("dbsv4.jd.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
    }

    public R export(String dbName,String domain,String sql,int count){
        String start = null;
        Map<String,String> param = new LinkedHashMap<String, String>();
        param.put("domain",domain);
        param.put("sql",sql);
        param.put("dbName",dbName);
        String resp = HttpService.post(cookieStore,"http://dbsv4.jd.com/dbquery/queryData",param);
        if(HttpService.Error.equals(resp)){
            log.error("ERROR-"+dbName+"-"+domain+"-"+sql);
        }
        JSONObject obj = JSON.parseObject(resp);
        JSONObject data = obj.getJSONObject("data");
        JSONArray objects = JSONArray.parseArray(data.getString("1"));
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<objects.size();i++){
            Map<String, String> listMap = JSON.parseObject(objects.get(i).toString(), new TypeReference<Map<String,String>>(){});
            start = listMap.get("id");
            sb.append(start).append("\t").append(listMap.get("skuId")).append("\t")
                    .append(listMap.get("salePrice")).append("\t").append(listMap.get("reward")).append("\t")
                    .append(listMap.get("purchasePrice")).append("\t").append(listMap.get("marketPrice")).append("\t")
                    .append(listMap.get("created")).append("\n");
        }
        writeToFile(Path,domain+"-"+dbName+".dat",sb.toString());
        count = count+objects.size();
        return new R(start,count);
    }

    public int exportTB(String domain,String dbname,String tbname){
        String startid = "0";
        int count = 0;
        do{
            //String domain = "my14463sc.mysql.jddb.com";
            String sql = "select  id, skuId, salePrice, reward, purchasePrice,marketPrice,created from "+tbname+" where id>="+startid+" and created>='2018-7-18 3:00' and created<'2018-7-18 10:00' order by id";
            //String dbName = "skuprice_1";
            R r = export(dbname,domain,sql,count);
            startid = r.getStart();
            if(StringUtils.isEmpty(startid)){
                System.out.println("导出表 "+dbname+" 结束");
                break;
            }
            startid = String.valueOf(Integer.valueOf(startid)+1);
            count = r.getCount();
        }while (true);
        return count;
    }

    public void exportDB(String domain,String dbname,String tbname){
        String[] db = dbname.split("_");
        Integer dbNo = Integer.valueOf(db[1]);
        System.out.println("表范围: [ "+String.valueOf((TB_COUNT*(dbNo-1)+1))+" - "+TB_COUNT*dbNo+" ]");
        int start = TB_COUNT*(dbNo-1)+1;
        int end = TB_COUNT*dbNo;
        String name = null;
        for(int i=start;i<=end;i++){
            name = tbname+"_"+i;
            System.out.println("开始导出 "+name);
            int count = exportTB(domain,dbname,name);
            System.out.println(name+" 导出结束... 总数: "+count);
        }
    }

    private void writeToFile(String path,String name,String content){
        File file = new File(path+File.separator+name);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileUtil.appendString(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
