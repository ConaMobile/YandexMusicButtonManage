package com.conamobile.yandexmusicbuttonmanage

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.conamobile.yandexmusicbuttonmanage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupSticky()
    }

    private fun setupSticky() {
        binding.clButNowSticky.isVisible =
            binding.nestedScrollView.isViewVisible(binding.clButNowFixed) == false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.nestedScrollView.setOnScrollChangeListener { _, _, _, _, _ ->
                if (!binding.nestedScrollView.isViewVisible(binding.clButNowFixed)) {
                    binding.clButNowSticky.isVisible = true
                    binding.clButNowFixed.isVisible = false
                } else {
                    binding.clButNowSticky.isVisible = false
                    binding.clButNowFixed.isVisible = true
                }
                binding.clButNowSticky.isVisible = binding.nestedScrollView.isViewVisible(binding.clButNowFixed) == false
            }
        }
    }

    private fun NestedScrollView.isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        this.getDrawingRect(scrollBounds)
        val bottom = view.height + view.y
        return scrollBounds.bottom > bottom
    }
}