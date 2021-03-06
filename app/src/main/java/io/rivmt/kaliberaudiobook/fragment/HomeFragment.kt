package io.rivmt.kaliberaudiobook.fragment

import android.content.ContentResolver
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.rivmt.kaliberaudiobook.LibraryGridViewAdapter
import io.rivmt.kaliberaudiobook.R
import io.rivmt.kaliberaudiobook.utility.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(contentResolver: ContentResolver): Fragment() {

    private val mAudioDataControl: AudioDataControl =
        AudioDataControl(contentResolver)
    private val TAG = "HomeFragment"

    private lateinit var mLibraryViewAdapter: LibraryGridViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Sort AudioDataList
        mAudioDataControl.mAudioList = ListControl().applyDateSort(mAudioDataControl.mAudioList)
        mAudioDataControl.mAudioList.sortByDescending { Integer.parseInt(it.DATE_MODIFIED) }

        //Set Main Image
        val index = getMainAudioBookIndex()
        setMainAudioBook(mAudioDataControl.mAudioList[index].ALBUM_ID, mAudioDataControl.mAudioList[index].ALBUM, mAudioDataControl.mAudioList[index].ARTIST)

        //Set RecyclerView Adapter
        val glm = GridLayoutManager(context, Utility.calculateNumberOfColumns(context, Constants.INT_LIBRARY_GRID_COLUMN_WIDTH))
        grid_recently_added.layoutManager = glm
        mLibraryViewAdapter = context?.let {
            LibraryGridViewAdapter(
                it
            )
        }!!

        mLibraryViewAdapter.setOnItemLongClickListener(object :
            LibraryGridViewAdapter.OnItemLongClickListener {
            override fun onItemLongClick(v: View?, pos: Int) {//Long Clicked Individual Item
                val fragment =
                    AudioBookBottomDrawer()
                fragment.mSelectedItem = mLibraryViewAdapter.mItems[pos]
                fragment.show(fragmentManager, TAG)
            }

            override fun onLongClick(v: View?): Boolean {
                TODO("Not yet implemented")
            }
        })
        grid_recently_added.adapter = mLibraryViewAdapter
        inputAudioDataToAdapter()
    }

    //Input Data to Adapter
    private fun inputAudioDataToAdapter() {
        mLibraryViewAdapter.mItems = ListControl().applyAlbumCategory(mAudioDataControl.mAudioList)
    }

    //Set Main AudioBook
    private fun setMainAudioBook(album_id:String, album:String, artist:String) {
        try {//Set AlbumArt as ImageView image, yet not null
            image_home_last_played.setImageBitmap(
                Utility.getAlbumImage(
                    context,
                    Integer.parseInt(album_id),
                    Constants.INT_LIBRARY_MAIN_IMAGE_SIZE
                )
            )
        } catch (e:NullPointerException) {
            Log.d(TAG, e.toString())
        }
        //Set Main Title & Artist
        text_home_last_played.text = album
        text_home_last_played_artist.text = artist
    }

    //Get Main AudioBook Index
    private fun getMainAudioBookIndex(): Int {
        return 0
    }
}