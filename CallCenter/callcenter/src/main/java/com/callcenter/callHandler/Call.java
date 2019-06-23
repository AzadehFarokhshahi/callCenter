package com.callcenter.callHandler;

import com.callcenter.employee.Employee;

public class Call {

	private boolean assigned = false;
	private Employee handler;
	
	public synchronized void setHandler(Employee e) {
		handler = e;
		this.handler.free =false;
	}
	
	public void reply(String message) {
		System.out.println(message);
	}
	
	public void assigned() {
		assigned = true;
		
	}
	
	public boolean getAssigned() {
		return assigned;
	}
	
	public int getHandler() {
		return handler.getRank();
	}
	
	public void disconnect() {
		this.handler.callCompleted();
		assigned = false;
		if(!CallHandler.getCallQueues().isEmpty()) {
			CallHandler.assignCallfromQueue(this.handler);
		}
	}	
	public void endCall() {
		this.handler.callCompleted();
		assigned = false;
	}

	
	
}
