package net.ddns.satsukies.transition_tuning;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by satsukies on 2017/07/16.
 */

public class MyApplication extends Application {

  private static MyApplication instance = null;

  @Override public void onCreate() {
    super.onCreate();

    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());

    instance = this;
  }

  public static MyApplication getInstance() {
    return instance;
  }
}
