package net.ddns.satsukies.transition_tuning.shared;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.StethoGlide;
import net.ddns.satsukies.transition_tuning.Values;
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

    supportPostponeEnterTransition();

    binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_detail);

    ImageView imageView = binding.headerImage;
    ViewCompat.setTransitionName(imageView, "image");
    StethoGlide.with(imageView.getContext())
        .load(Values.URL_IMAGE_VERYVERY_HIGH_V2)
        .listener(new RequestListener<Drawable>() {
          @Override public boolean onLoadFailed(@Nullable GlideException e, Object model,
              Target<Drawable> target, boolean isFirstResource) {
            return false;
          }

          @Override
          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
              DataSource dataSource, boolean isFirstResource) {
            supportStartPostponedEnterTransition();
            return false;
          }
        })
        .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
        .into(imageView);
  }
}
