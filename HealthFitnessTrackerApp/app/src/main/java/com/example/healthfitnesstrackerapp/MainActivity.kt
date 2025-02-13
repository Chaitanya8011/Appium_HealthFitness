package com.example.healthfitnesstrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val viewPager: ViewPager2 = findViewById(R.id.viewPager)
         val adapter = OnboardingPagerAdapter(this)
         viewPager.adapter = adapter
         val btnSkip: Button = findViewById(R.id.btnSkip)
        btnSkip.setOnClickListener {
            navigateToDashboard()
        }
      viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
          override fun onPageSelected(position: Int) {
              if (position == 2) {
                  navigateToDashboard()
              }
          }

      })
    }
    private fun navigateToDashboard() {
        val intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}