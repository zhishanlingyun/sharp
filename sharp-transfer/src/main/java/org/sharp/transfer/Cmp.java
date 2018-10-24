package org.sharp.transfer;

import jodd.io.FileUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cmp {

    private final static String Path = "D:\\cmp";
    CookieStore cookieStore;
    private String filename;
    private ExecutorService exe = Executors.newFixedThreadPool(10);

    public Cmp(String filename){
        cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "ADF27371D7EC0617F83183D24D329E15.s1");
        cookie.setVersion(0);
        cookie.setDomain("r.promo.jd.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        this.filename = filename;
    }

    public void process(){
        String[] skus = loadSku(this.filename);
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(skus.length);
        queue.addAll(Arrays.asList(skus));
        for(int p=0;p<10;p++){
            exe.submit(new Runnable() {
                public void run() {
                    try {
                        String sku = queue.take();
                        HttpService.get(cookieStore,sku);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public P getResult(String key){
        String result = HttpService.get(cookieStore,key);
        String[] r = result.split("\\n");
        if(null==r&&r.length<=0){
            return null;
        }
        String[] line = r[0].split(":");
        String jsp = line[2];
        jsp = jsp.substring(1,jsp.lastIndexOf("}"));
        //System.out.println(jsp);
        //String[] pr = jsp.split(",");
        P p = new P(line[0].trim(),jsp);
        return p;
    }

    public String[] loadSku(String file){
        File f = new File(Path+File.separator+this.filename);
        try {
            return FileUtil.readLines(f,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<P> loadData(String file){
        List<P> pl = null;
        File f = new File(Path+File.separator+this.filename);
        try {
            final String[] lines = FileUtil.readLines(f);
            if(null!=lines&&lines.length>0){
                pl = new ArrayList<P>(500);
                for(String line : lines){
                    if(StringUtils.isEmpty(line.trim())){
                        continue;
                    }
                    String[] len = line.split("\\t");
                    StringBuilder sb = new StringBuilder();
                    if(StringUtils.isEmpty(len[2])||"0.0000".equals(len[2])){
                        sb.append(",");
                    }else {
                        sb.append(new DecimalFormat("#").format(len[2]));
                    }
                    if(StringUtils.isEmpty(len[3])||"0.0000".equals(len[3])){
                        sb.append(",");
                    }else {
                        sb.append(",").append(new DecimalFormat("#").format(len[3]));
                    }
                    if(StringUtils.isEmpty(len[5])||"0.0000".equals(len[5])){
                        sb.append(",");
                    }else {
                        sb.append(",").append(new DecimalFormat("#").format(len[5]));
                    }
                    P p = new P("J_"+len[1]+"_U",sb.toString());
                    pl.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pl;
    }


}
