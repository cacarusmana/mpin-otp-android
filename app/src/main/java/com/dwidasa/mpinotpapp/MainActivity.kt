package com.dwidasa.mpinotpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dwidasa.mpinotpapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

        findViewById<Button>(R.id.btnGenerate).setOnClickListener {
            startActivity(Intent(this@MainActivity, GenerateActivity::class.java))
        }
    }
}