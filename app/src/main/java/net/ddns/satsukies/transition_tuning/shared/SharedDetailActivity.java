package net.ddns.satsukies.transition_tuning.shared;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.ActivitySharedDetailBinding;

public class SharedDetailActivity extends AppCompatActivity {

  private static final String EXTRA_ID = "extra_id";
  private static final String EXTRA_QUALITY = "extra_quality";

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, SharedDetailActivity.class);

    context.startActivity(intent);
  }

  public static void startActivityWithTransition(Context context,
      ActivityOptionsCompat optionsCompat) {
    Intent intent = new Intent(context, SharedDetailActivity.class);

    context.startActivity(intent, optionsCompat.toBundle());
  }

  ActivitySharedDetailBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getWindow().setSharedElementsUseOverlay(true);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_detail);

    ImageView imageView = binding.headerImage;
    imageView.setTransitionName("image");
    Glide.with(imageView.getContext())
        .load("http://imgur.com/K1oWDXu.png")
        .apply(new RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE))
        .thumbnail(Glide.with(imageView.getContext())
            .load("http://imgur.com/ZJNbigT.png")
            .apply(new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)))
        .into(imageView);  }
}