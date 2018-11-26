package com.jms.activemq.demo;

public class Main {
    public static void main(String args[]) {
        Producer producer = new Producer();
        Thread producerThread = new Thread(producer);
        producerThread.start();

        Consumer consumer = new Consumer();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
