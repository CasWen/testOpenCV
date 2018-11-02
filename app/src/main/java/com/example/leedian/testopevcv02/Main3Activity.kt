package com.example.leedian.testopevcv02

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.leedian.testopevcv02.R

import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class Main3Activity : AppCompatActivity() {

    private var imageView: ImageView? = null
    private var button: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()

        //初始化
        if (OpenCVLoader.initDebug()) {
            Toast.makeText(this, "Main3Activity中OpenCVLoader初始化成功", Toast.LENGTH_SHORT).show()
        }


        //测试灰度
        button?.setOnClickListener { convert2Grey() }

    }

    private fun convert2Grey() {

        val bmp = BitmapFactory.decodeResource(this.resources, R.mipmap.tupian)
        val rgbMat = Mat()
        val grayMat = Mat()
        //获取lena彩色图像所对应的像素数据
        Utils.bitmapToMat(bmp, rgbMat)
        //将彩色图像数据转换为灰度图像数据并存储到grayMat中
        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY)
        //创建一个灰度图像
        val grayBmp = Bitmap.createBitmap(bmp.width, bmp.height, Bitmap.Config.RGB_565)
        //将矩阵grayMat转换为灰度图像
        Utils.matToBitmap(grayMat, grayBmp)
        imageView!!.setImageBitmap(grayBmp)

    }

    private fun initView() {
        imageView = findViewById<View>(R.id.img) as ImageView
        button = findViewById<View>(R.id.button) as Button
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

  override fun onResume() {
        super.onResume()
        //        if (!OpenCVLoader.initDebug()) {
        ////            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
        //            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        //        } else {
        ////            Log.d(TAG, "OpenCV library found inside package. Using it!");
        //            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        //        }


    }

    companion object {

        // Used to load the 'native-lib' library on application startup.
        //    static {
        //        System.loadLibrary("libopencv_java3");

        //    }
        init {
            if (OpenCVLoader.initDebug()) {
                //System.loadLibrary("nonfree");
                //            Log.d(TAG, "static initializer: success");
                //            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            } else {
                //            Log.d(TAG, "static initializer: fail");
            }

        }
    }

}
