package org.cx.demo;

import java.util.*;

/**
 * @author grass
 * @date 2017/11/5
 */
public class ObservableDemo {

    public static void main(String[] args) {

        MyObservable observable = new MyObservable();
        //增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println(value);
            }
        });

        observable.setChanged();
        //发布者通知，订阅者是被动感知(推模式)
        observable.notifyObservers("hello world");

        echoIterator();
    }

    private static void echoIterator() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
        Iterator<Integer> iterable = values.iterator();
        //循环主动去获取 （拉模式）
        while (iterable.hasNext()) {
            System.out.println(iterable.next());
        }

    }

    public static class MyObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}

