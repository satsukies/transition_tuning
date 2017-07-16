package net.ddns.satsukies.transition_tuning.normal;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import net.ddns.satsukies.transition_tuning.Dispatcher;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.ActivityNormalMasterBinding;

public class NormalMasterActivity extends AppCompatActivity {

  private static final String EXTRA_ID = "extra_id";
  private static final String EXTRA_QUALITY = "extra_quality";

  private NormalMasterStore masterStore;
  //private NormalMasterAction masterAction;
  private NormalMasterAdapter adapter;

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, NormalMasterActivity.class);

    context.startActivity(intent);
  }

  public static void startActivityWithTransition(Context context,
      ActivityOptionsCompat optionsCompat) {
    Intent intent = new Intent(context, NormalMasterActivity.class);

    context.startActivity(intent, optionsCompat.toBundle());
  }

  ActivityNormalMasterBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_normal_master);
    masterStore = new NormalMasterStore(Dispatcher.getInstance());

    adapter = new NormalMasterAdapter(this, masterStore, v -> {
      Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();
      NormalDetailActivity.startActivity(this);
    });

    binding.recyclerList.setAdapter(adapter);
  }
}
