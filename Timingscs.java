import java.util.Timer;
import java.util.TimerTask;

public class Timingscs {

	private int time = 0;
	private Timer timer = new Timer();
	private Schedule schedule;
	
	Timingscs() {}
	
	
	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			//System.out.println(time);
			time++;
			if(time == schedule.nextArrivalTime()) {
				schedule.dinerArrives();
			}
		}
	};
	
	public void start(Schedule schedule) {
		this.schedule = schedule;
		timer.schedule(timerTask, 0, 120);
	}
	
	public synchronized int getTime() {
		return time;
	}
	
	public void stop() {
		timer.cancel();
		timerTask.cancel();
	}

}
