package com.xwl.javase.arithmetic;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @date 2019-10-21 18:32
 * @description 字符串input1包含字符串input2输出true，否则输出false
 */
public class Demo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input1 = sc.next();
        String input2 = sc.next();

        StringBuilder sb = new StringBuilder();
        sb.append(input1).append(input2);
        String input1AndInput2 = sb.toString();
        char[] chars = input1AndInput2.toCharArray();
        for (char aChar : chars) {
            if (aChar >= 'A' && aChar <= 'Z') {

            } else {
                System.out.println("输出提示：字符串input1和字符串input2中元素的值域为26个大写字母！");
                return;
            }
        }

        if (input1.length() < 5 || input2.length() < 5) {
            System.out.println("输出提示：字符串input1的长度和字符串input2的长度不小于5!");
            return;
        }
        if (input1.length() <= input2.length()) {
            System.out.println("输出提示：字符串input1的长度要大于字符串input2的长度!");
            return;
        }

        char[] chars1 = input1.toCharArray();
        char[] chars2 = input2.toCharArray();

        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char char1 : chars1) {
            set1.add(char1);
        }

        for (char char2 : chars2) {
            set2.add(char2);
        }

        List<Character> collect1 = set1.stream().sorted().collect(Collectors.toList());
        List<Character> collect2 = set2.stream().sorted().collect(Collectors.toList());

        if (collect1.containsAll(collect2)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
