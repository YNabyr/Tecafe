package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecafe.databinding.ActivityMejaOptionBinding

class MejaOption : AppCompatActivity() {

    private lateinit var binding: ActivityMejaOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMejaOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftarMeja.setOnClickListener{
            val intent = Intent(this, DaftarMeja::class.java)
            startActivity(intent)
        }

        binding.btnAddMeja.setOnClickListener{
            val intent = Intent(this, AddMeja::class.java)
            startActivity(intent)
        }

    }
}