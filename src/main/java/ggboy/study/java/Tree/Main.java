package ggboy.study.java.Tree;

public class Main {
    public static void main(String[] args) {
        SelfMap<String, String> map = new SelfMap<String, String>();
        map.put("5", "t");
        map.put("3", "e");
        map.put("8", "i");
        map.put("1", "q");
        map.put("2", "w");
        map.put("4", "r");
        map.put("6", "y");
        map.put("7", "u");
        map.put("9", "o");

        System.out.println(map.get("8"));
        System.out.println(map.remove("8"));
        System.out.println(map.get("8"));
        System.out.println(map.size());
    }
}
