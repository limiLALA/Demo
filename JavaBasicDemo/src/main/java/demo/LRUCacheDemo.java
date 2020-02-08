package demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo {
    static class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int cap;
        public LRUCache(int cap){
            super(cap, 0.75f, true);
            this.cap = cap;
        }

        int get(int key){
            Integer val = super.get(key);
            return val==null?-1:val;
        }


        protected boolean removeEldestEntry(Map.Entry entry){
            return size()>this.cap;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));;       // 返回  1
        System.out.println(cache.put(3, 3));    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

    }
}
