package demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeMath.log;


interface Evaluate {
    long getSize();

    long getClassSize();
}

class HasNoFunc {
    int a,b,c;
}


class HasFunc {
    void func() {

    }
//    void func2() {
//
//    }
//    void func3() {
//
//    }

}


class SubForSize extends SuperForSize {
    @Override
    void func() {

    }
}

class SuperForSize {
    void func() {

    }
}

@Slf4j
class ClassSizeDemo {
    public static void main(String[] args) {
//        HasNoFunc hasNoFunc = new HasNoFunc();

        log.info("hasNoFunc.class size:{}", RamUsageEstimator.sizeOf(HasNoFunc.class));
//        HasFunc hasFunc = new HasFunc();
        log.info("hasFunc.class size:{}", RamUsageEstimator.sizeOf(HasFunc.class));

        log.info("sub class size:{}", RamUsageEstimator.sizeOf(SubForSize.class));
        log.info("super class size:{}", RamUsageEstimator.sizeOf(SuperForSize.class));
    }
}

class EmptyForSize{
}

@Slf4j
public class ObjectSizeDemo {

    static class CollectionObjectSizeDemo{
        ArrayList<Integer> arrayList = new ArrayList<>(1);

        public static void main(String[] args) {
            System.out.println("hello");
            System.out.println(RamUsageEstimator.sizeOf(new CollectionObjectSizeDemo().arrayList));;
        }
    }

    static class ArrayObjectSizeDemo{
        int[] arr = new int[4];

        public static void main(String[] args) {
            System.out.println("hello");
            System.out.println(RamUsageEstimator.sizeOf(new ArrayObjectSizeDemo().arr));
        }
    }


    public static void main(String[] args) {
//        short s = (short) 0x8000;
        log.info("hasNoFunc.obj size:{}", RamUsageEstimator.sizeOf(new HasNoFunc()));
//        HasFunc hasFunc = new HasFunc();
        log.info("hasFunc.obj size:{}", RamUsageEstimator.sizeOf(new HasFunc()));

        log.info("SubForSize.obj size:{}", RamUsageEstimator.sizeOf(new SubForSize()));
        log.info("SuperForSize.obj size:{}", RamUsageEstimator.sizeOf(new SuperForSize()));
        log.info("EmptyForSize.obj size:{}", RamUsageEstimator.sizeOf(new EmptyForSize()));

    }
}
