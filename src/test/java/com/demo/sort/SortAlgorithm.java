package com.demo.sort;

import org.junit.Test;

public class SortAlgorithm {

    /**
     * 创建日期: 2019年7月25日 创建人: zhb 说明: 直接插入排序
     *
     */
    @Test
    public void demo() {
        int[] arr = {1,54,6,3,78,34,12,45,56,100};
        int temp = 0;
        int j = 0;
        for (int i = 1, len = arr.length; i < len; i++) {
            j = i - 1;
            temp = arr[i];
            for (; j >= 0 && temp < arr[j]; j--) {
                arr[j+1] = arr[j]; // 把比temp大的数整体后移一个单位
            }
            arr[j+1] = temp;
        }
        
        for (int i : arr) {
            System.out.println(i);
        }
    }
    
    /**
     * 创建日期: 2019年7月25日 创建人: zhb 说明: 希尔排序（最小增量排序）
     *
     */
    @Test
    public void demo2(){
        int a[]={1,54,6,3,78,34,12,45,56,100};
        double d1=a.length;
        int temp=0;
        while(true){
            d1= Math.ceil(d1/2);
            int d=(int) d1;
            for(int x=0;x<d;x++){
                for(int i=x+d;i<a.length;i+=d){
                    int j=i-d;
                    temp=a[i];
                    for(;j>=0&&temp<a[j];j-=d){
                    a[j+d]=a[j];
                    }
                    a[j+d]=temp;
                }
            }
            if(d==1)
                break;
        }
        for(int i=0;i<a.length;i++)
            System.out.println(a[i]);
    }
    
    @Test
    public void demo3(){
        int a = 6;
        int i = 5;
        int j = 2;
        for (; i < a && i > j ; i--) {
            System.out.println(i);
        }
    }
    
    
}
