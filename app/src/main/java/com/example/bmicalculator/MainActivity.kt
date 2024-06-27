package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var weightText: EditText
    private lateinit var heightText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weightText = findViewById(R.id.etWeight)
        heightText = findViewById(R.id.etHeight)
        button = findViewById(R.id.btnCalculate)

        button.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (checker(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val bmiTwoDigit = String.format("%.2f", bmi).toFloat()
                displayResult(bmiTwoDigit)
            }
        }

    }

    private fun checker(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }


    private fun displayResult(bmi: Float) {

        val showResult = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        var color = 0

        showResult.text = bmi.toString()

        when {
            bmi < 18.5 -> {
                resultDescription.text = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9 -> {
                resultDescription.text = "Healthy"
                color = R.color.normal
            }
            bmi in 25.0..29.9 -> {
                resultDescription.text = "Overweight"
                color = R.color.under_weight
            }
            bmi in 30.0..34.9 -> {
                resultDescription.text = "Obessed"
                color = R.color.under_weight
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this, color))
    }
}