package com.xwl.javase.arithmetic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xwl
 * @date 2019-10-21 12:12
 * @description
 */
public class TestA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s1 = new String[n];
        String[] s2 = new String[13 * n];  //每个字符串不大于100个字符 最多可差分13个子串
        int j = 0;
        for (int i = 0; i < n; i++) {

            s1[i] = sc.next();

            if (s1[i].length() % 8 != 0) {
                s1[i] = s1[i] + "00000000";
            }

            while (s1[i].length() >= 8) {

                s2[j] = s1[i].substring(0, 8);  // 存到新的字符串
                j++;
                s1[i] = s1[i].substring(8);

            }
        }
        String[] s3 = new String[j];    // 升序不能有null 的单元所以需要转到新的字符串s3
        for (int k = 0; k < j; k++) {
            s3[k] = s2[k];
        }

        Arrays.sort(s3);  //升序
        for (int s = 0; s < j; s++) {
            System.out.println(s3[s]);   //打印输出
        }
    }
}
