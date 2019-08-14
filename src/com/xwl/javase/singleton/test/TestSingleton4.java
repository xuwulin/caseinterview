package com.xwl.javase.singleton.test;

import com.xwl.javase.singleton.Singleton4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestSingleton4 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		Singleton4 si1 = Singleton4.getInstance();
		Singleton4 si2 = Singleton4.getInstance();
		System.out.println(si1 == si2); // true
		System.out.println(si1); // com.xwl.javase.singleton.Singleton4@1540e19d
		System.out.println(si2); // com.xwl.javase.singleton.Singleton4@1540e19d
		*/

		Callable<Singleton4> c = new Callable<Singleton4>() {
			@Override
			public Singleton4 call() throws Exception {
				return Singleton4.getInstance();
			}
		};

		// 创建线程池
		ExecutorService es = Executors.newFixedThreadPool(2);
		Future<Singleton4> f1 = es.submit(c);
		Future<Singleton4> f2 = es.submit(c);
		
		Singleton4 s1 = f1.get();
		Singleton4 s2 = f2.get();

		// 可能使true,也可能是false（线程安全问题）
		System.out.println(s1 == s2); // true/false
		System.out.println(s1);
		System.out.println(s2);
		
		es.shutdown();
	}
}
