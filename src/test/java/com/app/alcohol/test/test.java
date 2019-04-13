package com.app.alcohol.test;

import com.app.alcohol.utils.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static void main(String[] args) {
//        List<List<Integer>> lists=new ArrayList<>();
//        Queue<List<Integer>> queue=new PriorityQueue<>((o1,o2)->o1.get(0)-o2.get(0));
//        Queue<Integer> queue1=new PriorityQueue<>((o1,o2)->o1-o2);
//        List<Integer> list1=new ArrayList<>();
//        List<Integer> list2=new ArrayList<>();
//        List<Integer> list3=new ArrayList<>();
//        List<Integer> list4=new ArrayList<>();
//
//
//        list1.add(2);
//        list1.add(1);
//
//        list2.add(4);
//        list2.add(2);
//
//        list3.add(1);
//        list3.add(2);
//
//        list4.add(3);
//        list4.add(1);
//
//        lists.add(list1);
//        lists.add(list2);
//        lists.add(list3);
//        lists.add(list4);
//
////        queue.offer(list1);
////        queue.offer(list2);
////        queue.offer(list3);
////        queue.offer(list4);
//
//        queue1.add(2);
//        queue1.add(4);
//        queue1.add(1);
//        queue1.add(3);
//
//        queue.addAll(lists);
//
//        System.out.println(lists);
//        System.out.println(queue);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());

//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(currentTime);
//        System.out.println(dateString);

        String path = "/Users/gunannan/AlcoholProject/data/" + "44345" + "/" + "dhehr" + "/" + "ddt/" + DateUtil.getCurrentTime()+".txt";
        System.out.println(path);
        try {
            File file = new File(path);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists())
                file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write("username,"+"q1,"+"q2\r\n");
            out.write("nannan,"+"2,"+"3\r\n");
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }






    }
}
