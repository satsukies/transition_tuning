package net.ddns.satsukies.lazyloadimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by satsukies on 2017/07/15.
 */

public class LazyLoadImageView extends FrameLayout {

  private Context context;

  //attrs
  private String thumbnailUrl = null;
  private String targetUrl = null;
  private boolean disableMemoryCache = false;
  private boolean disableDiskCache = false;
  private boolean dontAnimate = false;
  private boolean dontTransform = false;
  private boolean autoLoad = true;

  //TODO: implement separate autoLoad control
  private boolean autoLoadThumbnail = true;
  private boolean autoLoadTarget = true;

  //inner views
  private ImageView imageView = null;

  //listeners
  private RequestListener thumbnailRequestListener = null;
  private RequestListener targetRequestListener = null;

  public LazyLoadImageView(Context context) {
    this(context, null);
  }

  public LazyLoadImageView(Context context, AttributeSet attrs) {
    super(context, attrs);

    this.context = context;

    init(context, attrs);

    imageView = new ImageView(context, attrs);
    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    addView(imageView);

    if (autoLoad) {
      loadImage();
    }
  }

  public LazyLoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    this.context = context;

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LazyLoadImageView);

    thumbnailUrl = typedArray.getString(R.styleable.LazyLoadImageView_src_thumbnail);
    targetUrl = typedArray.getString(R.styleable.LazyLoadImageView_src_target);

    disableMemoryCache =
        typedArray.getBoolean(R.styleable.LazyLoadImageView_disable_memory_cache, false);
    disableDiskCache =
        typedArray.getBoolean(R.styleable.LazyLoadImageView_disable_disk_cache, false);

    dontAnimate = typedArray.getBoolean(R.styleable.LazyLoadImageView_dont_animate, false);
    dontTransform = typedArray.getBoolean(R.styleable.LazyLoadImageView_dont_transform, false);

    autoLoad = typedArray.getBoolean(R.styleable.LazyLoadImageView_auto_load, true);
    autoLoadThumbnail =
        typedArray.getBoolean(R.styleable.LazyLoadImageView_auto_load_thumbnail, true);
    autoLoadTarget = typedArray.getBoolean(R.styleable.LazyLoadImageView_auto_load_target, true);

    typedArray.recycle();
  }

  private void loadImage() {
    if (imageView == null) return;

    RequestBuilder glideBuilder = Glide.with(context).load(targetUrl);

    if (thumbnailUrl != null) {
      glideBuilder.thumbnail(Glide.with(context)
          .load(thumbnailUrl)
          .listener(thumbnailRequestListener)
          .apply(createRequestOptions()));
    }

    glideBuilder.apply(createRequestOptions());
    glideBuilder.listener(targetRequestListener);

    glideBuilder.into(imageView);
  }

  private RequestOptions createRequestOptions() {
    RequestOptions options = new RequestOptions();

    if (dontAnimate) {
      options.dontAnimate();
    }

    if (dontTransform) {
      options.dontTransform();
    }

    options.skipMemoryCache(disableMemoryCache);

    if (disableDiskCache) {
      //TODO: Configurable DiskCacheStrategy.{AUTOMATIC|RESOURCE|DATA|ALL}
      options.diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    return options;
  }

  public void beginLoad() {
    loadImage();
  }

  public void setThumbnailRequestListener(RequestListener listener) {
    thumbnailRequestListener = listener;
  }

  public void setTargetRequestListener(RequestListener listener) {
    targetRequestListener = listener;
  }

  public ImageView getImageView() {
    return imageView;
  }
}
