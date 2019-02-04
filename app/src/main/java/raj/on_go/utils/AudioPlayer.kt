package raj.on_go.utils

import android.media.MediaPlayer
import android.util.Log

object AudioPlayer {
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    fun playAudio(audioUrl: String?) {
        Log.d("audio url", audioUrl)
        if (audioUrl != null) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }
}