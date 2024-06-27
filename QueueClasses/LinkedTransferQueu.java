package com.FlynautSaaS.QueueClasses;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueu {
	public static void main(String[] args) {
		TransferQueue<String> queue = new LinkedTransferQueue<>();

		// Producer thread
		Thread producer = new Thread(() -> {
			try {
				for(int i=0;i<=10;i++)
				{
					String item = "Item " + i;
					queue.transfer(item);
					System.out.println("Produced: " + item);
					Thread.sleep(500);
				}
			} catch (Exception e)
			{
				Thread.currentThread().interrupt();
			}
		});

		// Consumer thread
		Thread consumer = new Thread(() -> {
			try {
               while(true) {
                    String item = queue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(300);
               }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
		});

		producer.start();
		consumer.start();
	}
}
