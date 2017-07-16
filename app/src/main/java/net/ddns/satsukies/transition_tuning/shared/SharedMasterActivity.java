package net.ddns.satsukies.transition_tuning.shared;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import net.ddns.satsukies.transition_tuning.Dispatcher;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.ActivitySharedMasterBinding;

public class SharedMasterActivity extends AppCompatActivity {

  private static final String EXTRA_ID = "extra_id";
  private static final String EXTRA_QUALITY = "extra_quality";

  private SharedMasterStore masterStore;
  //private SharedMasterAction masterAction;
  private SharedMasterAdapter adapter;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, SharedMasterActivity.class);

    context.startActivity(intent);
  }

  public static void startActivityWithTransition(Context context,
      ActivityOptionsCompat optionsCompat) {
    Intent intent = new Intent(context, SharedMasterActivity.class);

    context.startActivity(intent, optionsCompat.toBundle());
  }

  ActivitySharedMasterBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_master);
    masterStore = new SharedMasterStore(Dispatcher.getInstance());

    adapter = new SharedMasterAdapter(this, masterStore, v -> {
      Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();

      if (v.getTransitionName() == null) {
        v.setTransitionName("image");
      }

      ActivityOptionsCompat optionsCompat =
          ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, v.getTransitionName());

      SharedDetailActivity.startActivityWithTransition(this, optionsCompat);
    });

    binding.recyclerList.setAdapter(adapter);
  }
}