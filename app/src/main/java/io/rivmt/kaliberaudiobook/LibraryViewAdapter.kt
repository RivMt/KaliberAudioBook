package io.rivmt.kaliberaudiobook

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import io.rivmt.kaliberaudiobook.utility.AudioData

class LibraryViewAdapter(context: Context): BaseAdapter() {

    private val mContext = context
    private val TAG = "LibraryViewAdapter"

    var mItems = mutableListOf<AudioData>()

    override fun getCount(): Int {
        return mItems.size
    }

    override fun getItem(i: Int): AudioData {
        return mItems[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val item = LibraryViewItem(mContext, R.layout.gridview_library)
        item.mAudioData = mItems[i]
        item.setGridViewData()
        return item
    }

    fun addItem(item: AudioData) {
        mItems.add(item)
    }

    fun replaceItem(i: Int, newItem: AudioData) {
        mItems[i] = newItem
    }

    fun removeItem(i: Int) {
        mItems.removeAt(i)
    }

    fun removeItems() {
        mItems.clear()
    }

}