package com.example.core1noisesoff

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var healthValue = 10
    var healthText = "Health: $healthValue"
    val textResult : TextView = findViewById<TextView>(R.id.Health)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textResult : TextView = findViewById<TextView>(R.id.Health)
        textResult.text = healthText

        var sneezePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.male_sneezing_into_arm)
        var nosePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.human_fart)
        var medicinePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.beeper_emergency_call)

        val sneeze = findViewById<Button>(R.id.Sneeze)
        val blowNose = findViewById<Button>(R.id.BlowNose)
        val medication = findViewById<Button>(R.id.Medication)
        sneeze.setOnClickListener {
            sneezePlayer?.start()
            damage("sneeze")
        }

        blowNose.setOnClickListener {
            nosePlayer?.start()
        }

        medication.setOnClickListener {
            medicinePlayer?.start()
            damage("medication")
        }
    }

    private fun damage(option: String) {
        when (option) {
            "sneeze" -> healthValue--
            "medication" -> healthValue += 2
        }

        healthCheck()
        healthText = "Health: $healthValue"
        textResult.text = healthText
    }

    private fun healthCheck() {
        if (healthValue > 10) healthValue = 10
        if (healthValue < 7) textResult.setBackgroundColor(Color.parseColor("#00ffff")) else textResult.setBackgroundColor(Color.parseColor("#00ff00"))
    }

}
