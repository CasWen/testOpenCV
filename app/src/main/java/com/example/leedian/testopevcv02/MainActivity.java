package com.example.leedian.testopevcv02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.lang.annotation.Native;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("libopencv_java3");

//    }
    static {
        if (OpenCVLoader.initDebug()) {
            //System.loadLibrary("nonfree");
//            Log.d(TAG, "static initializer: success");
//            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } else {
//            Log.d(TAG, "static initializer: fail");
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        initView();

        //初始化
        if (OpenCVLoader.initDebug()) {
            Toast.makeText(this, "OpenCVLoader初始化成功", Toast.LENGTH_SHORT).show();
        }


        //测试灰度
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert2Grey();
            }
        });

    }

    private void convert2Grey() {

        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.mipmap.tupian);
        Mat rgbMat = new Mat();
        Mat grayMat = new Mat();
        //获取lena彩色图像所对应的像素数据
        Utils.bitmapToMat(bmp, rgbMat);
        //将彩色图像数据转换为灰度图像数据并存储到grayMat中
        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);
        //创建一个灰度图像
        Bitmap grayBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.RGB_565);
        //将矩阵grayMat转换为灰度图像
        Utils.matToBitmap(grayMat, grayBmp);
        imageView.setImageBitmap(grayBmp);

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.img);
        button = (Button) findViewById(R.id.button);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    protected void onResume() {
        super.onResume();
//        if (!OpenCVLoader.initDebug()) {
////            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
//            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
//        } else {
////            Log.d(TAG, "OpenCV library found inside package. Using it!");
//            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//        }



    }


    //openCV4Android 需要加载用到
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
//
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

}
