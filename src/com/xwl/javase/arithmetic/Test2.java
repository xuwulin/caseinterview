package com.xwl.javase.arithmetic;

import java.util.Scanner;

/**
 * @author xwl
 * @date 2019-10-21 12:22
 * @description
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入两个26进制数");
        String res1 = sc.next();
        String res2 = sc.next();
        int num1 = 0;
        int sum1 = 0;
        char ch1;
        for (int i = 0; i < res1.length(); i++) {
            ch1 = res1.charAt(i);
            switch (ch1) {
                case 'a':
                    num1 = 0;
                    break;
                case 'b':
                    num1 = 1;
                    break;
                case 'c':
                    num1 = 2;
                    break;
                case 'd':
                    num1 = 3;
                    break;
                case 'e':
                    num1 = 4;
                    break;
                case 'f':
                    num1 = 5;
                    break;
                case 'g':
                    num1 = 6;
                    break;
                case 'h':
                    num1 = 7;
                    break;
                case 'i':
                    num1 = 8;
                    break;
                case 'j':
                    num1 = 9;
                    break;
                case 'k':
                    num1 = 10;
                    break;
                case 'l':
                    num1 = 11;
                    break;
                case 'm':
                    num1 = 12;
                    break;
                case 'n':
                    num1 = 13;
                    break;
                case 'o':
                    num1 = 14;
                    break;
                case 'p':
                    num1 = 15;
                    break;
                case 'q':
                    num1 = 16;
                    break;
                case 'r':
                    num1 = 17;
                    break;
                case 's':
                    num1 = 18;
                    break;
                case 't':
                    num1 = 19;
                    break;
                case 'u':
                    num1 = 20;
                    break;
                case 'v':
                    num1 = 21;
                    break;
                case 'w':
                    num1 = 22;
                    break;
                case 'x':
                    num1 = 23;
                    break;
                case 'y':
                    num1 = 24;
                    break;
                case 'z':
                    num1 = 25;
                    break;
            }
            int chengfang = 0;
            chengfang = (int) Math.pow(26, res1.length() - 1 - i);
            num1 = num1 * chengfang;
            sum1 += num1;
        }

        int num2 = 0;
        int sum2 = 0;
        char ch2;
        for (int a = 0; a < res2.length(); a++) {
            ch2 = res2.charAt(a);
            switch (ch2) {
                case 'a':
                    num2 = 0;
                    break;
                case 'b':
                    num2 = 1;
                    break;
                case 'c':
                    num2 = 2;
                    break;
                case 'd':
                    num2 = 3;
                    break;
                case 'e':
                    num2 = 4;
                    break;
                case 'f':
                    num2 = 5;
                    break;
                case 'g':
                    num2 = 6;
                    break;
                case 'h':
                    num2 = 7;
                    break;
                case 'i':
                    num2 = 8;
                    break;
                case 'j':
                    num2 = 9;
                    break;
                case 'k':
                    num2 = 10;
                    break;
                case 'l':
                    num2 = 11;
                    break;
                case 'm':
                    num2 = 12;
                    break;
                case 'n':
                    num2 = 13;
                    break;
                case 'o':
                    num2 = 14;
                    break;
                case 'p':
                    num2 = 15;
                    break;
                case 'q':
                    num2 = 16;
                    break;
                case 'r':
                    num2 = 17;
                    break;
                case 's':
                    num2 = 18;
                    break;
                case 't':
                    num2 = 19;
                    break;
                case 'u':
                    num2 = 20;
                    break;
                case 'v':
                    num2 = 21;
                    break;
                case 'w':
                    num2 = 22;
                    break;
                case 'x':
                    num2 = 23;
                    break;
                case 'y':
                    num2 = 24;
                    break;
                case 'z':
                    num2 = 25;
                    break;
            }
            int chengfang2 = 0;
            chengfang2 = (int) Math.pow(26, res2.length() - 1 - a);
            num2 = num2 * chengfang2;
            sum2 += num2;
        }

        int sum = sum1 + sum2;
        int yushu = 0;
        int shang = sum;
        String res = "";
        while (shang != 0) {
            yushu = shang % 26;
            String yushu26 = "";
            switch (yushu) {
                case 0:
                    yushu26 = "a";
                    break;
                case 1:
                    yushu26 = "b";
                    break;
                case 2:
                    yushu26 = "c";
                    break;
                case 3:
                    yushu26 = "d";
                    break;
                case 4:
                    yushu26 = "e";
                    break;
                case 5:
                    yushu26 = "f";
                    break;
                case 6:
                    yushu26 = "g";
                    break;
                case 7:
                    yushu26 = "h";
                    break;
                case 8:
                    yushu26 = "i";
                    break;
                case 9:
                    yushu26 = "j";
                    break;
                case 10:
                    yushu26 = "k";
                    break;
                case 11:
                    yushu26 = "l";
                    break;
                case 12:
                    yushu26 = "m";
                    break;
                case 13:
                    yushu26 = "n";
                    break;
                case 14:
                    yushu26 = "o";
                    break;
                case 15:
                    yushu26 = "p";
                    break;
                case 16:
                    yushu26 = "q";
                    break;
                case 17:
                    yushu26 = "r";
                    break;
                case 18:
                    yushu26 = "s";
                    break;
                case 19:
                    yushu26 = "t";
                    break;
                case 20:
                    yushu26 = "u";
                    break;
                case 21:
                    yushu26 = "v";
                    break;
                case 22:
                    yushu26 = "w";
                    break;
                case 23:
                    yushu26 = "x";
                    break;
                case 24:
                    yushu26 = "y";
                    break;
                case 25:
                    yushu26 = "z";
                    break;
            }
            res = yushu26 + res;
            shang = shang / 26;
        }
        System.out.println(res);
    }
}
