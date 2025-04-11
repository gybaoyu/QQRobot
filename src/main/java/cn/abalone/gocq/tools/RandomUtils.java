package cn.abalone.gocq.tools;

import java.util.*;

public class RandomUtils {
    public static ArrayList<Integer> getNumber(int l, int r, int count){
        ArrayList<Integer> set = new ArrayList<>();
        Random random = new Random();
        while (set.size() < count) {
            int num = random.nextInt(r-l + 1) + l;
            if (!set.contains(num))set.add(num);
        }
        return set;
    }
}
