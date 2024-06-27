package com.FlynautSaaS.QueueClasses;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueu 
{
	public static void main(String[] args) 
	{
		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

		// Producer thread
		Thread producer = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				queue.add(i);
				System.out.println("Produced: " + i);
				try {
					Thread.sleep(300); 
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		// Consumer thread
		Thread consumer = new Thread(() -> {
			while (true) {
				Integer item = queue.poll();
				if (item != null) {
					System.out.println("Consumed: " + item);
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		producer.start();
		consumer.start();
	}

}
