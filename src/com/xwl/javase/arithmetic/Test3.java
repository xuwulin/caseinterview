package com.xwl.javase.arithmetic;


import java.util.*;

/**
 * @author xwl
 * @date 2019-10-29 18:50
 * @description
 */
public class Test3 {
    public static void main(String[] args) {
        System.out.println("input:");
        Scanner scanner = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();
        do {
            String str = scanner.nextLine();
            // 输入空行表示输入结束
            if (str.equals("")) {
                break;
            }
            inputList.add(str);
        } while (true);

        // 将车牌如-n=AN2345作为map的key,checkin 和 checkout添加到集合中
        Map<String, List<String>> map = new HashMap<>();
        for (String s : inputList) {
            String[] s1 = s.split(" ");
            List list = new ArrayList();
            if (!map.containsKey(s1[2])) {
                list.add(s);
                map.put(s1[2], list);
            } else {
                map.get(s1[2]).add(s);
            }
        }

        // 将集合中的checkin放在下标为0的位置，checkout放在下标为1的位置
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List value = entry.getValue();
            if (value.size() > 1) {
                if (value.get(0).toString().startsWith("checkout")) {
                    Object temp = value.get(0);
                    value.set(0, value.get(1));
                    value.set(1, temp);
                }
            }
        }

        System.out.println("output:");
        int i = 1;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            // 牌照
            String license = value.get(0).split(" ")[2].substring(3);
            String in = value.get(0).split(" ")[1].substring(3);
            if (value.size() > 1) {
                String out = value.get(1).split(" ")[1].substring(3);
                System.out.println("record" + i + ":" + license + " " + in + " in " + out + " out");
            } else {
                System.out.println("record" + i + ":" + license + " " + in + " in");
            }
            i++;
        }
    }
}
