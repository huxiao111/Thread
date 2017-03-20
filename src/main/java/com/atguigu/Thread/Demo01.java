package com.atguigu.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{

	public Integer call() throws Exception {
		
		 System.out.println("11111111111111111111");
		 
		return 200;
	}

}

public class Demo01{
	
	  public static void main(String[] args) {
		
		   FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
		   
		   new Thread(futureTask).start();
		   
		   //
		   
		   try {
			   
			System.out.println(futureTask.get());
			
		} catch (InterruptedException e) {
		
			e.printStackTrace();
			
		} catch (ExecutionException e) {
		
			e.printStackTrace();
			
		}finally{
			
			//
			
		}
	}
}
