package io.rivmt.kaliberaudiobook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.rivmt.kaliberaudiobook.utility.AudioData

class InfoActivity : AppCompatActivity() {

    var mSelectedItem = AudioData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }
}
