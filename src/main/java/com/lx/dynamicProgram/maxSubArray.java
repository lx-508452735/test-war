package com.lx.dynamicProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liux
 * @version 1.0
 */
public class maxSubArray {


    public static void main(String[] args) {
        int num[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int [][] triangle = {{2},{3,4},{6,5,7},{4,1,8,3}};
//        maxSubArray(num);

//        int i = climbStairs(3);

//        System.out.println(i);


    }

    /*    假设有一组正整数数组 nums，定义一个函数 maxSubArray(nums)，
    要求找到该数组中连续子数组的最大和。
    即在所有可能的连续子数组（至少包含一个元素）中，返回具有最大和的那个子数组的和。
    例如： 输入：nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4] 输出：6 解释：连续子数组 [4, -1, 2, 1] 的和最大，为 6。*/
    static void maxSubArray(int num[]){
        int maxSum = num[0];
        int currentSum = num[0];
        for (int i = 1; i < num.length; i++) {
            currentSum = Math.max(num[i], currentSum + num[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        System.out.println(maxSum);
    }



/*    70.爬楼梯  简单
    假设你正在爬楼梯。需要n阶你才能到达楼顶m每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢?
示例2 输入: n = 3层 输出: 3种爬法 解释:有三种方法可以爬到楼顶
    1. 1阶+1阶+1 阶
    2. 1阶+ 2 阶
    3. 2阶+ 1 阶

 如果是4层
    1+1+1+1
    1+1+2
    1+2+1
    2+1+1
    2+2

 */
    static int climbStairs(int n){
        if (n==1){
            return 1;
        }

        if (n==2){
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);

    }


/*   120 给定一个三角形 triangle ，找出自顶向下的最小路径和。
    每一步只能移动到下一行中相邻的结点上。相邻的结点在这里指的是下标与上一层结点下标相同或者等于上层结点下标 +1的两个结点。也就是说，如果正位于当前行的下标i，那么下一步可以移动到下一行的下标或i+1。
    示例 1
    输入: triangle = [[2],[3,4],[6,5,7]
            [4,1,8,3]]
    输出:11
    解释:如下面简图所示
        2
       3 4
      6 5 7
     4 1 8 3

     1
    1 9
   1 5 9
100 100 100 1

     1
    1 1
   1 1 1
  2 9 9 4
 9 9 1 9 9


    自顶向下的最小路径和为 11 (即2+3+5+1=11)。*/

    //        int [][] triangle = {{2},{3,4},{6,5,7},{4,1,8,3}};
    static int triangle(int triangle[][]){


        int minFinal = triangle[triangle.length-1][0];
        int sumCurrent = triangle[triangle.length-1][0];


        for (int i=0;i<triangle[triangle.length-1].length;i++){
            for (int j =triangle.length;j>-1;j++){
                if (i>0){
                    int min = Math.min(triangle[j][i - 1], triangle[j][i]);
                    sumCurrent = Math.min(min,min+sumCurrent);
                }else {

                }
                Math.min(minFinal,sumCurrent);
            }
        }
        return minFinal;
    }
    
}


