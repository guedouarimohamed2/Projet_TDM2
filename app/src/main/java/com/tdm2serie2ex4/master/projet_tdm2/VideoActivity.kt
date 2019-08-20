package com.tdm2serie2ex4.master.projet_tdm2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)


        val fileName = ""
        val filePlace = "android.resource://"+packageName+"/raw/"+R.raw.video
        videoView.setVideoURI(Uri.parse(filePlace))
        videoView.setMediaController(MediaController(this))
        videoView.start()
    }
}
