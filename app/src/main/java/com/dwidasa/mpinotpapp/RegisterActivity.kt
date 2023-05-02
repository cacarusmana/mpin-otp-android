package com.dwidasa.mpinotpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.dwidasa.mpinotp.Otp
import com.dwidasa.mpinotpapp.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var etDeviceId: TextInputEditText
    private lateinit var etActivationCode: TextInputEditText
    private lateinit var etPin: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etDeviceId = findViewById(R.id.etDeviceId)
        etActivationCode = findViewById(R.id.etActivationCode)
        etPin = findViewById(R.id.etPin)


        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            register()
        }
    }

    private fun register() {
        Otp.activateOtp(this, etDeviceId.value(), etActivationCode.value(), etPin.value())

        Toast.makeText(this, "Register successfully", Toast.LENGTH_LONG).show()
    }


    private fun TextInputEditText.value(): String {
        return this.text.toString()
    }
}