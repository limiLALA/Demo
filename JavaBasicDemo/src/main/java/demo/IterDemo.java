package demo;

import java.util.ArrayList;
import java.util.Iterator;

public class IterDemo {
    static class SingleThreadIterDemo{

        static ArrayList<Integer> glist = new ArrayList<>();

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                glist.add(i);
            }

            ArrayList<Integer> list = new ArrayList<>(glist);
            try{
                for (int i = 0; i < list.size(); i++) {

                    list.remove(list.size()-1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            list = new ArrayList<>(glist);
            Iterator<Integer> iter = list.iterator();
            try {
                while (iter.hasNext()){
                    iter.next();
                    iter.remove();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            list = new ArrayList<>(glist);
            iter = list.iterator();
            try{
                while (iter.hasNext()){
                    iter.next();
                    list.remove(list.size()-1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
