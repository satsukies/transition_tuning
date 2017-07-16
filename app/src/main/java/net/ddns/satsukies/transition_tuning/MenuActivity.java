package net.ddns.satsukies.transition_tuning;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import net.ddns.satsukies.lazyloadimageview.LazyLoadImageView;
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

    LazyLoadImageView imageView = binding.image;

    imageView.setThumbnailRequestListener(new RequestListener() {
      @Override public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target,
          boolean isFirstResource) {
        return false;
      }

      @Override public boolean onResourceReady(Object resource, Object model, Target target,
          DataSource dataSource, boolean isFirstResource) {
        Log.d("image", "beginLoad");
        return false;
      }
    });

    imageView.beginLoad();
  }
}
