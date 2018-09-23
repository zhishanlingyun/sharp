package org.sharp.sd.lambda;

public class LambdaMain {

    BinaryOpt<Integer> opt_add = (x,y) -> x+y;


    public void opt(){
        int r = opt_add.add(1,2);
        System.out.println(r);
        BinaryOpt<Integer> opt_abst = (x,y) -> x-y;
        System.out.println(opt_abst.add(5,2));
        Opt opt = () -> {
            System.out.println("hello opt");
        };
        opt.run();
        new Run().run(()->{
            System.out.println("Run.run");
        });
    }

    public static void main(String[] args) {
        new LambdaMain().opt();
    }

    public class Run{
        public void run(Opt opt){
            opt.run();
        }
    }

    //lambda 只支持接口单方法的表达式
    interface BinaryOpt<T> {
        T add(T x,T y);
    }

    interface Opt{
        void run();
    }



}
