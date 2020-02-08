package demo;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectionDemo {

    static class CollectionNullKeyDemo{
        static abstract class MapDemo {

            private Map<String, Integer> map;

            MapDemo(Map<String, Integer> map) {
                System.out.println(map.getClass().toString());
                this.map = map;
            }

            public void run() {
                try {
                    System.out.println("map.put(\"asd\", null);");
                    map.put("asd", null);
//                    System.out.println("map.remove(\"asd\");");
//                    map.remove("asd");
                } catch (Exception e) {
                    System.out.println("value不能为null");
                }

                try {
                    System.out.println("map.put(\"asd\", 1);");
                    map.put("asd", 1);
//                    System.out.println("map.remove(\"asd\");");
//                    map.remove("asd");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println("map.put(null, 1);");
                    map.put(null, 1);
                } catch (Exception e) {
                    System.out.println("key不能为null");
                }


            }

        }

        static class HashMapDemp extends MapDemo {


            public HashMapDemp() {
                super(new HashMap<>());
            }


            public static void main(String[] args) {
                new HashMapDemp().run();
            }
        }

        static class ConcurrentHashMapDemo extends MapDemo {

            public ConcurrentHashMapDemo() {
                super(new ConcurrentHashMap<>());
            }

            public static void main(String[] args) {
                new ConcurrentHashMapDemo().run();
            }
        }

        static class HashtableDemo extends MapDemo {
            public HashtableDemo() {
                super(new Hashtable<>());
            }

            public static void main(String[] args) {

                new HashtableDemo().run();
            }
        }
    }





    static class ListDemo {
        private List<Object> list;

        public ListDemo(List<Object> list) {
            System.out.println(list.getClass().toString());
            this.list = list;
        }

        public void run() {
            try {
                this.list.add("asd");
//            System.out.println(this.list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.list.add(null);
            System.out.println(this.list);
            } catch (Exception e) {
                System.out.println(this.getClass().getName() + ":Element不能为null");
//            e.printStackTrace();
            }
        }

    }


    static class ArrayListDemo extends ListDemo {
        public ArrayListDemo() {
            super(new ArrayList<>());

        }

        public static void main(String[] args) {
            new ArrayListDemo().run();
        }
    }

    static class LinkedListDemo extends ListDemo {
        public LinkedListDemo() {
            super(new LinkedList<>());
        }

        public static void main(String[] args) {
            new LinkedListDemo().run();
        }
    }

    static class VectorDemo extends ListDemo {
        public VectorDemo() {
            super(new Vector<>());
        }

        public static void main(String[] args) {
            new VectorDemo().run();
        }
    }

    static class COWArrayListDemo extends ListDemo {
        public COWArrayListDemo() {
            super(new CopyOnWriteArrayList<>());
        }

        public static void main(String[] args) {
            new COWArrayListDemo().run();
        }
    }

    static {
        System.out.println(CollectionDemo.class + " static");

    }

    static class HashMapSizeDemo {
        HashMap hashMap;

        public HashMapSizeDemo(HashMap hashMap) {
            this.hashMap = hashMap;
        }

        public HashMap getHashMap() {
            return hashMap;
        }

        public void setHashMap(HashMap hashMap) {
            this.hashMap = hashMap;
        }

        public static void main(String[] args) throws IllegalAccessException {
            HashMap hashMap = new HashMap(17);
            HashMapSizeDemo hashMapSizeDemo = new HashMapSizeDemo(hashMap);
//            System.out.println(hashMapSizeDemo.getHashMap().size());
            Class<HashMap> clazz = (Class<HashMap>) hashMap.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(hashMap);
                System.out.println(field.getName()+":"+object);

            }
            System.out.println("after put");
            hashMapSizeDemo.hashMap.put("asd", "asd");
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(hashMap);
                System.out.println(field.getName()+":"+object);
                if (field.getName().equals("table")){
                    Object[] objects = (Object[]) object;
                    System.out.println("table_length:"+objects.length);
                }
            }
        }
    }

    public static void main(String[] args) {


//        ArrayList<Integer> arrayList = new ArrayList<>();
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        Vector<Integer> vector = new Vector<>();
    }
}
