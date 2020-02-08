package demo;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//只能应用于类上
@Retention(RetentionPolicy.RUNTIME)//保存到运行时
@interface DBTable {
    String name() default "";
}

@DBTable(name = "MEMBER")
class Member {

}

public class AnnotationDemo {
    public static void main(String[] args) {
        Member member = new Member();
        Annotation[] annotations = member.getClass().getAnnotations();
        System.out.println("annotations.length:"+annotations.length);
        for (int i = 0; i < annotations.length; i++) {
            Annotation annotation = annotations[i];
            System.out.println(annotation);
            Class<? extends Annotation> aClass = annotation.annotationType();

            System.out.println(aClass.getName());

        }
    }
}
