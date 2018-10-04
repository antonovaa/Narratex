package ru.gameserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestRun {

    public static void main(String[] args) {
        String[] data = {"abc", "bac", "abc", "d", "et", "d", "et", "zzz"};

        String allE= String.join("", Arrays.asList(data));
        int[] p={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197};
        List<Character> distE=allE.chars().mapToObj(i -> (char)i).distinct().collect(Collectors.toList());

        HashMap<Character,Integer> characterIntegerMap =new HashMap<>();
        for (int index=0;index<distE.size();index++) {
            characterIntegerMap.put(distE.get(index),p[index]);// набор уникальных соответствий простых чисел
        }
        List<Integer> dataPMap = Arrays.asList(data).stream().map(e->e.chars().mapToObj(i -> (char)i).map(c->characterIntegerMap.get(c)).reduce((a, b)->a*b).get()).collect(Collectors.toList());//, произведение которых всегда будет давать уникальное значение, то есть abc= 2*3*5  acb=2*5*3

        System.out.println(characterIntegerMap);



        for (int i = 0; i < data.length; i++) {
            Arrays.sort(data[i].toCharArray());


        }




    }
}
