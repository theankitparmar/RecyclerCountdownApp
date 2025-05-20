package com.example.recyclercountdownapp.adapter


import android.graphics.Color
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclercountdownapp.databinding.ItemTimerBinding
import com.example.recyclercountdownapp.model.TimerItem

class TimerAdapter(private val items: List<TimerItem>) :
    RecyclerView.Adapter<TimerAdapter.TimerViewHolder>() {

    inner class TimerViewHolder(private val binding: ItemTimerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var timer: CountDownTimer? = null

        fun bind(item: TimerItem) {
            // Cancel any previous timer to avoid overlap
            timer?.cancel()

            val timeLeftMillis = item.endTimeMillis - System.currentTimeMillis()

            if (timeLeftMillis <= 0 || item.isDone) {
                item.isDone = true
                binding.itemText.text = "Done"
                binding.itemText.setTextColor(Color.GREEN)
            } else {
                binding.itemText.setTextColor(Color.BLACK)

                timer = object : CountDownTimer(timeLeftMillis, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val seconds = millisUntilFinished / 1000
                        binding.itemText.text = "$seconds seconds remaining"
                    }

                    override fun onFinish() {
                        item.isDone = true
                        binding.itemText.text = "Done"
                        binding.itemText.setTextColor(Color.GREEN)
                    }
                }.also { it.start() }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        val binding = ItemTimerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TimerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: TimerViewHolder) {
        super.onViewRecycled(holder)
        holder.timer?.cancel() // Clean up to prevent memory leaks
    }

    override fun getItemCount(): Int = items.size
}
