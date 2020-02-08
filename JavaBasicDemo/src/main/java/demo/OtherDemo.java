package demo;

class FuncArgsDemo{

    void func(int... arg){

        System.out.println(arg.getClass().getTypeName());
    }

    void funcWithT(Integer... arg){
        System.out.println(arg.getClass().getTypeName());
    }

    public static void main(String[] args) {
        FuncArgsDemo funcArgsDemo = new FuncArgsDemo();
        funcArgsDemo
                .func(1,2,5);
        funcArgsDemo.funcWithT(1,2,5);

    }
}

public class OtherDemo {
}
