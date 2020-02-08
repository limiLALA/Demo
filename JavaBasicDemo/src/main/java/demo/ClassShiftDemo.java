package demo;

public class ClassShiftDemo{
    public static void main(String[] args) {
        // 向下转型不会影响方法
        Super sub = new Sub();
        sub.nonstaticOverride();
        Sub s = (Sub)sub;
        s.nonstaticOverride();
        // 向下转型会影响变量域
        System.out.println("sub.classname:"+(sub).classname);
        System.out.println("(Sub)sub.classname:"+((Sub) sub).classname);
    }
}