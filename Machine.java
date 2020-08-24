
public class Machine {
	private String name;
	private boolean inUse = false;
	private Timingscs clock;
	
	Machine(String name, Timingscs clock) {
		this.name = name;
		this.clock = clock;
		System.out.println(name + " ready!");
	}
	
	public synchronized boolean isInUse() {
		if(!inUse) {
			inUse = true;
			return false;
		}
		return inUse;
	}
	
	public void use(String cook, int id, long time) {
	    int endTime, startTime = clock.getTime();
	    System.out.println("["+startTime+"] "+"Diner-"+id+"'s order will be processed by "+cook);
	    System.out.println("["+startTime+"] "+cook + " takes the machine for " + name + " machine");
	    
	    while(clock.getTime() - startTime < time);
	    inUse = false;
	}
	
}



