package demo;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class DynamicProxyDemo {

    interface MyInterface {
        void show();

        void other();
    }
    static class MyInterfaceImpl implements MyInterface {

        @Override
        public void show() {
            System.out.println(this.getClass().getName());
        }

        @Override
        public void other() {

        }
    }

    static class JDKDyProxyDemo {

        static class MyInvocationHandler implements InvocationHandler{

            Object target;

            public void setTarget(Object target) {
                this.target = target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("aop pre");
                method.invoke(target);
                System.out.println("aop posts");
                return null;
            }
        }

        static class JDKDynamicProxyFactory{
            //使用默认的handler
             static <T> T generateProxy(T target){
                MyInvocationHandler invocationHandler = new MyInvocationHandler();
                invocationHandler.setTarget(target);
                return (T) Proxy.newProxyInstance(JDKDynamicProxyFactory.class.getClassLoader(), target.getClass().getInterfaces(),invocationHandler);
            }
            //自定义handler
            static <T> T generateProxy(T target, InvocationHandler handler){
                return (T) Proxy.newProxyInstance(JDKDynamicProxyFactory.class.getClassLoader(), target.getClass().getInterfaces(),handler);
            }
        }


        public static void main(String[] args) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
            MyInterface myInterface = JDKDynamicProxyFactory.generateProxy(new MyInterfaceImpl());

            myInterface.show();
        }
    }
    static class MyClass {
        void show() {
            System.out.println(this.getClass().getName());
        }

        ;
    }


    static class CGLIBDyProxyDemo {
        static class CGLIBPRroxyHandler implements MethodInterceptor {

            /**
             * 代理对象
             */
            private Object targetObj;


            public Object createObject(Object obj) {
                this.targetObj = obj;
                Enhancer eh = new Enhancer();
                eh.setSuperclass(this.targetObj.getClass());
                eh.setCallback(this);
                return eh.create();
            }


            public Object createObject() {
                Enhancer eh = new Enhancer();
//                eh.setSuperclass(MyInterface.class);

                return eh.create();
            }


            public Object intercept(Object obj, Method arg1, Object[] arg2,
                                    MethodProxy proxy) throws Throwable {
                System.out.println("pre;" + CGLIBPRroxyHandler.class);
                Object object = proxy.invoke(this.targetObj, arg2);//这里如果换成obj 会陷入死循环中
                return object;
            }


        }

        public static void main(String[] args) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib");
            MyClass myClass = new MyClass();
            CGLIBPRroxyHandler cglibpRroxyHandler = new CGLIBPRroxyHandler();
            MyClass myProxy = (MyClass) cglibpRroxyHandler.createObject(myClass);
            myProxy.show();
        }
    }


}
