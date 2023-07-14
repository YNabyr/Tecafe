package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.tecafe.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mAuth = FirebaseAuth.getInstance()

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            binding.pbLoading.visibility = View.VISIBLE
            val username = binding.edtUsername.text.toString()
            val pwd = binding.edtPassword.text.toString()

            if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "Text Field cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                mAuth.signInWithEmailAndPassword(username, pwd).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this, "Login succesful..", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = mAuth.currentUser

        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}