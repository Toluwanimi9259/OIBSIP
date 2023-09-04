package com.techafresh.unitconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.techafresh.unitconverter.adapter.ConvAdapter
import com.techafresh.unitconverter.databinding.ActivityMainBinding
import com.techafresh.unitconverter.model.Category

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initRecyclerView()
        binding.textViewAbout2.setOnClickListener {
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }

    }

    private fun createList() : List<Category> = listOf(
            Category(R.drawable.area , "Area" , "Hectare, Square Meter, Acre"),
            Category(R.drawable.mass , "Weight/Mass" , "Kilogram, Milligram, Pound"),
            Category(R.drawable.volume , "Volume" , "Liter, Gallon, Cubic Meter"),
            Category(R.drawable.temperature , "Temperature" , "Celsius, Fahrenheit, Kelvin"),
            Category(R.drawable.length , "Length" , "Mile, Meter, Foot"),
            Category(R.drawable.speed , "Speed" , "Miles per Hour, Kilometers per hour"),
            Category(R.drawable.time , "Time" , "Day, Hour, Minute, Second"),
            Category(R.drawable.storage , "Storage" , "Bit, Byte, Kilobyte, Megabyte"),
            Category(R.drawable.pressure , "Pressure" , "Atmosphere, Pascal")
        )

    private fun initRecyclerView(){
        binding.recyclerView.apply {
            adapter = ConvAdapter(createList())
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}