package com.callcenter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.callcenter.callHandler.Call;
import com.callcenter.callHandler.CallHandler;

public class TestCallCenter {
	
	  CallHandler callHandller = new CallHandler();
      Call call1 = new Call();
      Call call2 = new Call();
      Call call3 = new Call();
      Call call4 = new Call();
      Call call5 = new Call();
      Call call6 = new Call();
      
     
	
  //@Test(priority = 0)
  public void respondent(Call call) {
	
	  callHandller.findCallHandler(call);
      Assert.assertTrue(call.getAssigned());
      Assert.assertEquals(call.getHandler(),0);
  }
  
  //@Test(priority = 1)
  public void supervisor(Call call) {
	
	  callHandller.findCallHandler(call);
      Assert.assertTrue(call.getAssigned());
      Assert.assertEquals(call.getHandler(),1);
  }
  
  //@Test(priority = 2)
  public void manager(Call call) {
	
	  callHandller.findCallHandler(call);
      Assert.assertTrue(call.getAssigned());
      Assert.assertEquals(call.getHandler(),2);
  }
  
 // @Test(priority = 3)
  public void ifAddedToQueue(Call call) {	  
	  callHandller.findCallHandler(call);
	  Assert.assertFalse(call.getAssigned());
      Assert.assertTrue(callHandller.addedToQueue(call));
      
  }
  
  
  /*
   * In this test case if all the respondents are busy,
   * next call will be transfered to the supervisor
   */
  @Test(priority = 1)
  public void testCase1() {
	  respondent(call1);
	  respondent(call2);
	  supervisor(call3);
	  call1.endCall();
	  call2.endCall();
	  call3.endCall();
  }
  
  /*
   * In this test case if all the respondents and supervisors are busy,
   * next call will be transfered to the manager
   */
  @Test(priority = 2)
  public void testCase2() {
	  respondent(call1);
	  respondent(call2);
	  supervisor(call3);
	  manager(call4);
	  call1.endCall();
	  call2.endCall();
	  call3.endCall();
	  call4.endCall();
  }
  
 
  
  /*
   * In this test case if all the employees are busy and
   * the manager disconnected,
   * next call will be transfer to the manager
   */
  @Test(priority = 3)
  public void testCase3() {
	  respondent(call1);
	  respondent(call2);
	  supervisor(call3);
	  manager(call4);
	  call4.disconnect();
	  Assert.assertFalse(call4.getAssigned());
	  callHandller.findCallHandler(call6);
	  Assert.assertTrue(call6.getAssigned());
      Assert.assertEquals(call6.getHandler(),2);
	  call1.endCall();
	  call2.endCall();
	  call3.endCall();
	  call4.endCall();
	  call6.endCall();
	
  }
  // Note: Test case 4 and 5 can not be executed at the same time
  /*
   * In this test case all the employees are busy, 
   * the next call will store in the queue.
   */
  //@Test(priority = 4)
  public void testCase4() {
	  respondent(call1);
	  respondent(call2);
	  supervisor(call3);
	  manager(call4);
	  ifAddedToQueue(call5);
	  call1.endCall();
	  call2.endCall();
	  call3.endCall();
	  call4.endCall();

  }
  
  /*
   * In this test case all the employees are busy, 
   * there are calls in the queue,
   * after disconnecting one of the employee,
   * next call transfer to him
   */
  @Test(priority = 5)
  public void testCase5() {

	  respondent(call1);	  
	  respondent(call2);
	  supervisor(call3);
	  manager(call4);
	  ifAddedToQueue(call5);
	  ifAddedToQueue(call6);
	  call3.disconnect();
	  Assert.assertTrue(call5.getAssigned());
      Assert.assertEquals(call5.getHandler(),1);
      call1.endCall();
	  call2.endCall();
	  call4.endCall();

	  
  }
}
