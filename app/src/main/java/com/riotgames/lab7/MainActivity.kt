package com.riotgames.lab7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvCulture: TextView

    private lateinit var materialButton: MaterialButton

    override fun onResume() {
        super.onResume()
        window.addFlags(1024)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        network = Network(this)

        tvName = findViewById(R.id.tv_name)
        tvGender = findViewById(R.id.tv_gender)
        tvCulture = findViewById(R.id.tv_culture)

        materialButton = findViewById(R.id.btn_random)
    }

    private lateinit var network: Network

    @SuppressLint("SetTextI18n")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        materialButton.setOnClickListener {
            network.makeRequest {
                val newValidNames = it.toValidNames()

                tvName.text = "Ім'я: ${newValidNames.first}"
                tvGender.text = "Стать: ${newValidNames.second}"
                tvCulture.text = "Культура: ${newValidNames.third}"
            }
        }
    }

    private fun Triple<String, String, String>.toValidNames(): Triple<String, String, String> {
        var name: String = first
        var gender: String = second
        var culture: String = third
        when {
            name.isBlank() -> name = "Empty"
            gender.isBlank() -> gender = "Empty"
            culture.isBlank() -> culture = "Empty"
        }
        return Triple(name, gender, culture)
    }
}