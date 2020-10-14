package com.example.noteswroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment:Fragment = HomeFragment()
        val fm:FragmentManager = supportFragmentManager
        val ft:FragmentTransaction = fm.beginTransaction().add(R.id.frame_layout,fragment)
        ft.commit()

    }
}