package com.app.alcohol.test;

import com.app.alcohol.entity.User;
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
//        Queue<List<Integer>> queue=new PriorityQueue<>((o1,o2)->o1.getById(0)-o2.getById(0));
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

//        String path = "/Users/gunannan/AlcoholProject/data/" + "44345" + "/" + "dhehr" + "/" + "ddt/" + DateUtil.getCurrentTime()+".txt";
//        System.out.println(path);
//        try {
//            File file = new File(path);
//            if (!file.getParentFile().exists())
//                file.getParentFile().mkdirs();
//            if (!file.exists())
//                file.createNewFile();
//            BufferedWriter out = new BufferedWriter(new FileWriter(file));
//            out.write("username,"+"q1,"+"q2\r\n");
//            out.write("nannan,"+"2,"+"3\r\n");
//            out.flush();
//            out.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        String a = "abc";
////        String b = "ab";
////        System.out.println(a.length());
//////        System.out.println(a.compareTo(b));
//////        PriorityQueue<String> priorityQueue=new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
//////        priorityQueue.add(a);
//////        priorityQueue.add(b);
//////        System.out.println(priorityQueue.peek());
//////        System.out.println("abc".substring(0,"abc".length()));
////        System.out.println(a.substring(0,a.length()));

//        ArrayList<Integer> l1=new ArrayList<>();
//        l1.add(Integer.MAX_VALUE);
//
//        ArrayList<Integer> l2=new ArrayList<>();
//        l2.add(0);
//
//        ArrayList<Integer> delta=new ArrayList<>();
//        int len1=l1.size();
//        int len2=l2.size();
//        int size=Math.max(len1,len2);
//        StringBuilder sb1=new StringBuilder();
//        StringBuilder sb2=new StringBuilder();
//        for(int i=0;i<size;i++){
//            if(i<len1&&i<len2){
//                sb1.append(l1.getById(i));
//                sb2.append(l2.getById(i));
//            }
//            else if(i>=len1){
//                l1.add(0);
//                sb1.append(0);
//                sb2.append(l2.getById(i));
//            }
//            else {
//                l2.add(0);
//                sb1.append(l1.getById(i));
//                sb2.append(0);
//            }
//        }
//        int i1=Integer.valueOf(sb1.toString());
//        int i2=Integer.valueOf(sb2.toString());
//
//        String dif=String.valueOf(i1-i2);
//        for(int i=0;i<size-dif.length();i++){
//            delta.add(0);
//        }
//        for(int i=0;i<dif.length();i++){
//            delta.add( Integer.parseInt(String.valueOf(dif.charAt(i))));
//        }
//        System.out.println(delta.toString());



//
//        ArrayList<Integer> l=new ArrayList<>();
//        l.add(1);
//        l.add(5);
//        l.add(9);
//        l.add(3);
//        ArrayList<Integer> inc=new ArrayList<>();
//        inc.add(0);
//        inc.add(1);
//        ArrayList<Integer> res=new ArrayList<>();
//
//        if(inc.size()>l.size()){
//            l.add(1);
//
//        }
//        else {
//            int dif=l.size()-inc.size();
//            for(int i=0;i<dif;i++){
//                inc.add(0);
//            }
//            System.out.println(inc.toString());
//            StringBuilder sb1=new StringBuilder();
//            StringBuilder sb2=new StringBuilder();
//            for(int i=0;i<l.size();i++){
//                sb1.append(l.getById(i));
//                sb2.append(inc.getById(i));
//            }
//            int i1=Integer.valueOf(sb1.toString());
//            int i2=Integer.valueOf(sb2.toString());
//            String add=String.valueOf(i1+i2);
//            for(int i=0;i<add.length();i++){
//                res.add(Integer.parseInt(String.valueOf(add.charAt(i))));
//            }
//        }
//
//        System.out.println(res.toString());
//
//
//        ArrayList<Integer> b=new ArrayList<>();
//        b.add(1);
//        b.addAll(l);
//        System.out.println(b.toString());
//
//        ArrayList<Integer> l=new ArrayList<>();
//        l.add(1);
//        l.add(3);
//        l.add(5);
//        l.add(9);
//        for(int i=0;i<l.size();i++){
//            if(l.getById(i)>4){
//                l.add(i,4);
//                break;
//            }
//        }
//        System.out.println(l.toString());
//
//
//        Date date=new Date();
//        String currentTime=DateUtil.convert(date);
//
//        System.out.println(currentTime.substring(0,10));
//
//        String a="0100";
//        System.out.println(Integer.parseInt(a));

//        System.out.println("".compareTo(" "));

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        Date d = new Date();
//        System.setProperty(“user.timezone”,”GMT +08″);
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
////        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
//        String date = simpleDateFormat.format(d);
//        System.out.println(date);



    }

}
