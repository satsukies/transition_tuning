package net.ddns.satsukies.transition_tuning.normal;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.StethoGlide;
import net.ddns.satsukies.transition_tuning.StethoGlideModule;
import net.ddns.satsukies.transition_tuning.Values;
import net.ddns.satsukies.transition_tuning.databinding.ActivityNormalDetailBinding;

public class NormalDetailActivity extends AppCompatActivity {

  private static final String EXTRA_ID = "extra_id";
  private static final String EXTRA_QUALITY = "extra_quality";

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, NormalDetailActivity.class);

    context.startActivity(intent);
  }

  public static void startActivityWithTransition(Context context,
      ActivityOptionsCompat optionsCompat) {
    Intent intent = new Intent(context, NormalDetailActivity.class);

    context.startActivity(intent, optionsCompat.toBundle());
  }

  ActivityNormalDetailBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_normal_detail);

    ImageView imageView = binding.headerImage;
    StethoGlide.with(imageView.getContext())
        .load(Values.URL_IMAGE_VERYVERY_HIGH_V2)
        .apply(new RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE))
        .thumbnail(StethoGlide.with(imageView.getContext())
            .load(Values.URL_IMAGE_LOW)
            .apply(new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)))
        .into(imageView);  }
}
