package com.example.motomood

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    lateinit var resultText: TextView
    lateinit var preferences: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
        preferences = getSharedPreferences("MotoPrefs", MODE_PRIVATE)

        // ✅ Show previously saved preferences
        val bike = preferences.getString("bikeType", "")
        val vibe = preferences.getString("rideVibe", "")

        if (!bike.isNullOrEmpty() || !vibe.isNullOrEmpty()) {
            resultText.text = "Bike: $bike\nVibe: $vibe"
        }

        val longRide = findViewById<Button>(R.id.btnLongRide)
        val chillCruise = findViewById<Button>(R.id.btnChillCruise)
        val aggressive = findViewById<Button>(R.id.btnAggressive)
        val rainy = findViewById<Button>(R.id.btnRainyMood)

        val infoBtn = findViewById<Button>(R.id.btnInfo)
        val prefBtn = findViewById<Button>(R.id.btnPreferences)

        longRide.setOnClickListener { resultText.text = "You’re in for a scenic cruise 🛣️" }
        chillCruise.setOnClickListener { resultText.text = "Smooth roads and a calm mind 🧘" }
        aggressive.setOnClickListener { resultText.text = "Adrenaline mode on! 🏍️" }
        rainy.setOnClickListener { resultText.text = "Rain and throttle – pure bliss 🌧️" }

        infoBtn.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        prefBtn.setOnClickListener {
            showPreferencesModal()
        }
    }

    private fun showPreferencesModal() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.modal_preferences, null, false)

        val bikeInput = view.findViewById<EditText>(R.id.inputBike)
        val vibeInput = view.findViewById<EditText>(R.id.inputVibe)
        val saveBtn = view.findViewById<Button>(R.id.btnSavePref)

        // Pre-fill if saved before
        bikeInput.setText(preferences.getString("bikeType", ""))
        vibeInput.setText(preferences.getString("rideVibe", ""))

        saveBtn.setOnClickListener {
            preferences.edit().apply {
                putString("bikeType", bikeInput.text.toString())
                putString("rideVibe", vibeInput.text.toString())
                apply()
            }
            Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

            // Optionally update text immediately
            resultText.text = "Bike: ${bikeInput.text}\nVibe: ${vibeInput.text}"
        }

        dialog.setContentView(view)
        dialog.show()
    }
}
