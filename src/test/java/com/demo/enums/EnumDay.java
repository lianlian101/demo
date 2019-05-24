package com.demo.enums;

public enum EnumDay {

    MONDAY("星期一"), 
    TUESDAY("星期二"), 
    WEDNESDAY("星期三"), 
    THURSDAY("星期四"), 
    FRIDAY("星期五"), 
    SATURDAY("星期六"),
    SUNDAY("星期日");
    
    private String discription;
    
    private EnumDay(String discription){
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
    
    public String str(){
        String name2 = name();
        return name2;
    }
    
    public static void main(String[] args) {
        String discription2 = EnumDay.MONDAY.getDiscription();
        System.out.println(discription2);
        String str = EnumDay.MONDAY.str();
        System.out.println(str);
    }
    
}
