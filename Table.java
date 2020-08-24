

public class Table {
	private String name;
	private Order order;
	private Kitchen kitchen;
	
	Table(String name, Kitchen kitchen) {
		this.name = name;
		//System.out.println(name + " ready!");  changes
		this.kitchen = kitchen;
	}
	
	public synchronized void deliver(String cook) {
		System.out.println("["+order.deliveryTime+"] "+"Diner  " + order.id + "'s food is ready. Diner-" + order.id+ " starts to eat.");
		notifyAll();
	}
	
	public synchronized void occupy(String diner, Order order) {
		this.order = order;
	    System.out.println("["+kitchen.clock.getTime()+"] "+diner + " is seated at " + name);  //changes
	    
	    kitchen.orders.add(order);
	    
	    try {	// wait for cook to deliver order
	    	wait();
	    } catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }

	    int time;
		do {
			time = kitchen.clock.getTime();
		}
	    while(time - order.deliveryTime < 30);
	    
	    System.out.println("["+time+"] "+diner + " finishes eating."+diner +" leaves the restaurant");
	}
	
}
