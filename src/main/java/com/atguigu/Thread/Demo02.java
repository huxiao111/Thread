package com.atguigu.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
	
	private int number=0;
	
	private Lock lock =new ReentrantLock();
	
	private Condition condition=lock.newCondition();
	
	public void increment(){
		
		lock.lock();
		
		try {
			while (number!=0) {
				
				condition.await();
			}
			++number;
			
			System.out.println(Thread.currentThread().getName()+"------"+number);
		
			condition.signalAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}finally{
			
			lock.unlock();
		}
	}
	
	public void decrement(){
		
		lock.lock();
		
		try {
			
			while (number==0) {
				
				condition.await();
			}
				--number;
				
				System.out.println(Thread.currentThread().getName()+"-----"+number);
				
				condition.signalAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}finally{
			
			lock.unlock();
		}
	
	}

}

public class Demo02{
	
	public static void main(String[] args) {
		
		final ShareData sd = new ShareData();
		
		new Thread(new Runnable() {
			
			public void run() {
			
				for (int i = 0; i < 50; i++) {
					 
					try {
						
						Thread.sleep(1000);
						
						sd.increment();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
		},"AA").start();
		
		 new Thread(new Runnable() {
			
			public void run() {
				
				for (int i = 0; i < 40; i++) {
					
					try {
						
						Thread.sleep(1000);
						
						sd.decrement();
						
					} catch (InterruptedException e) {
			
						e.printStackTrace();
					}
					
				}
				
			}
		},"BB").start();
		 
		 new Thread(new Runnable() {
			
			public void run() {
				
				for (int i = 0; i < 40; i++) {
					
					try {
						
						Thread.sleep(1000);
						
						sd.increment();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			
			}
		},"CC").start();
		 
		  new Thread(new Runnable() {
			
			public void run() {
				
				for (int i = 0; i < 40; i++) {
					
					try {
						
						Thread.sleep(1000);
						
						sd.increment();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
				}
				
			}
		},"CC").start();
		  
		  new Thread(new Runnable() {
			
			public void run() {
				
				for (int i = 0; i <40; i++) {
					
					try {
						
						Thread.sleep(1000);
						
						sd.decrement();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
		},"DD").start();
	}
}
