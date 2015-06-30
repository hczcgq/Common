package com.chen.adapter;

import java.util.LinkedList;
import java.util.List;

import com.chen.R;
import com.chen.util.image.ImageFloder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LocalImageFoleAdapter extends BaseAdapterHelpter<ImageFloder> {
    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public static List<String> mSelectedImage = new LinkedList<String>();

    /**
     * 文件夹路径
     */
    private Context context;
    
    private List<ImageFloder> mDatas;

    private DisplayImageOptions options;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public LocalImageFoleAdapter(Context context, List<ImageFloder> mDatas) {
        super(context, mDatas);
        this.context = context;
        this.mDatas = mDatas;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.bg_ablum_default)
                .showImageOnFail(R.drawable.bg_ablum_default)
                .showImageForEmptyUri(R.drawable.bg_ablum_default)
                .resetViewBeforeLoading(true).cacheOnDisc(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .build();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderHelper holder = ViewHolderHelper.get(context, convertView,
                parent, R.layout.view_localimage_list_dir_item, position);
        TextView nameTextView=holder.getView(R.id.id_dir_item_name);
        ImageView imageView=holder.getView(R.id.id_dir_item_image);
        TextView countTextView =holder.getView(R.id.id_dir_item_count);
        
        ImageFloder item=mDatas.get(position);
        
        String imageUri = "file://" +item.getFirstImagePath();
        imageLoader.displayImage(imageUri, imageView, options);
        nameTextView.setText(item.getName());
        countTextView.setText(item.getCount() + "张");
        return holder.getConvertView();
    }

    LocalImageListen listen;

    public void setLocalImageListen(LocalImageListen listen) {
        this.listen = listen;
    }

    public interface LocalImageListen {
        public void getOneImage(String str);
    }
}
