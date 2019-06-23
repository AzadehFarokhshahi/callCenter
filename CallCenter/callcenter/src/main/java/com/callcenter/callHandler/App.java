package com.callcenter.callHandler;

import com.callcenter.employee.Manager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Manager mg = new Manager();
        System.out.println(mg.getRank());
        CallHandler ch = new CallHandler();
         Call call1 = new Call();
         Call call2 = new Call();
         Call call3 = new Call();
         Call call4 = new Call();
       ch.findCallHandler(call1);
       ch.findCallHandler(call2);
       ch.findCallHandler(call3);
       ch.findCallHandler(call4);
     //  System.out.println(call4.getHandler());
     //  call1.disconnect();
       Call call5 = new Call();
       ch.findCallHandler(call5);
       Call call6 = new Call();
       ch.findCallHandler(call6);
       Call call7 = new Call();
       ch.findCallHandler(call7);
       call3.disconnect();
       call4.disconnect();
       
       
       
    //    call.setHandler(resp1);
    //    resp1.assignNewCall(call);
        
    }
}
