package com.app.alcohol.utils;

import java.util.*;

public class NBackUtil {


    //this algorithm is stupid and may have some bugs,i'm not sure
    //so i recommend to generate thousands of theses character string before experiment and save them to the database
    //when doing the experiments, we can randomly choose the strings in the database, it also saves time
    public void generateOneBack(int nback) throws Exception{




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



        try {
            while (true){
                int out=0;
                for(int i=nback;i<20;i++){
                    if(!traits[i].equals("A")&&traits[i].equals(traits[i-nback])){
                        out++;
                    }
                }

                System.out.println(Arrays.toString(traits));
                System.out.println(out);

                if(out==7){
                    break;
                }
                if (out>7){
                    System.err.println("There is a bug");
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
                    }
                }

            }

        }catch (Exception e){
            System.err.println("there is a exception");
            e.printStackTrace();
        }


        for(int i=0;i<20;i++){

            if(traits[i].equals("A")){
                while (traits[i].equals("A")){
                    String insert=characters.get(random.nextInt(characters.size()));

                    if(i<nback){
                        if(!insert.equals(traits[i+nback])){
                            traits[i]=insert;
                        }

                    }
                    else if(i+nback>19){
                        if(!insert.equals(traits[i-nback])){
                            traits[i]=insert;
                        }

                    }
                    else if(!insert.equals(traits[i-nback])&&!insert.equals(traits[i+nback])){
                        traits[i]=insert;
                    }
                }

            }
        }


        for(int i=nback;i<20;i++){
            if(traits[i].equals(traits[i-nback])){
                result.add(i);
            }
        }


        System.out.println(result.size());
        System.out.println(result);
        System.out.println(Arrays.toString(traits));



    }
}
