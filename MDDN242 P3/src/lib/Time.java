package lib;

import processing.core.PApplet;

public class Time {
	
	private static Time me;
	
	private double timeStep, timeFrame, timeFactor;
	private double timeStamp;

	Time(PApplet papplet){
		me = this;
		timeStamp = papplet.millis();
		timeStep = 0;
		timeFrame = 0;
		timeFactor = 1;
	}
	
	static void update(PApplet papplet){
		float newTimeStamp = papplet.millis();
		me.timeFrame = newTimeStamp - me.timeStamp;
		me.timeStep = me.timeFrame * me.timeFactor;
		me.timeStamp = newTimeStamp;
	}
	
	public static double getTimeStep(){
		return me.timeStep / 1000;
	}
	
	public static double getTimeFrame(){
		return me.timeFrame / 1000;
	}
	
	public static double getTimeFactor(){
		return me.timeFactor;
	}
	
	public static double getTimeStamp(){
		return me.timeStamp / 1000;
	}
	
	public static void setTimeFactor(float timeFactor){
		me.timeFactor = timeFactor;
	}
}
