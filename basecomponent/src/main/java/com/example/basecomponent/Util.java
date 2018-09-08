package com.example.basecomponent;

public class Util {

    public static  int getABCCount(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            char cs =s.charAt(i);
            if((cs>='a'&& cs<='z') || ((cs>='A'&& cs<='Z')) || ((cs>='0'&& cs<='9')) ){
                count++;
            }
        }
        return count;
    }

    public static  int getChCount(String s){

        int count =0;
        String Reg="^[\u4e00-\u9fa5]{1}$";  //汉字的正规表达式
        for(int i=0;i<s.length();i++){
            String b=Character.toString(s.charAt(i));
            if(b.matches(Reg))
                count++;
        }

        return count;
    }
}
