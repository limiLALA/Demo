package demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface UserManager{
    void queryAll();
    void queryDetail();
    void create();
    void delete();
    void update();

}


class UserManagerImpl implements UserManager {
    @Override
    public void queryAll() {
        System.out.println("UserManagerImpl."+"queryAll");
    }

    @Override
    public void queryDetail() {
        System.out.println("UserManagerImpl."+"queryDetail");
    }

    @Override
    public void create() {
        System.out.println("UserManagerImpl."+"create");
    }

    @Override
    public void delete() {
        System.out.println("UserManagerImpl."+"delete");
    }

    @Override
    public void update() {
        System.out.println("UserManagerImpl."+"update");
    }
}

interface Player{
    void play();
}

class PlayerImpl implements Player {
    @Override
    public void play() {
        System.out.println("PlayImpl."+"play");
    }
}

class MyProxy implements InvocationHandler{

    private Object object;

    public Object newProxyInstance(Object object){
        this.object = object;
        return Proxy.newProxyInstance(this.object.getClass().getClassLoader(), this.object.getClass().getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        Object param = null;
        System.out.println("method name: "+ method.getName());
        //method.invoke(this.object);


        return null;
    }
}


public class ProxyDemo {
    public static void main(String[] args) {
        MyProxy myProxy = new MyProxy();
        UserManager manager = (UserManager) myProxy.newProxyInstance(new UserManagerImpl());
        manager.queryAll();
        Player player = (Player) myProxy.newProxyInstance(new PlayerImpl());
        player.play();

    }
}
