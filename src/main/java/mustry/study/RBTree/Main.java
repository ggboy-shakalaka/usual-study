package mustry.study.RBTree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        RBMap<Integer, Integer> map = new RBMap<>();
//        System.out.println(map.put(1,1));
//        System.out.println(map.put(2,2));
//        System.out.println(map.put(3,3));
//        System.out.println(map.put(4,4));
//        System.out.println(map.put(5,5));
//        System.out.println(map.put(6,6));
//        System.out.println(map.put(7,7));
//        System.out.println(map.put(8,8));
//        System.out.println(map.get(2));

//        Map<Pojo, String> map = new HashMap<>(3);
//        map.put(new Pojo(),"1");
//        map.put(new Pojo(),"2");
//        map.put(new Pojo(),"3");
//        map.put(new Pojo(),"4");
//        map.put(new Pojo(),"5");
//        map.put(new Pojo(),"6");
//        map.put(new Pojo(),"7");
//        map.put(new Pojo(),"8");
//        map.put(new Pojo(),"9");

        List<String> list = new ArrayList<String>();
        list.add("asd");
        Collections.sort(list);
    }

    static class Pojo {
        @Override
        public int hashCode() {
            return 43586012;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

}
