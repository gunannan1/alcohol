package com.app.alcohol.utils;

import java.util.*;

public class NBackUtil {

    public void generateOneBack(){
        Set<String> set=new HashSet<>();


    }

    public static void main(String[] args) {
        List<String> list=new LinkedList<>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        String[] traits=new String[20];
        Arrays.fill(traits,"A");
        Random random=new Random();
        int nback=2;

        for (int i=0;i<20;i++){
            list2.add(i);
        }

        list.add("P");
        list.add("Q");
        list.add("L");
        list.add("K");
        list.add("W");
        list.add("C");
        list.add("V");
        list.add("Z");


//        for(int i=0;i<20;i++){
//            traits[i]=list.get(random.nextInt(list.size()));
//        }
//
//        System.out.println(Arrays.toString(traits));
//
//        int num=0;
//
//        for(int i=2;i<20;i++){
//            if(traits[i].equals(traits[i-2])){
//                num++;
//                result.add(i);
//            }
//        }
//        System.out.println(result);
//        System.out.println(num);
//



        System.out.println(Arrays.toString(traits));


        int count=0;


        while (true){
            int out=0;
            for(int i=2;i<20;i++){
                if(!traits[i].equals("A")&&traits[i].equals(traits[i-2])){
                    out++;
                }
            }

            System.out.println(Arrays.toString(traits));
            System.out.println(out);

            if(out==7){
                break;
            }

            int index=random.nextInt(list2.size());
            int num=list2.get(index);
            String character=list.get(random.nextInt(list.size()));

            if(traits[num].equals("A")||traits[num].equals(character)){
                if(num+nback<20&&traits[num+nback].equals("A")){
                    traits[num]=character;
                    traits[num+nback]=character;
                    list2.remove(index);
                    count++;
                }
                else if(num-nback>=0&&traits[num-nback].equals("A")){
                    traits[num]=character;
                    traits[num-nback]=character;
                    list2.remove(index);
                    count++;
                }
            }



//            else if(traits[num].equals(character)){
//                if(num-nback>=0&&traits[num-nback].equals("A")){
//                    traits[num]=character;
//                    traits[num-nback]=character;
//                    list2.remove(index);
//                    count++;
//                }
//            }
//            else {
//
//            }


        }

        count=0;

        for(int i=2;i<20;i++){
            if(!traits[i].equals("A")&&traits[i].equals(traits[i-2])){
                count++;
                result.add(i);
            }
        }
//        System.out.println(result.size());
//        System.out.println(result);
        System.out.println(Arrays.toString(traits));



    }
}
