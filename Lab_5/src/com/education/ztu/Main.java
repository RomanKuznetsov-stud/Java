package com.education.ztu;
import java.time.LocalDate;
import java.util.*;
public class Main {

    private static void printCollection(String title, Collection<Product> col) {
        System.out.println(title);
        if (col.isEmpty()) {
            System.out.println("  (empty)");
        } else {
            col.forEach(p -> System.out.println("  " + p));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Product apple = new Product("Apple", 10, LocalDate.now(), 20);
        Product banana = new Product("Banana", 15, LocalDate.now(), 30);
        Product pear = new Product("Pear", 18, LocalDate.now(), 15);
        Product plum = new Product("Plum", 22, LocalDate.now(), 12);
        Product orange = new Product("Orange", 25, LocalDate.now(), 20);

        divFunc();
        System.out.println("========== Task 3 ArrayList ==========\n");

        List<Product> list = new ArrayList<>();
        list.add(apple);
        list.add(banana);
        list.add(pear);
        list.add(banana);
        list.addAll(Arrays.asList(plum, orange));

        printCollection("Initial list:", list);

        System.out.println("get(1): " + list.get(1));
        System.out.println("indexOf Apple: " + list.indexOf(apple));
        System.out.println("lastIndexOf Banana: " + list.lastIndexOf(banana));
        System.out.println("contains Banana: " + list.contains(banana));
        System.out.println("size: " + list.size() + "\n");

        list.remove(banana);
        list.set(1, plum);
        list.sort(null);

        printCollection("After remove / set / sort:", list);

        printCollection("subList(0,2):", list.subList(0, 2));

        list.retainAll(Arrays.asList(apple, plum));
        printCollection("After retainAll:", list);

        list.clear();
        System.out.println("isEmpty after clear: " + list.isEmpty() + "\n");

        divFunc();
        System.out.println("========== Task 4 ArrayDeque ==========\n");

        Deque<Product> queue = new ArrayDeque<>();
        queue.push(apple);
        queue.offerLast(banana);
        queue.offerLast(pear);

        System.out.println("getFirst:  " + queue.getFirst());
        System.out.println("peekLast:  " + queue.peekLast());
        System.out.println("pop:       " + queue.pop());
        System.out.println("removeLast:" + queue.removeLast());
        System.out.println("pollLast:  " + queue.pollLast() + "\n");

        divFunc();
        System.out.println("========== Task 5 TreeSet ==========\n");

        TreeSet<Product> set = new TreeSet<>(
                Arrays.asList(apple, banana, pear, plum, orange)
        );

        System.out.println("first:\n  " + set.first());
        System.out.println("last:\n  " + set.last() + "\n");

        printCollection("headSet(Banana):", set.headSet(banana));
        printCollection("subSet(Apple, Plum):", set.subSet(apple, plum));
        printCollection("tailSet(Banana):", set.tailSet(banana));

        System.out.println("ceiling(Banana): " + set.ceiling(banana));
        System.out.println("floor(Banana):   " + set.floor(banana));
        System.out.println("higher(Banana):  " + set.higher(banana));
        System.out.println("lower(Banana):   " + set.lower(banana) + "\n");

        System.out.println("pollFirst: " + set.pollFirst());
        System.out.println("pollLast:  " + set.pollLast() + "\n");

        printCollection("descendingSet:", set.descendingSet());

        divFunc();
        System.out.println("========== Task 5 HashMap ==========\n");

        Map<String, Product> map = new HashMap<>();
        map.put("Apple", apple);
        map.putIfAbsent("Banana", banana);
        map.put("Pear", pear);

        System.out.println("get Apple: " + map.get("Apple"));
        System.out.println("containsKey Banana: " + map.containsKey("Banana"));
        System.out.println("containsValue Apple: " + map.containsValue(apple) + "\n");

        System.out.println("EntrySet:");
        for (Map.Entry<String, Product> e : map.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        map.clear();
        System.out.println("\nmap isEmpty after clear: " + map.isEmpty());

        divFunc();
        System.out.println("\n========== Task 6 Collections ==========\n");

        List<Product> colList = Arrays.asList(apple, banana, pear, plum, orange);

        Collections.sort(colList);
        printCollection("sort:", colList);

        System.out.println("binarySearch Banana: " + Collections.binarySearch(colList, banana) + "\n");

        Collections.reverse(colList);
        printCollection("reverse:", colList);

        Collections.shuffle(colList);
        printCollection("shuffle:", colList);

        System.out.println("max: " + Collections.max(colList));
        System.out.println("min: " + Collections.min(colList) + "\n");

        List<Product> dest = new ArrayList<>(Collections.nCopies(colList.size(), null));
        Collections.copy(dest, colList);
        printCollection("copy:", dest);

        Collections.rotate(colList, 2);
        printCollection("rotate:", colList);
        System.out.println("frequency Apple: " + Collections.frequency(colList, apple));

    }
    public static void divFunc() {
        System.out.println("\u001B[32m----------------------------------------\u001B[0m");
    }
}
