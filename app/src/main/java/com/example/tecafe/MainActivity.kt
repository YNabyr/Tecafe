package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tecafe.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.btnMenu.setOnClickListener{
            val intent = Intent(this, MenuOption::class.java)
            startActivity(intent)
        }

        binding.btnMeja.setOnClickListener{
            val intent = Intent(this, MejaOption::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            mAuth.signOut()
            Toast.makeText(this,"Berhasil Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}