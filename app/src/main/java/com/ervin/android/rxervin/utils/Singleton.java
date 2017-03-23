package com.ervin.android.rxervin.utils;

/**
 * Created by Ervin on 2017/3/21.
 */


public class Singleton {
    //双锁单例
    private static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


    //饿汉式单例，没有懒加载，在Singleton加载时就创建了实例
    private static Singleton singleton = new Singleton();
    public static Singleton getSingleton(){
        return singleton;
    }


    //登记式单例，实现了懒加载
    private static class SingletonHolder{
        private static final Singleton SINGLETON = new Singleton();
    }
    public static Singleton getSingletonInstance(){
        return SingletonHolder.SINGLETON;
    }
}
