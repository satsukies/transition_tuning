package net.ddns.satsukies.transition_tuning.shared;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.LayoutHorizontalListBinding;

/**
 * Created by satsukies on 2017/07/14.
 */

public class SharedMasterAdapter extends ArrayAdapter<String> {
  private SharedMasterStore masterStore;
  private OnClickListener onClickListener;
  private AppCompatActivity activity;
  private LinearLayoutManager manager;

  private SharedRecyclerAdapter adapter;

  private static final int LAYOUT_LIST = R.layout.layout_horizontal_list;

  public interface OnClickListener {
    void onClickItem(View v);
  }

  public SharedMasterAdapter(@NonNull AppCompatActivity activity, SharedMasterStore store,
      OnClickListener onClickListener) {
    super(activity, LAYOUT_LIST, store.getDataList());
    this.activity = activity;
    this.masterStore = store;
    this.onClickListener = onClickListener;
  }

  @NonNull @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    String str = masterStore.getDataList().get(position);

    LayoutHorizontalListBinding binding;

    if (convertView == null) {
      binding = DataBindingUtil.inflate(LayoutInflater.from(activity), LAYOUT_LIST, parent, false);
    } else {
      binding = DataBindingUtil.getBinding(convertView);
    }

    binding.listTitle.setText(str + "番目のレイアウト");

    manager = new LinearLayoutManager(activity);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);

    adapter = new SharedRecyclerAdapter(activity, position, masterStore.getDataList(),
        v -> onClickListener.onClickItem(v));

    binding.contentList.setAdapter(adapter);
    binding.contentList.setLayoutManager(manager);

    return binding.getRoot();
  }
}
