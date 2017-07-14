package net.ddns.satsukies.transition_tuning;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by satsukies on 2017/07/14.
 */

public class Dispatcher {

  private final EventBus dispatcherBus;
  private static Dispatcher self;

  private Dispatcher() {
    dispatcherBus = EventBus.getDefault();
  }

  public static Dispatcher getInstance() {
    if(self == null) {
      self = new Dispatcher();
    }

    return self;
  }

  public void dispatch(Object payload) {
    dispatcherBus.post(payload);
  }

  public void register(Object observer) {
    dispatcherBus.register(observer);
  }

  public void unregister(Object observer) {
    dispatcherBus.unregister(observer);
  }
}
