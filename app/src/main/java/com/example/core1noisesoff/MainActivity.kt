package com.example.core1noisesoff

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //Comments marked with a # were removed for correct implementation of string externalisation
    var healthValue = 10
    //#var healthString = "Health "
    //#var healthText = "$healthString $healthValue"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       # Localisation()

        val textResult: TextView = findViewById(R.id.Health) as TextView
        textResult.setBackgroundColor(Color.parseColor("#00ff00"))
        //#healthText = "$healthString $healthValue"
        textResult.text = healthValue.toString()

        var sneezePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.male_sneezing_into_arm)
        var nosePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.human_fart)
        var medicinePlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.beeper_emergency_call)

        val sneeze = findViewById<Button>(R.id.Sneeze)
        val blowNose = findViewById<Button>(R.id.BlowNose)
        val medication = findViewById<Button>(R.id.Medication)
        sneeze.setOnClickListener {
            sneezePlayer?.start()
            damage("sneeze", textResult)
            Log.i("MAIN", "Sneezed")
        }

        blowNose.setOnClickListener {
            nosePlayer?.start()
            Log.i("MAIN", "Blew Nose")
        }

        medication.setOnClickListener {
            medicinePlayer?.start()
            damage("medication", textResult)
            Log.i("MAIN", "Took Medication")
        }

        savedInstanceState?.let {
            healthValue = it.getInt("HEALTH")
            val textResult: TextView = findViewById(R.id.Health) as TextView
            healthCheck(textResult)
            Log.i("MAIN", "Reading health state as $healthValue")
        }
    }

    private fun damage(option: String, textResult: TextView) {
        when (option) {
            "sneeze" -> healthValue--
            "medication" -> healthValue += 2
        }
        healthCheck(textResult)
    }

    private fun healthCheck(textResult: TextView) {

        when (healthValue) {
            11, 12 -> {healthValue = 10
                Log.i("MAIN", "Health was $healthValue and was set to 10")}
            in 8..10 -> {textResult.setBackgroundColor(Color.parseColor("#00ff00"))
                Log.i("MAIN", "Health was $healthValue and colour was set to green")}
            6, 7 -> {textResult.setBackgroundColor(Color.parseColor("#00ffff"))
                Log.i("MAIN", "Health was $healthValue and colour was set to blue")}
            in 0..5 -> {textResult.setBackgroundColor(Color.parseColor("#ff3300"))
                Log.i("MAIN", "Health was $healthValue and color was set to red")}
            -1 -> {healthValue = 0
                Log.i("MAIN", "Health was $healthValue and was set to 0")}
            else -> {healthValue = 10
                Log.i("MAIN", "This should be impossible. Health was $healthValue and was set to 10")}
        }

        //$healthText = "$healthString $healthValue"
        textResult.text = healthValue.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("HEALTH", healthValue)
        Log.i("MAIN", "Saving health state as $healthValue")

    }

//    fun Localisation() {
//        healthString = getString(R.string.health)
//        val sneezeString: String = getString(R.string.sneeze)
//        val noseString: String = getString(R.string.blowNose)
//        val medicationString: String = getString(R.string.medication)
//
//        val healthResult: TextView = findViewById(R.id.Health) as TextView
//        healthResult.text = healthString
//        val sneezeResult: TextView = findViewById(R.id.Sneeze) as TextView
//        sneezeResult.text = sneezeString
//        val noseResult: TextView = findViewById(R.id.BlowNose) as TextView
//        noseResult.text = noseString
//        val medicationResult: TextView = findViewById(R.id.Medication) as TextView
//        medicationResult.text = medicationString
//    }
}


