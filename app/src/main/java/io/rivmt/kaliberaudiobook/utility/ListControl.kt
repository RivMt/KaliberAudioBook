package io.rivmt.kaliberaudiobook.utility

class ListControl {

    fun applyAlbumCategory(input: MutableList<AudioData>): MutableList<AudioData> {
        return input.distinctBy { it.ALBUM }.toMutableList()
    }

    fun applyDateSort(input: MutableList<AudioData>): MutableList<AudioData> {
        input.sortBy { Integer.parseInt(it.DATE_MODIFIED) }
        return input
    }

}