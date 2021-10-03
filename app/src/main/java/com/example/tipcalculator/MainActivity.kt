package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val costOfService = binding.costOfService.text.toString().toDoubleOrNull()
        if(costOfService == null) {
            binding.tipResultText.text = ""
            return
        }
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.tip_option_15_percent -> .15
            R.id.tip_option_18_percent -> .18
            else -> .20
        }

        var tipAmount = costOfService * tipPercentage
        val roundUp = binding.tipAmountSwitch.isChecked
        if(roundUp) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.tipResultText.text = getString(R.string.tip_amount, formattedTip)

    }
}