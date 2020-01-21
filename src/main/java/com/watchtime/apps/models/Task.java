package com.watchtime.apps.models;

import java.util.Date;

public class Task {
private String name;
public Task(String string, Date date, int i) {
	// TODO Auto-generated constructor stub
	this.name = string;
	this.duration = i;
	this.startTime = date;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getStartTime() {
	return startTime;
}
public void setStartTime(Date startTime) {
	this.startTime = startTime;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
private Date startTime;
private int duration; //in hours
}
