package com.cn.wyntools;

import android.view.View;
import android.widget.Toast;

import com.cn.wyntools.util.BitmapUtil;
import com.cn.wyntools.util.LogUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


/**
 * Bitmap测试
 *
 * @author jingle1267@163.com
 */
public class BitmapActivity extends Activity {

    private final String TAG = BitmapActivity.class.getSimpleName();

    private ImageView imageViewOrigin, imageViewCombine, blurImageView;

    private int radius;
    private Bitmap blurOriginBitmap;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        imageViewOrigin = (ImageView) findViewById(R.id.iv_round_origin);
        imageViewCombine = (ImageView) findViewById(R.id.iv_round_combine);
        blurImageView = (ImageView) findViewById(R.id.iv_blur);
        testProcess();
        testCombine();

        blurOriginBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sea);
        toast = Toast.makeText(this, "Radius 必须在0到25范围", Toast.LENGTH_SHORT);
    }

    private void testProcess() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.beautiful);
        imageViewOrigin.setImageBitmap(BitmapUtil.getRoundBitmap(bitmap));
        bitmap.recycle();
    }

    private void testCombine() {
        Bitmap bitmap = BitmapUtil.getRoundBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.beautiful));
        Bitmap mask = BitmapFactory.decodeResource(getResources(),
                R.drawable.sea);

        Log.d(TAG, "foreground width - height : " + bitmap.getWidth() + " - " + bitmap.getHeight());
        Log.d(TAG, "background width - height : " + mask.getWidth() + " - " + mask.getHeight());

        imageViewCombine.setImageBitmap(BitmapUtil.combineImagesToSameSize(mask, bitmap));
        bitmap.recycle();
        mask.recycle();
    }

    public void radiusSmall(View view) {
        radius--;
        if (radius < 0) {
            radius = 0;
            toast.show();
            blurImageView.setImageResource(R.drawable.sea);
            return;
        }
        long start = System.nanoTime();
        blurImageView.setImageBitmap(BitmapUtil.blur(this, blurOriginBitmap, radius));
        long useTime = System.nanoTime() - start;
        LogUtils.d("blur time : " + (useTime / 1000000) + " ms");
    }

    public void radiusBig(View view) {
        radius++;
        if (radius > 25) {
            radius = 25;
            toast.show();
            blurImageView.setImageBitmap(BitmapUtil.blur(this, blurOriginBitmap, radius));
            return;
        }
        long start = System.nanoTime();
        blurImageView.setImageBitmap(BitmapUtil.blur(this, blurOriginBitmap, radius));
        long useTime = System.nanoTime() - start;
        LogUtils.d("blur time : " + (useTime / 1000000) + " ms");
    }

}
