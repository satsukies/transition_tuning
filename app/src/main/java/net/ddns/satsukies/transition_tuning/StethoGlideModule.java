package net.ddns.satsukies.transition_tuning;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.io.InputStream;
import okhttp3.OkHttpClient;

/**
 * Created by satsukies on 2017/07/16.
 */

@GlideModule(glideName = "StethoGlide")
public class StethoGlideModule extends AppGlideModule {
  @Override public void applyOptions(Context context, GlideBuilder builder) {
  }

  @Override public void registerComponents(Context context, Registry registry) {
    OkHttpClient client =
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
    registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
  }
}
