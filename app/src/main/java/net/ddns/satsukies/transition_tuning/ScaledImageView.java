package net.ddns.satsukies.transition_tuning;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by satsukies on 2017/07/14.
 */

public class ScaledImageView extends AppCompatImageView {

  public ScaledImageView(Context context) {
    super(context);
  }

  public ScaledImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ScaledImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int width = getMeasuredWidth();

    int calculatedHeight = width / 16 * 9;

    setMeasuredDimension(width, calculatedHeight);
  }
}