package io.rivmt.kaliberaudiobook.utility

class ListControl {

    fun applyAlbumSort(input: MutableList<AudioData>): MutableList<AudioData> {
        return input.distinctBy { it.ALBUM }.toMutableList()
    }

}