//delete this
package ru.gameserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestRun {

    public static void main(String[] args) {
        String[] data={"abc", "bac", "abc", "d","et","d","et","zzz"};
        
        //получение количества одинакового набора символов

        Arrays.stream(data).map(e-> e.chars().mapToObj(x -> (char) x).sorted().collect(Collectors.toList())).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).forEach((r,t)-> System.out.println(""+r+" "+t));
    }
}
