package com.dp.state;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Context extends AbstractState
{

    private State state;

    @Override
    public void checkEvent(Context context)
    {
        state.checkEvent(context);
        getCurrentState();
    }

    @Override
    public void checkFailEvent(Context context)
    {
        state.checkFailEvent(context);
        getCurrentState();

    }

    @Override
    public void makePriceEvent(Context context)
    {
        state.makePriceEvent(context);
        getCurrentState();

    }

    @Override
    public void acceptOrderEvent(Context context)
    {
        state.acceptOrderEvent(context);
        getCurrentState();

    }

    @Override
    public void notPeopleAcceptEvent(Context context)
    {
        state.notPeopleAcceptEvent(context);
        getCurrentState();

    }

    @Override
    public void payOrderEvent(Context context)
    {
        state.payOrderEvent(context);
        getCurrentState();

    }

    @Override
    public void orderFailureEvent(Context context)
    {
        state.orderFailureEvent(context);
        getCurrentState();

    }

    @Override
    public void feedBackEvent(Context context)
    {
        state.feedBackEvent(context);
        getCurrentState();

    }

    @Override
    public String getCurrentState()
    {
        System.out.println("当前状态:  " + state.getCurrentState());
        return state.getCurrentState();
    }
}
