package io.rivmt.kaliberaudiobook

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import io.rivmt.kaliberaudiobook.utility.AudioData
import io.rivmt.kaliberaudiobook.utility.Constants
import io.rivmt.kaliberaudiobook.utility.Utility
import kotlinx.android.synthetic.main.gridview_library.view.*

class LibraryViewItem @JvmOverloads constructor(context: Context, layout: Int, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var mAudioData: AudioData

    init {
        val li: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(layout, this, true)
    }

    fun setGridViewData() {
        gridview_item_image.setImageBitmap(Utility.getAlbumImage(context, Integer.parseInt(mAudioData.ALBUM_ID), Constants.INT_LIBRARY_STANDARD_IMAGE_SIZE))
        gridview_item_title.text = mAudioData.ALBUM
    }
}