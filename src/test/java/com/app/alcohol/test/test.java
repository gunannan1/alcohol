package com.app.alcohol.test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class test {
    public static void main(String[] args) {
        List<List<Integer>> lists=new ArrayList<>();
        Queue<List<Integer>> queue=new PriorityQueue<>((o1,o2)->o1.get(0)-o2.get(0));
        Queue<Integer> queue1=new PriorityQueue<>((o1,o2)->o1-o2);
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> list3=new ArrayList<>();
        List<Integer> list4=new ArrayList<>();


        list1.add(2);
        list1.add(1);

        list2.add(4);
        list2.add(2);

        list3.add(1);
        list3.add(2);

        list4.add(3);
        list4.add(1);

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);

//        queue.offer(list1);
//        queue.offer(list2);
//        queue.offer(list3);
//        queue.offer(list4);

        queue1.add(2);
        queue1.add(4);
        queue1.add(1);
        queue1.add(3);

        queue.addAll(lists);

        System.out.println(lists);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());



    }
}
