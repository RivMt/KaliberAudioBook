package io.rivmt.kaliberaudiobook

import android.app.Activity
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import io.rivmt.kaliberaudiobook.utility.Constants
import kotlinx.android.synthetic.main.fragment_error.*


class ErrorFragment(act: Activity) : Fragment() {

    private var mActivity:Activity = act
    private var mRequestedPermission = false;

    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ImageView Color
        image_error_warning.setColorFilter(R.color.colorContent, PorterDuff.Mode.SRC_IN )

        //Request Permission
        button_request_permission.setOnClickListener {
            if (!mRequestedPermission) {
                ActivityCompat.requestPermissions(
                    mActivity, arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), Constants.INT_REQUEST_PERMISSION
                )
            }
        }

        return inflater.inflate(R.layout.fragment_error, container, false)
    }
}