package com.techafresh.unitconverter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.techafresh.unitconverter.databinding.ActivityConverterBinding


class ConverterActivity : AppCompatActivity() {
    lateinit var binding : ActivityConverterBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getStringExtra("Category")
        when (category) {
            "Temperature" -> {
                binding.categoryText.text = "Temperature"
                createAdapter(R.array.temperature)
            }
            "Weight/Mass" -> {
                binding.categoryText.text = "Weight/Mass"
                createAdapter(R.array.weight_mass)
            }
            "Area" -> {
                binding.categoryText.text = "Area"
                createAdapter(R.array.area)
            }
            "Volume" -> {
                binding.categoryText.text = "Volume"
                createAdapter(R.array.volume)
            }
            "Length" -> {
                binding.categoryText.text = "Length"
                createAdapter(R.array.length)
            }
            "Speed" -> {
                binding.categoryText.text = "Speed"
                createAdapter(R.array.speed)
            }
            "Time" -> {
                binding.categoryText.text = "Time"
                createAdapter(R.array.time)
            }
            "Storage" -> {
                binding.categoryText.text = "Storage"
                createAdapter(R.array.storage)
            }
            "Pressure" -> {
                binding.categoryText.text = "Pressure"
                createAdapter(R.array.pressure)
            }
            else -> {
                binding.categoryText.text = "NNNNNNNNNNN"
                createAdapter(R.array.nullz)
            }
        }

        binding.textViewAbout.setOnClickListener {
            startActivity(Intent(this@ConverterActivity, AboutActivity::class.java))
        }

        binding.editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (category != null) {
                    convert(category)
                }
            }
        })

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                binding.editTextFrom.hint = "From " + parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                binding.textViewTo.hint = "To "+ parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }

        binding.buttonConvert.setOnClickListener {
            if (category != null) {
                convert(category)
            }
        }
    }

    private fun createAdapter(array : Int){
        ArrayAdapter.createFromResource(
            this,
            array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerTo.adapter = adapter
            binding.spinnerFrom.adapter = adapter
        }
    }

    private fun convert(category: String){
        when(category){
            "Temperature" -> {
                binding.textViewTo.text = convertTemp(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Length" -> {
                binding.textViewTo.text = convertDistance(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Area" -> {
                binding.textViewTo.text = convertArea(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Weight/Mass" -> {
                binding.textViewTo.text = convertWeight(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Time" -> {
                binding.textViewTo.text = convertTime(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Pressure" -> {
                binding.textViewTo.text = convertPressure(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Speed" -> {
                binding.textViewTo.text = convertSpeed(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Storage" -> {
                binding.textViewTo.text = convertStorage(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
            "Volume" -> {
                binding.textViewTo.text = convertVolume(binding.spinnerFrom.selectedItem.toString(), binding.editTextFrom.text.toString().toDouble(),binding.spinnerTo.selectedItem.toString())
            }
        }
    }


    private fun convertTemp(from : String, value : Double, to : String) : String{
        return when(from) {
            "Celsius" -> {
                return when (to) {
                    "Kelvin" -> {
                        "${value + 273}" // Kelvin
                    }
                    "Fahrenheit" -> {
                        "${value * 1.8 + 32}" // Fahrenheit
                    }
                    else -> {
                        "$value"  // Celsius
                    }
                }
            }
            "Kelvin" -> {
                return when (to) {
                    "Celsius" -> {
                        "${value - 273.15}" // Kelvin
                    }
                    "Fahrenheit" -> {
                        "${((value - 273.15) * 1.8) + 32}" // Fahrenheit
                    }
                    else -> {
                        "$value"  // Kelvin
                    }
                }
            }
            "Fahrenheit" -> {
                return when (to) {
                    "Kelvin" -> {
                        "${((value - 32) * 5/9) + 273.15}" // Kelvin
                    }
                    "Celsius" -> {
                        "${(value - 32) * 5/9}" // Fahrenheit
                    }
                    else -> {
                        "$value"  // Fahrenheit
                    }
                }
            }
            else -> "NULL"
        }
    }

    private fun convertDistance(from: String, value: Double, to: String) : String {
        Log.d("MYTAG" , "Distance Converter")
        return when(from){
            "Mile (mi)" -> {
                return when(to){
                    "Meter (m)" -> {
                        "${value * 1609.344}"
                    }
                    "Centimeter (cm)" -> {
                        "${value * 160934.4}"
                    }
                    "Kilometer (km)" -> {
                        "${value * 1.609344}"
                    }
                    "Foot (ft)" -> {
                        "${value * 5280}"
                    }

                    else -> "$value" // Miles
                }
            }
            "Meter (m)" -> {
                return when(to){
                    "Mile (mi)" -> {
                        "${value / 1609.344}"
                    }
                    "Centimeter (cm)" -> {
                        "${value * 100}"
                    }
                    "Kilometer (km)" -> {
                        "${value / 1000}"
                    }
                    "Foot (ft)" -> {
                        "${value * 3.2808399}"
                    }

                    else -> "$value" // Meter
                }
            }
            "Centimeter (cm)" -> {
                return when(to){
                    "Meter (m)" -> {
                        "${value / 100}"
                    }
                    "Mile (mi)" -> {
                        "${value / 160934.4}"
                    }
                    "Kilometer (km)" -> {
                        "${value / 100000}"
                    }
                    "Foot (ft)" -> {
                        "${value / 30.48}"
                    }

                    else -> "$value" // Miles
                }
            }
            "Kilometer (km)" -> {
                return when(to){
                    "Meter (m)" -> {
                        "${value * 1000}"
                    }
                    "Centimeter (cm)" -> {
                        "${value * 100000}"
                    }
                    "Mile (mi)" -> {
                        "${value / 1.609344}"
                    }
                    "Foot (ft)" -> {
                        "${value * 3280.8399}"
                    }

                    else -> "$value" // Miles
                }
            }
            "Foot (ft)" -> {
                return when(to){
                    "Meter (m)" -> {
                        "${value / 3.2808399}"
                    }
                    "Centimeter (cm)" -> {
                        "${value * 30.48}"
                    }
                    "Kilometer (km)" -> {
                        "${value / 3280.8399}"
                    }
                    "Mile (mi)" -> {
                        "${value / 5280}"
                    }

                    else -> "$value" // Miles
                }
            }
            else -> "NULL"
        }
    }

    private fun convertArea(from: String, value: Double, to: String) : String{
        return when(from){
            "Square meter (m²)" -> {
                return when(to){
                    "Square centimeter (cm²)" -> {
                        "${value * 10000}"
                    }
                    "Hectare (ha)" -> {
                        "${value / 10000}"
                    }
                    "Acre (a)" -> {
                        "${value / 4046.85642}"
                    }
                    else -> "$value" // Square meter
                }
            }
            "Square centimeter (cm²)" -> {
                return when(to){
                    "Square meter (m²)" -> {
                        "${value / 10000}"
                    }
                    "Hectare (ha)" -> {
                        "${value / 100000000}"
                    }
                    "Acre (a)" -> {
                        "${value / 40469000}"
                    }
                    else -> "$value" //Square centimeter
                }
            }
            "Hectare (ha)" -> {
                return when(to){
                    "Square centimeter (cm²)" -> {
                        "${value * 100000000}"
                    }
                    "Square meter (m²)" -> {
                        "${value * 10000}"
                    }
                    "Acre (a)" -> {
                        "${value * 2.47105381}"
                    }
                    else -> "$value" // Hectare
                }
            }
            "Acre (a)" -> {
                return when(to){
                    "Square centimeter (cm²)" -> {
                        "${value * 40469000}"
                    }
                    "Hectare (ha)" -> {
                        "${value / 2.47105381}"
                    }
                    "Square meter (m²)" -> {
                        "${value * 4046.85642}"
                    }
                    else -> "$value" //Acre
                }
            }
            else -> "NULL"
        }
    }

    private fun convertWeight(from: String, value: Double, to: String) : String{
        return when(from){
            "Kilogram (kg)" -> {
                return when(to){
                    "Milligram (mg)" -> {
                        "${value * 1000000}"
                    }
                    "Pound (lb)" -> {
                        "${value * 2.20462262}"
                    }
                    "Gram (g)" -> {
                        "${value * 1000}"
                    }
                    else -> "$value" //Kilogram
                }
            }
            "Milligram (mg)" -> {
                return when(to){
                    "Kilogram (kg)" -> {
                        "${value / 1000000}"
                    }
                    "Pound (lb)" -> {
                        "${value / 453592.37}"
                    }
                    "Gram (g)" -> {
                        "${value / 1000}"
                    }
                    else -> "$value" //Milligram
                }
            }
            "Gram (g)" -> {
                return when(to){
                    "Milligram (mg)" -> {
                        "${value * 1000}"
                    }
                    "Pound (lb)" -> {
                        "${value / 453.59237}"
                    }
                    "Kilogram (kg)" -> {
                        "${value / 1000}"
                    }
                    else -> "$value" //Gram
                }
            }
            "Pound (lb)" -> {
                return when(to){
                    "Milligram (mg)" -> {
                        "${value * 453592.37}"
                    }
                    "Kilogram (kg)" -> {
                        "${value / 2.20462262}"
                    }
                    "Gram (g)" -> {
                        "${value * 453.59237}"
                    }
                    else -> "$value" //Pounds
                }
            }
            else -> "NULL"
        }
    }

    private fun convertTime(from: String, value: Double, to: String) : String {
        return when(from){
            "Day (d)" -> {
                return when(to){
                    "Hour (h)" -> {
                        "${value * 24}"
                    }
                    "Minute (min)" -> {
                        "${value * 1440}"
                    }
                    "Second (s)" -> {
                        "${value * 86400}"
                    }

                    else -> "$value" // Day
                }
            }
            "Hour (h)" -> {
                return when(to){
                    "Day (d)" -> {
                        "${value / 24}"
                    }
                    "Minute (min)" -> {
                        "${value * 60}"
                    }
                    "Second (s)" -> {
                        "${value * 3600}"
                    }

                    else -> "$value" // Hour
                }
            }
            "Minute (min)" -> {
                return when(to){
                    "Hour (h)" -> {
                        "${value / 60}"
                    }
                    "Day (d)" -> {
                        "${value / 1440}"
                    }
                    "Second (s)" -> {
                        "${value * 60}"
                    }

                    else -> "$value" // Minute
                }
            }
            "Second (s)" -> {
                return when(to){
                    "Hour (h)" -> {
                        "${value * 3600}"
                    }
                    "Minute (min)" -> {
                        "${value / 60}"
                    }
                    "Day (d)" -> {
                        "${value / 86400}"
                    }

                    else -> "$value" // Second
                }
            }
            else -> "NULL"
        }
    }

    private fun convertPressure(from: String, value: Double, to: String) : String{
        return when(from){
            "Atmosphere (atm)" -> {
                return when(to){
                    "Pascal (Pa)" -> {
                        "${value * 101325}"
                    }
                    "Millimeter of Mercury (mmHg)" -> {
                        "${value * 760.000002}"
                    }

                    else -> "$value"
                }
            }
            "Pascal (Pa)" -> {
                return when(to){
                    "Atmosphere (atm)" -> {
                        "${value / 101325}"
                    }
                    "Millimeter of Mercury (mmHg)" -> {
                        "${value / 133.322368}"
                    }
                    else -> "$value"
                }
            }
            "Millimeter of Mercury (mmHg)" -> {
                return when(to){
                    "Pascal (Pa)" -> {
                        "${value * 133.322368}"
                    }
                    "Atmosphere (atm)" -> {
                        "${value / 760.000002}"
                    }
                    else -> "$value"
                }
            }
            else -> "NULL"
        }
    }

    private fun convertSpeed(from: String, value: Double, to: String) : String {
        return when(from){
            "Miles per hour (mph)" -> {
                return when(to){
                    "Kilometers per hour (km/h)" -> {
                        "${value * 1.609344}"
                    }
                    "Meters per second (m/s)" -> {
                        "${value / 2.23693629}"
                    }
                    else -> "$value" // Miles per Hour
                }
            }
            "Kilometers per hour (km/h)" -> {
                return when(to){
                    "Miles per hour (mph)" -> {
                        "${value / 1.609344}"
                    }
                    "Meters per second (m/s)" -> {
                        "${value / 3.6}"
                    }
                    else -> "$value" // Miles per Hour
                }
            }
            "Meters per second (m/s)" -> {
                return when(to){
                    "Kilometers per hour (km/h)" -> {
                        "${value * 3.6}"
                    }
                    "Miles per hour (mph)" -> {
                        "${value * 2.23693629}"
                    }
                    else -> "$value" // Meters per Second
                }
            }
            else -> "NULL"
        }
    }

    private fun convertStorage(from: String, value: Double, to: String) : String{
        return when(from){
            "Bit (b)" -> {
                return when(to){
                    "Byte (B)" -> {
                        "${value / 8}"
                    }
                    "Kilobyte (kB)" -> {
                        "${value / 8000}"
                    }
                    "Megabyte (MB)" -> {
                        "${value / 8000000}"
                    }
                    "Gigabyte (GB)" -> {
                        "${value / 8000000000}"
                    }
                    "Terabyte (TB)" -> {
                        "${value / 8000000000000}"
                    }

                    else -> "$value"
                }
            }
            "Byte (B)" -> {
                return when(to){
                    "Bit (b)" -> {
                        "${value * 8}"
                    }
                    "Kilobyte (kB)" -> {
                        "${value / 1000}"
                    }
                    "Megabyte (MB)" -> {
                        "${value / 1000000}"
                    }
                    "Gigabyte (GB)" -> {
                        "${value / 1000000000}"
                    }
                    "Terabyte (TB)" -> {
                        "${value / 1000000000000}"
                    }

                    else -> "$value"
                }
            }
            "Kilobyte (kB)" -> {
                return when(to){
                    "Byte (B)" -> {
                        "${value * 1000}"
                    }
                    "Bit (b)" -> {
                        "${value * 8000}"
                    }
                    "Megabyte (MB)" -> {
                        "${value / 1000}"
                    }
                    "Gigabyte (GB)" -> {
                        "${value / 1000000}"
                    }
                    "Terabyte (TB)" -> {
                        "${value / 1000000000}"
                    }

                    else -> "$value"
                }
            }
            "Megabyte (MB)" -> {
                return when(to){
                    "Byte (B)" -> {
                        "${value * 1000000}"
                    }
                    "Kilobyte (kB)" -> {
                        "${value * 1000}"
                    }
                    "Bit (b)" -> {
                        "${value * 8000000}"
                    }
                    "Gigabyte (GB)" -> {
                        "${value / 1000}"
                    }
                    "Terabyte (TB)" -> {
                        "${value / 1000000}"
                    }

                    else -> "$value"
                }
            }
            "Gigabyte (GB)" -> {
                return when(to){
                    "Byte (B)" -> {
                        "${value * 1000000000}"
                    }
                    "Kilobyte (kB)" -> {
                        "${value * 1000000}"
                    }
                    "Megabyte (MB)" -> {
                        "${value * 1000}"
                    }
                    "Bit (b)" -> {
                        "${value * 8000000000}"
                    }
                    "Terabyte (TB)" -> {
                        "${value / 1000}"
                    }

                    else -> "$value"
                }
            }
            "Terabyte (TB)" -> {
                return when(to){
                    "Byte (B)" -> {
                        "${value * 1000000000000}"
                    }
                    "Kilobyte (kB)" -> {
                        "${value * 1000000000}"
                    }
                    "Megabyte (MB)" -> {
                        "${value * 1000000}"
                    }
                    "Gigabyte (GB)" -> {
                        "${value * 1000}"
                    }
                    "Bit (b)" -> {
                        "${value * 8000000000000}"
                    }

                    else -> "$value"
                }
            }
            else -> "NULL"
        }
    }

    private fun convertVolume(from: String, value: Double, to: String) : String{
        return when(from){
            "Liter (L)" -> {
                return when(to){
                    "Gallon (gal)" -> {
                        "${value / 3.78541178}"
                    }
                    "Cubic meter (m³)" -> {
                        "${value / 1000}"
                    }
                    "Cubic decimeter (dm³)" -> {
                        "${value * 1}"
                    }
                    "Cubic centimeter (mm³)" -> {
                        "${value * 1000000}"
                    }
                    else -> "$value"
                }
            }
            "Gallon (gal)" -> {
                return when(to){
                    "Liter (L)" -> {
                        "${value * 3.78541178}"
                    }
                    "Cubic meter (m³)" -> {
                        "${value / 264.172053}"
                    }
                    "Cubic decimeter (dm³)" -> {
                        "${value * 3.78541178}"
                    }
                    "Cubic centimeter (mm³)" -> {
                        "${value * 3785400}"
                    }
                    else -> "$value"
                }
            }
            "Cubic meter (m³)" -> {
                return when(to){
                    "Gallon (gal)" -> {
                        "${value * 264.172053}"
                    }
                    "Liter (L)" -> {
                        "${value * 1000}"
                    }
                    "Cubic decimeter (dm³)" -> {
                        "${value * 1000}"
                    }
                    "Cubic centimeter (mm³)" -> {
                        "${value * 1000000000}"
                    }
                    else -> "$value"
                }
            }
            "Cubic decimeter (dm³)" -> {
                return when(to){
                    "Gallon (gal)" -> {
                        "${value / 3.78541178}"
                    }
                    "Cubic meter (m³)" -> {
                        "${value / 1000}"
                    }
                    "Liter (L)" -> {
                        "${value * 1}"
                    }
                    "Cubic centimeter (mm³)" -> {
                        "${value * 1000000}"
                    }
                    else -> "$value"
                }
            }
            "Cubic centimeter (mm³)" -> {
                return when(to){
                    "Gallon (gal)" -> {
                        "${value / 3785400}"
                    }
                    "Cubic meter (m³)" -> {
                        "${value / 1000000000}"
                    }
                    "Cubic decimeter (dm³)" -> {
                        "${value / 1000000}"
                    }
                    "Liter (L)" -> {
                        "${value / 1000000}"
                    }
                    else -> "$value"
                }
            }

            else -> "NULL"
        }
    }
}


