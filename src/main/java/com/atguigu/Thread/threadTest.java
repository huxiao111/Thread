package com.atguigu.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
  
	private int number=30;
	
	private Lock lock =new ReentrantLock();
	
	public void sale(){
		
		  lock.lock();
		  
		  try {
			  
			  if (number>0) {
				
			Thread.sleep(1000);
			
			System.out.println(Thread.currentThread().getName()+"当前还剩:"+(number--)+"现在卖的第:"+number+"张票");
		
			  }
			  
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}finally{
			
			lock.unlock();
		}
	}
}

public class threadTest{
	
	public static void main(String[] args) {
		
		final Ticket t=new Ticket();
		
		new Thread(new Runnable() {
			
			public void run() {
       
				for (int i = 0; i < 40; i++) {
					
					t.sale();
				}
				 
			}
		},"AA").start();
		
		new Thread(new Runnable() {
			
			public void run() {
			
				 for (int i = 0; i < 40; i++) {
					 
					 t.sale();
				}
			}
		},"BB").start();
		
		
		new Thread(new Runnable() {
			
			public void run() {
				
				t.sale();
				
			}
		},"CC").start();
		
		new Thread(new Runnable() {
			
			public void run() {
				
				t.sale();
			}
		},"DD").start();
		
	}
	
}
