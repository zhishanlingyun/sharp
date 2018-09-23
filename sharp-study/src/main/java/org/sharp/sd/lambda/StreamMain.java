package org.sharp.sd.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {


    public static void main(String[] args) {
        String[] artsArray = {"zhangsan_beijing","zhaosi_shanghai","wangwu_beijing"};
        List<String> arts = Arrays.asList(artsArray);
        long c1 = arts.stream().count();
        long c2 = arts.stream().filter(e -> {
            System.out.println(e);
            return e.endsWith("beijing");
        }).count();
        System.out.println("---------------");
        arts.stream().filter(e->e.endsWith("beijing")).forEach(e-> System.out.println(e));
        System.out.println(c1);
        System.out.println(c2);
        System.out.println("---------------");
        List<String> narts = arts.stream().map(e -> e.split("_")[0]).collect(Collectors.toList());
        narts.stream().forEach(e-> System.out.println(e));
        System.out.println("---------------");
        arts.stream().forEach(e-> System.out.println(e));
        Integer[] numsArray = {3,1,6,3,20,5,67};
        List<Integer> nums = Arrays.asList(numsArray);
        int max = nums.stream().max(Comparator.comparing(e->e)).get();
        int min = nums.stream().min(Comparator.comparing(e->e)).get();
        System.out.println(max);
        System.out.println(min);
        int sum = nums.stream().reduce((x,y)->x+y).get();
        System.out.println(sum);
        //(x, y) -> new Point(1, 2);
        Point p = new Point(1,2);
        List<Point> points = Stream.of(new Point(1,2),new Point(2,3),new Point(1,3)).collect(Collectors.toList());
        Point point = points.stream().max(Comparator.comparing(Point::getX)).get();
        System.out.println(point);
        points.stream().map(Point::getX).collect(Collectors.toList()).stream().forEach(e-> System.out.println(e));
        points.stream().map(Point::getX).collect(Collectors.toCollection(TreeSet::new)).stream().forEach(e-> System.out.println(e));
        Map<Boolean,List<Point>> mp = points.stream().collect(Collectors.partitioningBy(e->e.getX()>1));
        mp.forEach((b,e)->{
            //System.out.println("b is "+b);
            e.stream().forEach(t-> System.out.print(t+","));
            System.out.println();
        });

    }

}
