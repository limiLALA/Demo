package demo;

public class FinallyDemo {

    int demo(){
        try{
            throw new Exception();
        }catch (Exception e){
            System.out.println("catch");
            return 1;
        }finally {
            System.out.println("finally");
            try{
                throw new Exception("finally.catch");
            }catch (Exception e){
                System.out.println(e.getMessage());
                return 2;
            }finally {
                System.out.println("finally.finally");
            }

        }
    }

    int demo2(){
        int begin  = 2;
        try{
            if (begin == 2)
                throw new Exception();
        } catch (Exception e) {
            System.out.println(begin++);
            //return begin;
        }finally {
            System.out.println(begin++);
        }
        return begin;
    }

    int simpleDemo(int n){
        int a = 5;
        try{
            if (n==2)
                throw new RuntimeException();

        }catch (Exception e){
            return 0;
        }finally {
            a  = 3;
            ;
        }
        return 1;
    }

    public static void main(String[] args) {
        int ret = new FinallyDemo().demo();
        System.out.println(ret);
    }
}
