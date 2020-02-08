package demo;

public class SynchronizeDemo {
    static class Res{
        Integer result = 0;

        public int getResult(){
            return result;
        }

        public void setResult(int result){
            synchronized (this.result){
                this.result = result;
            }

        }
    }


    public static void main(String[] args) {
        Res res = new Res();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (res.getResult()<100);
                System.out.println(Thread.currentThread()+":"+res.getResult());
            }).start();
        }
        new Thread(()->{
//            System.out.println(Thread.currentThread()+":"+"set res");
            res.setResult(100);
//            System.out.println(Thread.currentThread()+":"+"set res done");
        }).start();
    }
}
