import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
import java.util.*;

public class Panel extends JPanel implements ActionListener{
	
	private JTextField pointAmount;
	private JButton addPoint, removePoint;
	
	public Panel() {
		this.setSize(777, 777);
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.black);
		
		pointAmount = new JTextField("0");
		pointAmount.setSize(80, 60);
		pointAmount.setLocation(0, 0);
		pointAmount.setOpaque(true);
		pointAmount.setBackground(Color.black);
		pointAmount.setFont(new Font("Arial", Font.BOLD, 30));
		pointAmount.setForeground(Color.red);
		pointAmount.setHorizontalAlignment(JTextField.CENTER);
		pointAmount.addActionListener(this);
		this.add(pointAmount);
		
		addPoint = new JButton("\u02C4");
		addPoint.setLocation(80, 0);
		addPoint.setSize(50, 30);
		addPoint.setBackground(Color.DARK_GRAY);
		addPoint.setFocusable(false);
		addPoint.setFont(new Font("Arial", Font.BOLD, 30));
		addPoint.setForeground(Color.LIGHT_GRAY);
		addPoint.setVerticalAlignment(JButton.TOP);
		addPoint.addActionListener(this);
		this.add(addPoint);
		
		removePoint = new JButton("\u02C5");
		removePoint.setLocation(80, 30);
		removePoint.setSize(50, 30);
		removePoint.setBackground(Color.DARK_GRAY);
		removePoint.setFocusable(false);
		removePoint.setFont(new Font("Arial", Font.BOLD, 30));
		removePoint.setForeground(Color.LIGHT_GRAY);
		removePoint.setVerticalAlignment(JButton.TOP);
		removePoint.addActionListener(this);
		this.add(removePoint);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int pointX, pointY, pointZ;
		double longitude, latitude;
		Sphere sphere = Main.getSphere();
		int pointRadius;
		int sphereRadius = sphere.radius;
		
		g.setColor(Color.white);
		g.drawOval(39, 39, sphereRadius * 2, sphereRadius * 2);
		g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
		g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
		
		List<Point> points = sphere.getPoints();
		for(Point point: points) {
			pointRadius = sphere.pointRadius;
			longitude = point.getLongitude() * Math.PI / 180;
			latitude = point.getLatitude() * Math.PI / 180;
			pointX = 39 + sphereRadius + (int) (sphere.radius * Math.cos(latitude) * Math.sin(longitude));
			pointY = 39 + sphereRadius - (int) (sphere.radius * Math.sin(latitude)); 
			pointZ = (int) (sphere.radius * Math.cos(latitude) * Math.cos(longitude));
			
			pointRadius += (int) ((double) pointRadius * 0.60 * ((double) (pointZ + sphereRadius) / sphereRadius / 2));
			g.setColor(new Color(255, 255, 255, 155 + (int) (100 * ((double) (pointZ + sphereRadius) / sphereRadius / 2))));
			g.fillOval(pointX - pointRadius, pointY - pointRadius, pointRadius * 2, pointRadius * 2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == pointAmount) {
			Main.setPoints(Integer.parseInt(pointAmount.getText()));
		}else if(e.getSource() == addPoint) {
			Main.addPoint();
			pointAmount.setText(String.valueOf(Integer.parseInt(pointAmount.getText()) + 1));
		}else if(e.getSource() == removePoint) {
			Main.removePoint();
			if(Integer.parseInt(pointAmount.getText()) > 0) {
				pointAmount.setText(String.valueOf(Integer.parseInt(pointAmount.getText()) - 1));
			}
		}
	}

}
