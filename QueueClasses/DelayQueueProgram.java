package com.FlynautSaaS.QueueClasses;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedPacket implements Delayed
{
	private final String order;
    private final long startTime;
     
    public DelayedPacket(String packet, long delay) {
        this.order = packet;
        this.startTime = System.currentTimeMillis() + delay;
    }
	@Override
	public int compareTo(Delayed o) {
		if(this.startTime < ((DelayedPacket)o).startTime)
		{
			return -1;
		}
		if(this.startTime > ((DelayedPacket )o).startTime)
		{
			return 1;
		}
		return 0;
	}
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	 @Override
	    public String toString() {
	        return order;
	    } 	
}

public class DelayQueueProgram {
	public static void main(String[] args) {
		
		DelayQueue<DelayedPacket> packetQueue = new DelayQueue<>();
		
		packetQueue.add(new DelayedPacket("Order 1", 500));
		packetQueue.add(new DelayedPacket("Order 2", 600));
		packetQueue.add(new DelayedPacket("Order 3", 100));
		
		while(! packetQueue.isEmpty())
		{
			try
			{
				DelayedPacket packet = packetQueue.take();
				System.out.println("Sending " + packet);
				
			}catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				
			}
		}
		
	}
}