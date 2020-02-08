package demo;

public class InnerClassDemo {

    class Dynamic{
        //动态内部类不能有静态成员变量
        //static int a = 0;

        //但是可以有静态常量
        static final int sfi = 0;

        //也可以有动态变量
        int dynamic = 0;

        Dynamic(){
            try{

            }catch (Exception e){

            }
        }
    }

    static class Static{
        static int si=0;
        static final int sfi=0;
        int d = 0;
    }



    public static void main(String[] args) {

    }

}
