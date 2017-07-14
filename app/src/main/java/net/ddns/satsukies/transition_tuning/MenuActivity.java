package net.ddns.satsukies.transition_tuning;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.ddns.satsukies.transition_tuning.advanced.AdvancedMasterActivity;
import net.ddns.satsukies.transition_tuning.databinding.ActivityMenuBinding;
import net.ddns.satsukies.transition_tuning.normal.NormalMasterActivity;
import net.ddns.satsukies.transition_tuning.shared.SharedMasterActivity;

public class MenuActivity extends AppCompatActivity {

  ActivityMenuBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

    binding.buttonNormal.setOnClickListener(v -> NormalMasterActivity.startActivity(this));
    binding.buttonShared.setOnClickListener(v -> SharedMasterActivity.startActivity(this));
    binding.buttonAdvanced.setOnClickListener(v -> AdvancedMasterActivity.startActivity(this));
  }
}
