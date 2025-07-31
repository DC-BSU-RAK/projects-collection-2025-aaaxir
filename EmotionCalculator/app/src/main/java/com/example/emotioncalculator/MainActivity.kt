package com.example.emotioncalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultText = findViewById<TextView>(R.id.resultText)
        val happyButton = findViewById<Button>(R.id.happyButton)
        val sadButton = findViewById<Button>(R.id.sadButton)
        val angryButton = findViewById<Button>(R.id.angryButton)
        val infoButton = findViewById<Button>(R.id.infoButton)

        happyButton.setOnClickListener {
            resultText.text = "You're feeling happy! 😊"
        }

        sadButton.setOnClickListener {
            resultText.text = "You're feeling a bit down. 😢"
        }

        angryButton.setOnClickListener {
            resultText.text = "You're feeling angry. 😡 Take a deep breath!"
        }

        infoButton.setOnClickListener {
            showModalInfo()
        }
    }

    private fun showModalInfo() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.modal_info, null)
        dialog.setContentView(view)
        dialog.show()
    }
}
