# 실행 결과 및 문제 풀이

## 실행 결과

![스크린샷 2024-01-17 오후 2 02 45](https://github.com/YoungHyun-Cho/mobile_factory/assets/72560095/d4c01ebc-9edd-4c02-bb59-e52446a1b0dd)

<br />

## 문제1) 랜덤한 숫자 만들기

```java
private static Set<Integer> addElement(Set<Integer> set) {

    while (set.size() < 6) {
        set.add((int) (Math.random() * 45) + 1);
    }

    return set;
}
```
- 랜덤 숫자를 저장할 자료구조로 Set을 선택하였습니다. 
  - 문제의 예시를 보고 아래와 같은 규칙을 확인했습니다.  
    - 1~45 범위의 숫자를 
    - 6개 뽑아야 하며,
    - 중복된 요소가 없고,
    - 정렬은 되어있지 않습니다. 
  - 이러한 규칙이 저는 로또 번호 추첨과 비슷하다고 생각했으며, Set을 사용하는 것이 용이할 것이라 판단했습니다.
    - 그 중에서도 **요소 추가 시 중복 검사를 내부적으로 해주며, 요소 정렬을 실시하지 않는 HashSet**을 Set의 구현체로 활용했습니다.
- `(int) (Math.random() * 45) + 1`
  - 랜덤한 숫자는 Math.random()을 통하여 생성하였으며, 그 결과는 0.0 이상, 1.0 미만의 난수입니다. (예 : 0.12..) 
  - 여기에 45를 곱하면 0.0이상, 45.0 미만의 난수가 됩니다. (예 : 0.12.. * 45 = 5.4..)
  - 이를 int형으로 형변환하면 정수가 됩니다. (예 : (int) 5.4... -> 5)
- `while (set.size() < 6) { ... }` 
  - 반복문이 순회하면서 1~45범위의 중복되지 않는 랜덤한 숫자를 뽑아, Set의 요소 개수가 6개가 될 때까지 추가합니다.

<br />

## 문제2) 6개 일치 숫자 중에 5개를 무작위로 선별하기
```java
private static Set<Integer> removeElement(Set<Integer> set) {

    List<Integer> list = new ArrayList<>(set);
    list.remove((int) (Math.random() * 6));

    return new HashSet<>(list);
}
```

- '**6개 중에 5개를 무작위로 선별한다**'는 것은 '**6개 중에 1개를 무작위로 골라서 삭제한다**'는 것과 같습니다. 
- Set의 삭제 메서드는 remove(Object o)로, 요소 객체의 값을 직접 전달해주어야 합니다. 
  - 이 방식으로 랜덤 요소 하나를 삭제하려면, Set 내에 존재하는 값이 나올 때까지 계속해서 랜덤으로 값을 뽑아주어야 되므로 비효율적입니다. 
- 따라서, Set에서 직접 요소를 삭제하는 것이 아니라, **일시적으로 List로 변환하고, 인덱스를 무작위로 뽑아 요소를 삭제한 뒤, 다시 HashSet으로 매핑**하는 방식으로 해결했습니다. 
  - `List<Integer> list = new ArrayList<>(set);` 
    - Set을 List로 변환합니다. 
  - `list.remove((int) (Math.random() * 6));`
    - List에서 랜덤 인덱스가 가리키는 요소를 삭제합니다. 
  - `return new HashSet<>(list);`
    - List를 다시 Set으로 변환합니다.

<br />

## 문제3) 2번에서 선택된 숫자들 이외의 것으로 나머지 숫자를 생성 하여 한 행 완성하기
```java
private static Set<Integer> addElement(Set<Integer> set) {

    while (set.size() < 6) {
        set.add((int) (Math.random() * 45) + 1);
    }

    return set;
}
```

  - 문제1에서 활용한 addElement 메서드를 재사용했습니다.
  - 문제2의 결과를 인자로 전달하면 부족한 하나의 요소를 1~45 사이의 중복되지 않는 랜덤 숫자로 채워서 리턴해줍니다.

<br />

## 문제4) 3번에서 생성한 행과 기존의 행 간에 중복 검사하기

```java
private static String checkDuplicate(Set<Integer> set1, Set<Integer> set2) {

    return new ArrayList<>(set1).stream()
            .filter(el -> set2.contains(el))
            .collect(Collectors.toSet())
            .toString()
            .replace("[", "")
            .replace("]", "");
}
```

- 중복 검사의 결과는 중복된 요소를 출력하는 것으로 규정했습니다.
- Stream을 활용하여, set1과 set2가 공통으로 가지고 있는 요소를 filter()로 추립니다.
    - toString()을 통해 문자열로 변환하면 \[3, 23, 14, 9, 44\]과 같이 변환됩니다. 
    - 앞뒤의 "\["와 "\]"를 replace()로 제거하면 중복된 요소를 출력할 포맷이 완성됩니다.
