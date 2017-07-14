package net.ddns.satsukies.transition_tuning.advanced;

import java.util.Arrays;
import java.util.List;
import net.ddns.satsukies.transition_tuning.Dispatcher;

/**
 * Created by satsukies on 2017/07/14.
 */

public class AdvancedMasterStore {

  //private List<String> dataList = Collections.emptyList();
  private List<String> dataList = Arrays.asList("1", "2", "3", "4", "5", "6");
  private Dispatcher dispatcher;

  public AdvancedMasterStore(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public Dispatcher getDispatcher() {
    return dispatcher;
  }

  public List<String> getDataList() {
    return dataList;
  }
}
