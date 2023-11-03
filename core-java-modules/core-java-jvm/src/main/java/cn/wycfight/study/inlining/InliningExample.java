package cn.wycfight.study.inlining;

import java.util.*;
import java.util.stream.Collectors;

public class InliningExample {

    public static final int NUMBERS_OF_ITERATIONS = 15000;

    public static void main(String[] args) {
        String reciptors = "01812,00008,01813,00008,00005,00008";
        String[] reciptorArray = reciptors.split(",");
        List<String> uniqueNumbers = new ArrayList<>(Arrays.asList(reciptorArray));
        reciptors = uniqueNumbers.stream().distinct().collect(Collectors.joining(","));
        System.out.println(reciptors);
    }

    private static long calculateSum(int n) {
        return new ConsecutiveNumbersSum(n).getTotalSum();
    }
}