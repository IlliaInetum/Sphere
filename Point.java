import java.util.Random;

public class Point {
	
	private Random rng;

	private double longitude;
	private double latitude;
	
	private double longitudeVelocity;
	private double latitudeVelocity;
	
	public Point(double longitude, double latitude) {
		
		rng = new Random();
		
		this.longitude = longitude;
		this.latitude = latitude;
		
		this.longitudeVelocity = 1 - rng.nextDouble(2);
		this.latitudeVelocity = 1 - rng.nextDouble(2);
	}
	
	public void updatePoint(Sphere sphere) {
				
		longitude += longitudeVelocity;
		latitude += latitudeVelocity;
		
		if(longitude >= 180) {
			longitude -= 360; 
		}else if(longitude <= -180) {
			longitude += 360;
		}
		
		if(latitude >= 90) {
			latitude -= (latitude - 90) * 2;
			latitudeVelocity = -latitudeVelocity;
			longitude += 180;
			if(longitude >= 180) {
				longitude -= 360; 
			}else if(longitude <= -180) {
				longitude += 360;
			}
		}else if(latitude <= -90) {
			latitude -= (latitude + 90) * 2;
			latitudeVelocity = -latitudeVelocity;
			longitude += 180;
			if(longitude >= 180) {
				longitude -= 360; 
			}else if(longitude <= -180) {
				longitude += 360;
			}
		}
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
}
