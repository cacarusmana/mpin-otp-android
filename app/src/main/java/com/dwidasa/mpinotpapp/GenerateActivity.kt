package com.dwidasa.mpinotpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.dwidasa.mpinotp.Otp
import com.google.android.material.textfield.TextInputEditText

class GenerateActivity : AppCompatActivity() {

    private lateinit var etPin: TextInputEditText
    private lateinit var tvToken: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate)

        etPin = findViewById(R.id.etPin)
        tvToken = findViewById(R.id.tvToken)


        findViewById<Button>(R.id.btnGenerate).setOnClickListener {
            generate()
        }
    }

    private fun generate() {
        val token = Otp.generateToken(this, etPin.value())
        tvToken.text = token
    }

    private fun TextInputEditText.value(): String {
        return this.text.toString()
    }
}