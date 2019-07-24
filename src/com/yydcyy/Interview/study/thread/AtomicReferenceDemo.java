package com.yydcyy.Interview.study.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by YYDCYY on 2019-07-23.
 */
class User{
    String userName;
    int age;
    public User(String n, int a){
        userName = n;
        age = a;
    }

    @Override
    public String toString() {
        return userName + " :: " + age;
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3); //Exception in thread "main" java.lang.NullPointerException  不赋值, 空指针

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString()); // true ,
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString()); // false
    }
}
