package com.dp.state;

public class ClientTest
{

    public static void main(String[] args)
    {
        Context context = new Context();
        context.setState(new PublishState());
        System.out.println("main : " + context.getCurrentState());

//        //publish --> not pay
        context.acceptOrderEvent(context);
//        //not pay --> paid
        context.payOrderEvent(context);

//        try {
//        	context.checkFailEvent(context);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

    }

}