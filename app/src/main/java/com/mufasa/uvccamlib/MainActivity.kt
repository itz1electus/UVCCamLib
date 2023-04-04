package com.mufasa.uvccamlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiangdg.ausbc.MultiCameraClient
import com.jiangdg.ausbc.base.CameraFragment
import com.jiangdg.ausbc.callback.ICameraStateCallBack
import com.jiangdg.ausbc.widget.IAspectRatio

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class DemoFragment: CameraFragment() {
    private var mViewBinding: FragmentUvcBinding? = null

    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View? {
        if (mViewBinding == null) {
            mViewBinding = FragmentUvcBinding.inflate(inflater, container, false)
        }
        return mViewBinding?.root
    }

    // if you want offscreen render
    // please return null
    override fun getCameraView(): IAspectRatio? {
        return mViewBinding?.tvCameraRender
    }

    // if you want offscreen render
    // please return null, the same as getCameraView()
    override fun getCameraViewContainer(): ViewGroup? {
        return mViewBinding?.container
    }

    // camera open status callback
    override fun onCameraState(self: MultiCameraClient.ICamera,
                               code: ICameraStateCallBack.State,
                               msg: String?) {
        when (code) {
            ICameraStateCallBack.State.OPENED -> handleCameraOpened()
            ICameraStateCallBack.State.CLOSED -> handleCameraClosed()
            ICameraStateCallBack.State.ERROR -> handleCameraError()
        }
    }

    override fun getGravity(): Int = Gravity.TOP
}
