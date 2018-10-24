package org.sharp.transfer;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CmpTest {

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(5);
        final LocalCmp cmp = new LocalCmp();
        for(int i=1;i<=4;i++){
            final int j = i;
            exe.submit(new Runnable() {
                public void run() {
                    cmp.exportDB("my14463sc.mysql.jddb.com","skuprice_"+j,"skuprice");
                }
            });
        }
    }
    @Test
    public void export(){
        //P p1 = new P("1",null,"22","22");
        //P p2 = new P("1",null,"22","22");
       // System.out.println(p1.equals(p2));
    }

    @Test
    public void load(){
        Cmp cmp = new Cmp("my14463sc.mysql.jddb.com-skuprice_1.dat");
        List<P> ps = cmp.loadData(null);

        for(P p : ps){
            System.out.println(p);
        }
        System.out.println(ps.size());
    }

    @Test
    public void get(){
        /*CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "33051F1E1A40BB541918C4EB23BB4B89.s1");
        cookie.setVersion(0);
        cookie.setDomain("r.promo.jd.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        String resp = HttpService.get(cookieStore,"J_30448418817_U");*/
        Cmp cmp = new Cmp(null);
        P p = cmp.getResult("J_30448418817_U");
        System.out.println(p);
    }

    @Test
    public void f(){
        String f = "76.5000";
        System.out.println(new DecimalFormat("#").format(Double.valueOf(f)));
    }
    /*@Test
    public void exportDB(){
        String[] domains = {
                "my14463sc.mysql.jddb.com", *//*1-4*//*
                "my17478sc.mysql.jddb.com", *//*5-8*//*
                "my14464sc.mysql.jddb.com", *//*9-12*//*
                "my17479sc.mysql.jddb.com",*//*13-16*//*
                "my14465sc.mysql.jddb.com",*//*17-20*//*
                "my17480sc.mysql.jddb.com",*//*21-24*//*
                "my14466sc.mysql.jddb.com",*//*25-28*//*
                "my17481sc.mysql.jddb.com",*//*29-32*//*
                "my14467sc.mysql.jddb.com",*//*33-36*//*
                "my17482sc.mysql.jddb.com",*//*37-40*//*
                "my14468sc.mysql.jddb.com",*//*41-44*//*
                "my17483sc.mysql.jddb.com",*//*45-48*//*
                "my14469sc.mysql.jddb.com",*//*49-52*//*
                "my17484sc.mysql.jddb.com",*//*53-56*//*
                "my14470sc.mysql.jddb.com",*//*57-60*//*
                "my17485sc.mysql.jddb.com",*//*61-64*//*
        };
        final Map<String,String> rql = new LinkedHashMap<String, String>(30);
        rql.put("my14463sc.mysql.jddb.com", "1-4");
        rql.put("my17478sc.mysql.jddb.com", "5-8");
        rql.put("my14464sc.mysql.jddb.com", "9-12");
        rql.put("my17479sc.mysql.jddb.com","13-16");
        rql.put("my14465sc.mysql.jddb.com","17-20");
        rql.put("my17480sc.mysql.jddb.com","21-24");
        rql.put("my14466sc.mysql.jddb.com","25-28");
        rql.put("my17481sc.mysql.jddb.com","29-32");
        rql.put("my14467sc.mysql.jddb.com","33-36");
        rql.put("my17482sc.mysql.jddb.com","37-40");
        rql.put("my14468sc.mysql.jddb.com","41-44");
        rql.put("my17483sc.mysql.jddb.com","45-48");
        rql.put("my14469sc.mysql.jddb.com","49-52");
        rql.put("my17484sc.mysql.jddb.com","53-56");
        rql.put("my14470sc.mysql.jddb.com","57-60");
        rql.put("my17485sc.mysql.jddb.com","61-64");
        final LocalCmp cmp = new LocalCmp();
        ExecutorService exe = Executors.newFixedThreadPool(20);
        Iterator<String> it = rql.keySet().iterator();
        while (it.hasNext()){
            final String domain = it.next();
            String[] tbs = rql.get(domain).split("-");
            int s = Integer.valueOf(tbs[0]);
            int e = Integer.valueOf(tbs[1]);
            for(int k=s;k<=e;k++){
                final int j = k;
                exe.submit(new Runnable() {
                    public void run() {
                        cmp.exportDB(domain,"skuprice_"+j,"skuprice");
                    }
                });
            }
        }

    }*/

}
