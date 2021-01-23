package com.example.ncs.musicdata

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.ncs.MainActivity
import com.example.ncs.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_next_m1.seekbar
import kotlinx.android.synthetic.main.activity_next_m2.*

class NextM2 : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_m2)



        val mediaplayer : MediaPlayer = MediaPlayer.create(this,R.raw.lost_sky_needs_you)
        seekbar.progress = 0
        seekbar.max = mediaplayer.duration
        mediaplayer.start()
        playBtn3.setImageResource(R.drawable.ic_pause_black_24dp)

        btnLogout3.setOnClickListener{
            mediaplayer.stop()
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        prewBtn3.setOnClickListener{
            mediaplayer.stop()
            val i = Intent(this, NextM1::class.java)
            startActivity(i)
        }

        nextBtn3.setOnClickListener{
            mediaplayer.stop()
            val i = Intent(this, NextM3::class.java)
            startActivity(i)

        }



        playBtn3.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()

                playBtn3.setImageResource(R.drawable.ic_pause_black_24dp)
            }else{
                mediaplayer.pause()
                playBtn3.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            }

        }
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)

                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)

        mediaplayer.setOnCompletionListener {
            playBtn3.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            seekbar.progress = 0

        }

    }
}
