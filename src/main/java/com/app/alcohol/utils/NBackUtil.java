package com.app.alcohol.utils;

import java.util.*;

public class NBackUtil {

    public void generateOneBack(){
        Set<String> set=new HashSet<>();


    }

    public static void main(String[] args) {
        List<String> characters=new LinkedList<>();
        List<Integer> nums=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        String[] traits=new String[20];
        Arrays.fill(traits,"A");
        Random random=new Random();
        int nback=2;

        for (int i=0;i<20;i++){
            nums.add(i);
        }

        characters.add("P");
        characters.add("Q");
        characters.add("L");
        characters.add("K");
        characters.add("W");
        characters.add("C");
        characters.add("V");
        characters.add("Z");


//        for(int i=0;i<20;i++){
//            traits[i]=characters.get(random.nextInt(characters.size()));
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

        try {
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
                if (out>7){
                    throw new Exception("more than 7");
                }

                int index=random.nextInt(nums.size());
                int num=nums.get(index);
                String character=characters.get(random.nextInt(characters.size()));

                if(traits[num].equals("A")||traits[num].equals(character)){

                    if(num+nback<20&&traits[num+nback].equals("A")){
                        if(out==6){
                            if((num+2*nback<20&&traits[num+2*nback].equals(character))||(num-nback>=0&&traits[num-nback].equals(character))){
                                continue;
                            }
                        }
                        traits[num]=character;
                        traits[num+nback]=character;
                        nums.remove(index);
                        count++;
                    }
                    else if(num-nback>=0&&traits[num-nback].equals("A")){
                        if(out==6){
                            if((num-2*nback>=0&&traits[num-2*nback].equals(character))||(num+nback<20&&traits[num+nback].equals(character))){
                                continue;
                            }
                        }
                        traits[num]=character;
                        traits[num-nback]=character;
                        nums.remove(index);
                        count++;
                    }
                }



//            else if(traits[num].equals(character)){
//                if(num-nback>=0&&traits[num-nback].equals("A")){
//                    traits[num]=character;
//                    traits[num-nback]=character;
//                    nums.remove(index);
//                    count++;
//                }
//            }
//            else {
//
//            }


            }

        }catch (Exception e){
            e.printStackTrace();
        }


        for(int i=0;i<20;i++){

            if(traits[i].equals("A")){
                while (traits[i].equals("A")){
                    String insert=characters.get(random.nextInt(characters.size()));
                    if(i<nback&&!insert.equals(traits[i+nback])){
                        traits[i]=insert;
                    }
                    else if(i+nback>19&&!insert.equals(traits[i-nback])){
                        traits[i]=insert;
                    }
                    else if(!insert.equals(traits[i-nback])&&!insert.equals(traits[i+nback])){
                        traits[i]=insert;
                    }
                }

            }
        }


        for(int i=2;i<20;i++){
            if(traits[i].equals(traits[i-2])){
                result.add(i);
            }
        }


        System.out.println(result.size());
        System.out.println(result);
        System.out.println(Arrays.toString(traits));



    }
}
