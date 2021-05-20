package com.bersoncrios.rickpedia.Speech

import android.Manifest.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bersoncrios.rickpedia.R
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.*


class SpeechActivity : AppCompatActivity() {

    private val RecordAudioRequestCode = 1
    lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)
        micSetup()
        checkPermission()
        speech()
    }

    private fun micSetup() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        SpeechImageView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, motionEvent: MotionEvent): Boolean {
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening()
                }
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    SpeechImageView.setImageResource(R.drawable.ic_baseline_mic_red)
                    speechRecognizer.startListening(speechRecognizerIntent)
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission.RECORD_AUDIO),
                RecordAudioRequestCode
            )
        }
    }

    private fun speech(){
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {
                SpeechEditText.setText("")
                SpeechEditText.setHint("Ouvindo você ...")
            }

            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {}
            override fun onError(i: Int) {}
            override fun onResults(bundle: Bundle) {
                SpeechImageView.setImageResource(R.drawable.ic_baseline_mic_24)
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                SpeechEditText.setText(data!![0])
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RecordAudioRequestCode && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) Toast.makeText(
                this,
                "Permission Granted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}