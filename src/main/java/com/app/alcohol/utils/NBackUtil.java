package com.app.alcohol.utils;

import com.app.alcohol.vo.NBackResponseVO;

import java.util.*;

/**
 * generate n-back trials and answer position
 */
public class NBackUtil {


    /**
     *generate n-back trials and answer position
     * @param nback 1 or 2 or 3
     * @param target As required,it is 33% of max size, 33% * 20 =7,can be changed if needed
     * @param max As required it is 20,can be changed if needed
     * @throws Exception
     */
    public static NBackResponseVO generateNBack(int nback, int target, int max) throws Exception{

        NBackResponseVO nBackResponseVO=new NBackResponseVO();
        List<String> characters=new LinkedList<>();

        //n-back only need theses characters
        characters.add("P");
        characters.add("Q");
        characters.add("L");
        characters.add("K");
        characters.add("W");
        characters.add("C");
        characters.add("V");
        characters.add("Z");

        List<Integer> nums=new ArrayList<>();
        List<Integer> targets=new ArrayList<>();
        String[] traits=new String[max];

        //fill A in all position first
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


            //if trait in this num
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

//        System.out.println(targets.size());
//        System.out.println(targets);
//        System.out.println(Arrays.toString(traits));
//        System.out.println(result);

        nBackResponseVO.setAnswers(targets);
        nBackResponseVO.setPermutation(Arrays.asList(traits));
//        System.out.println(nBackResponseVO.getAnswers().toString());
//        System.out.println(nBackResponseVO.getPermutation().toString());

        return nBackResponseVO;



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
        int min=20;
        NBackResponseVO nBackResponseVO=new NBackResponseVO();


        try {
            for (int i=0;i<10;i++){
                nBackResponseVO=generateNBack(2,6,20);
//                int temp=nBackResponseVO.getAnswers().get(5);
//                if(temp<min){
//                    min=temp;
//                    System.out.println(min);
//                    System.out.println(nBackResponseVO.getAnswers());
//                }

            }

        }catch (Exception e){
            System.err.println("bug appear");
            e.printStackTrace();
        }



    }
}
