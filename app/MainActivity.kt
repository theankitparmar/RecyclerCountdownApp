package com.example.recyclercountdownapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclercountdownapp.adapter.TimerAdapter
import com.example.recyclercountdownapp.databinding.ActivityMainBinding
import com.example.recyclercountdownapp.model.TimerItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TimerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.submitButton.setOnClickListener {
            val start = binding.startEditText.text.toString().toIntOrNull()
            val end = binding.endEditText.text.toString().toIntOrNull()

            if (start != null && end != null && end >= start) {
                val numbers = (start..end).shuffled()
                val items = numbers.map {
                    TimerItem(it, endTimeMillis = System.currentTimeMillis() + it * 1000L)
                }

                adapter = TimerAdapter(items)
                binding.recyclerView.adapter = adapter
            } else {
                Toast.makeText(this, "Enter valid start and end values", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
