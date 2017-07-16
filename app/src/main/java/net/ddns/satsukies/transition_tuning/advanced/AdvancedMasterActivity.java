package net.ddns.satsukies.transition_tuning.advanced;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import net.ddns.satsukies.transition_tuning.Dispatcher;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.ActivityAdvancedMasterBinding;

public class AdvancedMasterActivity extends AppCompatActivity {

  private static final String EXTRA_ID = "extra_id";
  private static final String EXTRA_QUALITY = "extra_quality";

  private AdvancedMasterStore masterStore;
  //private AdvancedMasterAction masterAction;
  private AdvancedMasterAdapter adapter;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, AdvancedMasterActivity.class);

    context.startActivity(intent);
  }

  public static void startActivityWithTransition(Context context,
      ActivityOptionsCompat optionsCompat) {
    Intent intent = new Intent(context, AdvancedMasterActivity.class);

    context.startActivity(intent, optionsCompat.toBundle());
  }

  ActivityAdvancedMasterBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_advanced_master);
    masterStore = new AdvancedMasterStore(Dispatcher.getInstance());

    adapter = new AdvancedMasterAdapter(this, masterStore, v -> {
      Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();

      if (v.getTransitionName() == null) {
        ViewCompat.setTransitionName(v, "image");
      }

      ActivityOptionsCompat optionsCompat =
          ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, v.getTransitionName());

      AdvancedDetailActivity.startActivityWithTransition(this, optionsCompat);
    });

    binding.recyclerList.setAdapter(adapter);
  }
}