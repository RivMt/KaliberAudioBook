package io.rivmt.kaliberaudiobook

import android.content.ContentResolver
import android.provider.MediaStore
import android.util.Log

public class AudioDataControl(crv: ContentResolver) {

    private val TAG = "AudioDataControl"

    public var mAudioList = mutableListOf<AudioData>()

    private val mContentResolver: ContentResolver = crv

    init {
        getAudioData()
    }

    private fun getAudioData() {
        val prj = arrayOf (
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.DATE_MODIFIED
        )

        val selection = null//"${MediaStore.Audio.Media.DURATION} >= ?"
        val selectionArgs = null//arrayOf(TimeUnit.MILLISECONDS.convert(1,TimeUnit.MINUTES).toString())
        val sortOrder = null//"${MediaStore.Audio.Media.TITLE} ASC"

        val query = mContentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            prj,
            selection,
            selectionArgs,
            sortOrder
        )
        while(query!!.moveToNext()) {
            val id = query.getString(query.getColumnIndex(MediaStore.Audio.Media._ID))
            val aid = query.getString(query.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
            val title = query.getString(query.getColumnIndex(MediaStore.Audio.Media.TITLE))
            val album = query.getString(query.getColumnIndex(MediaStore.Audio.Media.ALBUM))
            val artist = query.getString(query.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            val tr = checkTrackNumberNull(query.getString(query.getColumnIndex(MediaStore.Audio.Media.TRACK)))
            val date = query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATE_MODIFIED))

            val dat = AudioData(
                id,
                aid,
                title,
                album,
                artist,
                tr,
                date
            )
            Log.d(TAG, "Read Audio File Title:${title}, Album:${album}, Artist:${artist}, Track:${tr}, ID:${id}, AlbumId:${aid}, Date:${date}")
            mAudioList.add(dat)
        }
        query.close()
    }

    private fun checkTrackNumberNull(s: String?) : String {
        if (s != null) {
            return s
        }
        return "1"
    }
}