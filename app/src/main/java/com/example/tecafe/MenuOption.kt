package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecafe.databinding.ActivityMenuOptionBinding

class MenuOption : AppCompatActivity() {

    private lateinit var binding: ActivityMenuOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftarMenu.setOnClickListener{
            val intent = Intent(this, DaftarMenu::class.java)
            startActivity(intent)
        }

        binding.btnAddMenu.setOnClickListener{
            val intent = Intent(this, AddMenuActivity::class.java)
            startActivity(intent)
        }
    }
}