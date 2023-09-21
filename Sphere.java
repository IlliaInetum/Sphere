import java.util.*;

public class Sphere {
	
	private Random rng;
	
	private List<Point> points;
	public final int pointRadius = 50;
	
	public final int radius = 350;
	
	public Sphere() {
		
		rng = new Random();
		points = new ArrayList<>();
	}
	
	public void updateSphere() {
		
		if(points.size() > 0) {
			for(Point point: points) {
				point.updatePoint(this);
			}
		}
	}
	
	public void addPoint() {
		
		points.add(new Point(180 - rng.nextDouble(360), 90 - rng.nextDouble(180)));
	}
	
	public void removePoint() {
		
		if(points.size() != 0) {
			points.remove(points.get(points.size() - 1));
		}
	}
	
	public List<Point> getPoints(){
		return points;
	}
}
