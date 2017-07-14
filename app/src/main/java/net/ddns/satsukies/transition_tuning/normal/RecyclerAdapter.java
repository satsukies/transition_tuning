package net.ddns.satsukies.transition_tuning.normal;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import net.ddns.satsukies.transition_tuning.R;
import net.ddns.satsukies.transition_tuning.databinding.LayoutListItemBinding;

/**
 * Created by satsukies on 2017/07/14.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

  private AppCompatActivity context;
  private List<String> list;
  private int parentPosition;
  private MyViewHolder.Listener listener;

  private static final int LAYOUT_ID = R.layout.layout_list_item;

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    LayoutListItemBinding binding;
    Listener listener;

    public interface Listener {
      void onClickItem(View v);
    }

    public MyViewHolder(View itemView) {
      super(itemView);

      binding = DataBindingUtil.bind(itemView);
    }

    public MyViewHolder(View itemView, Listener listener) {
      super(itemView);

      this.listener = listener;

      //binding
      binding = DataBindingUtil.bind(itemView);
    }

    public LayoutListItemBinding getBinding() {
      return binding;
    }
  }

  public RecyclerAdapter(AppCompatActivity context, int position, List<String> list,
      MyViewHolder.Listener listener) {
    this.context = context;
    parentPosition = position;
    this.list = list;
    this.listener = listener;
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ID, parent, false);

    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, final int position) {
    holder.getBinding().contentImage.setOnClickListener(v -> {
      Toast.makeText(context, "toast:" + parentPosition + "-" + position, Toast.LENGTH_SHORT)
          .show();

      listener.onClickItem(holder.binding.contentImage);
    });

    ImageView imageView = holder.getBinding().contentImage;
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
        .into(imageView);

    //veryhigh;http://imgur.com/a/HGM2k direct;http://imgur.com/K1oWDXu.png
    //mid;http://imgur.com/a/XrGjF direct;http://imgur.com/QUKsCnM
    //low;http://imgur.com/a/NG6i4 direct;http://imgur.com/ZJNbigT
  }

  @Override public int getItemCount() {
    return list.size();
  }
}