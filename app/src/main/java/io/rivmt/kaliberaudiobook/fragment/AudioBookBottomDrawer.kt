package io.rivmt.kaliberaudiobook.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.rivmt.kaliberaudiobook.InfoActivity
import io.rivmt.kaliberaudiobook.R
import io.rivmt.kaliberaudiobook.utility.AudioData
import io.rivmt.kaliberaudiobook.utility.Constants
import io.rivmt.kaliberaudiobook.utility.Utility
import kotlinx.android.synthetic.main.drawer_audiobook_menu.*

class AudioBookBottomDrawer: BottomSheetDialogFragment() {

    private val TAG = "AudioBookBottomDrawer"

    var mSelectedItem:AudioData = AudioData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.drawer_audiobook_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "BottomDrawer onActivityCreated: ${mSelectedItem.ALBUM}")

        //Set Information Object
        drawer_audiobook_title.text = mSelectedItem.ALBUM
        drawer_audiobook_author.text = mSelectedItem.ARTIST
        drawer_audiobook_image.setImageBitmap(Utility.getAlbumImage(context,Integer.parseInt(mSelectedItem.ALBUM_ID), Constants.INT_LIBRARY_STANDARD_IMAGE_SIZE))
        drawer_audiobook_date.text = Utility.getDateFromUnix(mSelectedItem.DATE_MODIFIED.toLong())

        //Navigation Listener
        drawer_audiobook_navigation.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_audiobook_info -> {
                    val intent = Intent(activity, InfoActivity::class.java)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
        }
    }
}