package com.waracle.androidtest.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class NetworkImageView extends AppCompatImageView implements NetworkImageContract.Observer {

    NetworkImageViewModel viewModel;

    public NetworkImageView(Context context) {
        super(context);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        viewModel = new NetworkImageViewModel(this);
        viewModel.loadImage(uri.toString());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(viewModel != null)
            viewModel.unsubscribe();
    }


    @Override
    public void onImageAvailable(Bitmap bitmap) {
        setImageBitmap(bitmap);
    }
}
