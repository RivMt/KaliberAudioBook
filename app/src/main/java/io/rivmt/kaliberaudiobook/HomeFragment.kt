package io.rivmt.kaliberaudiobook

import android.content.ContentResolver
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(contentResolver: ContentResolver): Fragment() {

    private val mAudioDataControl: AudioDataControl = AudioDataControl(contentResolver)
    private val TAG = "HomeFragment"

    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Set Main Image
        try {//Set AlbumArt as ImageView image, yet not null
            image_home_last_played.setImageBitmap(
                Utility.getAlbumImage(
                    context,
                    Integer.parseInt(mAudioDataControl.mAudioList[0].ALBUM_ID),
                    160
                )
            )
        } catch (e:NullPointerException) {
            Log.d(TAG, e.toString())
        }
        try {
            text_home_last_played.text = mAudioDataControl.mAudioList[0].ALBUM
        } catch (e: IllegalStateException) {
            Log.d(TAG, "Inserted Text: ${mAudioDataControl.mAudioList[0].ALBUM}\n" + e.toString())
        }
    }
}