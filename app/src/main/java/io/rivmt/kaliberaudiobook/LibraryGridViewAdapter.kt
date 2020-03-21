package io.rivmt.kaliberaudiobook

import android.content.Context
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.rivmt.kaliberaudiobook.utility.AudioData
import io.rivmt.kaliberaudiobook.utility.Constants
import io.rivmt.kaliberaudiobook.utility.Utility

open class LibraryGridViewAdapter(context: Context): RecyclerView.Adapter<LibraryGridViewAdapter.ViewHolder>() {

    private val mContext = context
    private val TAG = "LibraryViewAdapter"
    private var mItemOnLongClickListener: OnItemLongClickListener? = null

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.mItemOnLongClickListener = listener
    }

    var mItems = mutableListOf<AudioData>()

    interface OnItemLongClickListener : View.OnLongClickListener {
        fun onItemLongClick(v: View?, pos: Int)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.gridview_item_image)
        val mTextView: TextView = itemView.findViewById(R.id.gridview_item_title)

        init {
            //Long Click Listener
            mImageView.setOnLongClickListener {
                val pos:Int = adapterPosition
                mItemOnLongClickListener?.onItemLongClick(itemView, pos)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context:Context = parent.context
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view:View = inflater.inflate(R.layout.gridview_library, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mImageView.setImageBitmap(Utility.getAlbumImage(mContext, Integer.parseInt(mItems[position].ALBUM_ID), Constants.INT_LIBRARY_STANDARD_IMAGE_SIZE))
        holder.mTextView.text = mItems[position].ALBUM
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
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