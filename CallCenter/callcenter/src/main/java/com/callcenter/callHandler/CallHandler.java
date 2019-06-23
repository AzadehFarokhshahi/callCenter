package com.callcenter.callHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.callcenter.employee.Employee;
import com.callcenter.employee.Manager;
import com.callcenter.employee.Respondent;
import com.callcenter.employee.Supervisor;

public class CallHandler {
	
	    private final int LEVELS = 3; // we have 3 levels of employees
	    private final int NUM_RESPONDENTS = 2; // we have 2 respondent
	    private final int NUM_SUPERVISOR = 1;
	    private final int NUM_MANAGER = 1;
	    
	    

	    List<Employee>[] employeeLevels = new ArrayList[LEVELS];
	    
	    private static Queue<Call> callQueues = new LinkedList<Call>();

	    public CallHandler() {
	        // create freshers
	        ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
	        for (int k = 0; k < NUM_RESPONDENTS; k++) {
	        	respondents.add(new Respondent());
	        }
	        employeeLevels[0] = respondents;


	        // create technical lead
	        ArrayList<Employee> supervisors = new ArrayList<Employee>(NUM_SUPERVISOR);
	        supervisors.add(new Supervisor()); // we have 1 technical lead
	        employeeLevels[1] = supervisors;

	        // create product manager
	        ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGER);
	        managers.add(new Manager()); // we have 1 product manager
	        employeeLevels[2] = managers;
	    }
	    
	    public Employee findCallHandler(Call call) {
	    	for (int level = 0; level < LEVELS; level++) {
	            List<Employee> employeeLevel = employeeLevels[level];
	            for (Employee emp : employeeLevel) {
	                if (emp.isFree()) {
	                	if(callQueues.isEmpty()) {
		                	System.out.println("emp assigned "+ emp.getRank());
		                	call.setHandler(emp);
		                	emp.assignNewCall(call);
		                	call.assigned();
		                    return emp;
	                	}
	                	else {
	                		assignCallfromQueue(emp);
	                	}
	                }
	                
	            }
	        }
	    	if(!call.getAssigned()) {
	    		addedToQueue(call);
	    	}
	        return null;
	    }
	    
	    
    
	     // employee got   free, look for a waiting call he/she can serve
	    public static  Employee assignCallfromQueue(Employee emp) {
		    System.out.println("emp assigned from queue"+ emp.getRank());
		    
	   		Call queueCall = callQueues.peek();
		    //callQueues.isEmpty();
	   		queueCall.setHandler(emp);
	   		emp.assignNewCall(queueCall);
	   		queueCall.assigned();
	   		callQueues.poll();
	   		System.out.println("emp assigned "+ emp.getRank());
           return emp;
	    }

		public static  Queue<Call> getCallQueues() {
			return callQueues;
		}

		
	   public boolean addedToQueue(Call call) {
			call.reply("Please wait");
	   		callQueues.add(call);
	   		return true;
	   }

	  
	

}
