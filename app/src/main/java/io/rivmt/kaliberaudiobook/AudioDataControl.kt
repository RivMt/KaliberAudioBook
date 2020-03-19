package io.rivmt.kaliberaudiobook

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import java.util.concurrent.TimeUnit

public class AudioDataControl {

    public var mAudioList = mutableListOf<AudioData>()

    val mContentResolver: ContentResolver

    constructor(crv: ContentResolver) {
        mContentResolver=crv
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
        val selectionArgs = arrayOf(TimeUnit.MILLISECONDS.convert(1,TimeUnit.MINUTES).toString())
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        val query = mContentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            prj,
            selection,
            selectionArgs,
            sortOrder
        )
        while(query!!.moveToNext()) {
            val dat = AudioData(
                query.getString(query.getColumnIndex(MediaStore.Audio.Media._ID)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.TRACK)),
                query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATE_MODIFIED))
            )
            mAudioList.add(dat)
        }
        query.close()
    }
}