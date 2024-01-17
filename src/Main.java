import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Set<Integer> result1 = addElement(new HashSet<>());
        Set<Integer> result2 = removeElement(result1);
        Set<Integer> result3 = addElement(new HashSet<>(result2));
        Set<Integer> result4 = addElement(new HashSet<>(result2));
        Set<Integer> result5 = addElement(new HashSet<>(result2));
        String result6 = checkDuplicate(result1, result3);
        String result7 = checkDuplicate(result1, result4);
        String result8 = checkDuplicate(result1, result5);

        System.out.println("문제1) 랜덤한 숫자 만들기");
        System.out.println("답안1) 1행 => " + result1 + "\n");
        System.out.println("문제2) 6개 일치 숫자 중에 5개를 무작위로 선별하기");
        System.out.println("답안2) " + result2 + "\n");
        System.out.println("문제3) 2번에서 선택된 숫자들 이외의 것으로 나머지 숫자를 생성 하여 한 행 완성하기");
        System.out.println("답안3) 2행 => " + result3);
        System.out.println("답안3) 3행 => " + result4);
        System.out.println("답안3) 4행 => " + result5 + "\n");
        System.out.println("문제4) 3번에서 생성한 행과 기존의 행 간에 중복 검사하기");
        System.out.println("답안4) 1행-2행 중복 요소 : " + result6);
        System.out.println("답안4) 1행-3행 중복 요소 : " + result7);
        System.out.println("답안4) 1행-4행 중복 요소 : " + result8);
    }

    private static Set<Integer> addElement(Set<Integer> set) {

        while (set.size() < 6) {
            set.add((int) (Math.random() * 45) + 1);
        }

        return set;
    }

    private static Set<Integer> removeElement(Set<Integer> set) {

        List<Integer> list = new ArrayList<>(set);
        list.remove((int) (Math.random() * 6));

        return new HashSet<>(list);
    }

    private static String checkDuplicate(Set<Integer> set1, Set<Integer> set2) {

        return new ArrayList<>(set1).stream()
                .filter(el -> set2.contains(el))
                .collect(Collectors.toSet())
                .toString()
                .replace("[", "")
                .replace("]", "");
    }
}