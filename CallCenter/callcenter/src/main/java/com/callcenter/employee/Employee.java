package com.callcenter.employee;

import com.callcenter.callHandler.Call;
import com.callcenter.callHandler.CallHandler;

public abstract class Employee {
	CallHandler callHandler;

    int rank; // 0- respondent, 1 - supervisor, 2 - manager

    public boolean free;
	private Call currentCall = null;

	public Employee(int rank) {

        this.rank = rank;
	}
	
	public void callCompleted() {
		
		currentCall = null;
		callHandler = null;
	}
	
	
	public void assignNewCall(Call call) {
		free = false;
		currentCall = call;
				
	}
	
	public boolean isFree() {
		return currentCall == null;
	}
	
	public int getRank() {
		return rank;
	}

}
