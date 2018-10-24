package org.sharp.transfer;

import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class d {

    private static ExecutorService exe = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "3549E7ADC970CEFA968A4889FDAD7207.s1");
        cookie.setVersion(0);
        cookie.setDomain("r.promo.jd.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        String[] skus = loadSku("E:\\sh\\sku.txt");
        System.out.println("sku size is "+skus.length);
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(skus.length);
        final CountDownLatch latch = new CountDownLatch(skus.length);
        for(String s: skus){
            queue.add(s);
        }
        for(int i=0;i<20;i++){
            exe.submit(new Runnable() {
                public void run() {
                    StringBuilder sb = new StringBuilder();
                    while (true){
                        String sku = null;
                        try {
                            sku = queue.take();
                            if(StringUtil.isBlank(sku)){
                                break;
                            }
                            String resp = HttpService.get(cookieStore,"J_"+sku+"_SKUA");
                            int c = count(resp.split("@@@")[0]);
                            if(c>100){
                                System.out.println(sku+"-->"+c);
                                sb.append(sku).append("\n");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            latch.countDown();
                        }

                    }
                    writeToFile("E:\\sh\\m.dat",sb.toString());
                }
            });
        }
        try {
            latch.await();
            System.out.println("---------end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static int count(String str){
        if(StringUtil.isBlank(str)){
            return 0;
        }
        return StringUtil.count(str,'=');
    }

    public static String[] loadSku(String file){
        File f = new File(file);
        try {
            return FileUtil.readLines(f,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeToFile(String path,String content){
        File file = new File(path);
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
