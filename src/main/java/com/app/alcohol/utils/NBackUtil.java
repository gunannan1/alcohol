package com.app.alcohol.utils;

import java.util.*;

public class NBackUtil {


    /**
     * this algorithm is stupid and  have some bugs
     * so i recommend to generate thousands of theses character string before experiment and save them to the database
     * when doing the experiments, we can randomly choose the strings in the database, it also saves time
     * @param nback 1 or 2 or 3
     * @param target As required,it is 33% of max size, 33% * 20 =7
     * @param max As required it is 20
     * @throws Exception
     */
    public static void generateOneBack(int nback,int target,int max,List<String> characters) throws Exception{

        List<Integer> nums=new ArrayList<>();
        List<Integer> targets=new ArrayList<>();
        String[] traits=new String[max];
        Arrays.fill(traits,"A");
        Random random=new Random();

        //add the nums and target characters to list
        for (int i=0;i<max;i++){
            nums.add(i);
        }

        while (true){

            //check the count of targets
            int count=0;
            for(int i=nback;i<max;i++){
                if(!traits[i].equals("A")&&traits[i].equals(traits[i-nback])){
                    count++;
                }
            }


//            System.out.println(Arrays.toString(traits));
//            System.out.println(nums);
//            System.out.println(count);


            //if count of targets equals to 7, break. if it is larger than 7, it means bug appears
            if(count==target){
                break;
            }
            if (count>target){
                System.err.println("There is a bug");
                throw new Exception("Bug:Target more than 7");
            }

            //randomly choose a num from all th max nums
            int index=random.nextInt(nums.size());
            int num=nums.get(index);
            String character=characters.get(random.nextInt(characters.size()));


            if(traits[num].equals("A")||traits[num].equals(character)){
                if(num+nback<max&&traits[num+nback].equals("A")){
                    if(count==target-1){
                        if((num+2*nback<max&&traits[num+2*nback].equals(character))||(num-nback>=0&&traits[num-nback].equals(character))){
                            continue;
                        }
                    }
                    if(count==target-2){
                        if((num+2*nback<max&&traits[num+2*nback].equals(character))&&(num-nback>=0&&traits[num-nback].equals(character))){
                            continue;
                        }

                    }
                    traits[num]=character;
                    traits[num+nback]=character;
                    nums.remove(index);
                    nums.remove((Object)(num+nback));

                }
                else if(num-nback>=0&&traits[num-nback].equals("A")){
                    if(count==target-1){
                        if((num-2*nback>=0&&traits[num-2*nback].equals(character))||(num+nback<max&&traits[num+nback].equals(character))){
                            continue;
                        }
                    }
                    if(count==target-2){
                        if((num-2*nback>=0&&traits[num-2*nback].equals(character))&&(num+nback<max&&traits[num+nback].equals(character))){
                            continue;
                        }
                    }

                    traits[num]=character;
                    traits[num-nback]=character;
                    nums.remove(index);
                    nums.remove((Object)(num-nback));
                }
                else if(traits[num].equals("A")&&num+nback<max&&character.equals(traits[num+nback])){
                    if(count==target-1){
                        if((num-nback>=0&&traits[num-nback].equals(character))){
                            continue;
                        }
                    }
                    traits[num]=character;
                    nums.remove(index);
                }
                else if(traits[num].equals("A")&&num-nback>=0&&character.equals(traits[num-nback])){
                    if(count==target-1){
                        if((num-nback>=0&&traits[num-nback].equals(character))){
                            continue;
                        }
                    }
                    traits[num]=character;
                    nums.remove(index);
                }

            }
            else {
                nums.remove(index);
            }


        }

        //after all the targets is insert to the traits, we need to fill all the "A" with needed characters
        for(int i=0;i<max;i++){

            if(traits[i].equals("A")){
                while (traits[i].equals("A")){
                    String insert=characters.get(random.nextInt(characters.size()));

                    if(i<nback){
                        if(!insert.equals(traits[i+nback])){
                            traits[i]=insert;
                        }

                    }
                    else if(i+nback>max-1){
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

        //record targets position
        for(int i=nback;i<max;i++){
            if(traits[i].equals(traits[i-nback])){
                targets.add(i);
            }
        }

        StringBuilder sb=new StringBuilder();
        String result=null;

        //check the target size again
        if(targets.size()==target){
            //convert the string array to string
            for(int i=0;i<max;i++){
                if(traits[i].equals("A")){
                    throw new Exception("Bug : A still exists");
                }
                sb.append(traits[i]);
            }
            result=sb.toString();

        }else {
            throw new Exception("Bug:target size not equals 7");
        }


        //Todo inset to the database


//
        System.out.println(targets.size());
        System.out.println(targets);
        System.out.println(Arrays.toString(traits));
        System.out.println(result);



    }

    public static void main(String[] args) {

        List<String> characters=new LinkedList<>();
        characters.add("P");
        characters.add("Q");
        characters.add("L");
        characters.add("K");
        characters.add("W");
        characters.add("C");
        characters.add("V");
        characters.add("Z");


        try {
            for (int i=0;i<12;i++){
                generateOneBack(1,7,20,characters);
                System.out.println(i);

            }

        }catch (Exception e){
            System.err.println("bug appear");
            e.printStackTrace();
        }



    }
}
