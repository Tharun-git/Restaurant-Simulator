public class Kitchen {
	
	public Timingscs clock;
	public Orders orders;
	private Cook[] cook;
	private Machine[] machine = new Machine[3];
	private String[] mNames = {"Burger", "Fries",        
								"Coke"};
	private int[] mTimes = {5, 3, 1};            

	Kitchen(Timingscs clock, int diners, int cooks) {
		this.clock = clock;
		orders = new Orders(diners);
		cook = new Cook[cooks];
		for(int c=0; c<cooks; c++) {
			cook[c] = new Cook("Cook"+c);
			cook[c].start();
		}

		for(int m=0; m<3; m++) {         
			machine[m] = new Machine(mNames[m], clock);
		}
		
	}
	

	private class Cook implements Runnable {
	   private Thread t;
	   private String name;
	   private Order order;
	   
	   Cook(String name) {	
	      this.name = name;
	   }
	   
	   public void run() {
		   while(orders.pending() > 0) {   
			   order = orders.release();
			   //System.out.println(name + " was assigned order " + order.id + " at time " + clock.getTime());   //changes
			   
			   while(!order.completed()) {  
				   for(int m=0; m<3; m++) {         
					   if(order.list[m] > 0 && !machine[m].isInUse()) {	
						   machine[m].use(name, order.id, mTimes[m]);
						   order.list[m]--;
					   } 
				   }				   
			   }
			   
			   order.deliveryTime = clock.getTime();
			   order.table.deliver(name);
		   }
	   }
	   
	   public void start () {
	      if (t == null) {
	         t = new Thread (this, name);
	         t.start ();
	      }
	     // System.out.println(name + " ready!");  changes
	   }
	}
	
}
