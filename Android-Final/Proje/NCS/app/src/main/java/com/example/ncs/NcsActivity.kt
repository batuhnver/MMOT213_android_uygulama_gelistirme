package com.example.ncs
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ncs.musicdata.NextM
import com.example.ncs.musicdata.NextM3
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ncs.*

class NcsActivity : AppCompatActivity() {
    lateinit var runnable: Runnable
    private var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ncs)

        val mediaplayer : MediaPlayer = MediaPlayer.create(this,R.raw.adondoned_siah)

        seekbar.progress = 0
        seekbar.max = mediaplayer.duration
        mediaplayer.start()
        playBtn.setImageResource(R.drawable.ic_pause_black_24dp)

        btnLogout.setOnClickListener{
            mediaplayer.stop()
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        prewBtn.setOnClickListener{
            mediaplayer.stop()
            val i = Intent(this,NextM3::class.java)
            startActivity(i)
        }

        nextBtn.setOnClickListener{
            mediaplayer.stop()
            val i = Intent(this, NextM::class.java)
            startActivity(i)

        }
        playBtn.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()

                playBtn.setImageResource(R.drawable.ic_pause_black_24dp)
            }else{
                mediaplayer.pause()
                playBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp)
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
            playBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            seekbar.progress = 0

        }


    }
}









