import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static TimerTask task;
	private static Timer timer;
	private static final int fps = 100;
	
	private static Frame frame;
	private static Panel panel;
	private static Sphere sphere;

	public static void main(String[] args) {
		
		frame = new Frame();
		panel = new Panel();
		frame.add(panel);
		frame.setVisible(true);
		
		sphere = new Sphere();
		
		task = new TimerTask() {
			public void run() {
				sphere.updateSphere();
				panel.repaint();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 1000 / fps, 1000 / fps);
		
	}
	
	public static Sphere getSphere() {
		return sphere;
	}
	
	public static void setPoints(int pointAmount) {
		
		int pointDifference = pointAmount - sphere.getPoints().size();
		for(int i = 0; i < Math.abs(pointDifference); i++) {
			if(pointDifference > 0) {
				sphere.addPoint();
			}else {
				sphere.removePoint();
			}
		}
	}
	
	public static void addPoint() {
		sphere.addPoint();
	}
	
	public static void removePoint() {
		sphere.removePoint();
	}

}
