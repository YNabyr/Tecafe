package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var edtUsername: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var edtCnfPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var pbLoading: ProgressBar
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize data
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        edtCnfPassword = findViewById(R.id.edtCnfPwd)
        tvLogin = findViewById(R.id.tvLogin)
        btnRegister = findViewById(R.id.btnRegister)
        pbLoading = findViewById(R.id.pbLoading)

        /*
        FirebaseAuth: This is a class provided by the Firebase Authentication library. It allows you
        to authenticate users in your Android application using various methods like email/password, phone number,
        or third-party providers (e.g., Google, Facebook).

        getInstance(): getInstance() is a static method of the FirebaseAuth class. It returns
        an instance of the FirebaseAuth object, which is used to interact with the Firebase Authentication service.

        mAuth: This is a variable (presumably of type FirebaseAuth) that is being assigned the instance of the
        FirebaseAuth object returned by getInstance().
        The prefix "m" is often used in Android development to denote member variables.

        By calling FirebaseAuth.getInstance(), you obtain a single instance of the FirebaseAuth object
        that can be used throughout your Android application to handle user authentication-related tasks,
        such as signing in, signing out, or retrieving user information. Once you have the FirebaseAuth instance assigned
        to mAuth, you can use it to perform authentication operations in your code. */
        mAuth = FirebaseAuth.getInstance()

        // Adding setOnClickListener
        tvLogin.setOnClickListener {
            /*  navigating from one activity to another.
            1. `Intent`: `Intent` is a class in Android used to represent an intent, which is a message that
            describes an operation to be performed. In this case, it represents the intention to start a new activity.

            2. `this`: `this` refers to the current context or activity where this code is being executed.
            It is usually used to specify the context from which the intent is being initiated.

            3. `LoginActivity::class.java`: `LoginActivity::class.java` is a reference to the `LoginActivity` class.
            It is used to specify the target activity to which we want to navigate.

            4. `val intent = Intent(this, LoginActivity::class.java)`: This line creates a new instance of the `Intent` class
            with the current activity as the context and the `LoginActivity` class as the target activity.

            5. `startActivity(intent)`: This line starts the `LoginActivity` by invoking the `startActivity` method with
            the `intent` object as the parameter. It initiates the transition from the current activity to the `LoginActivity`.

            In summary, the code creates an intent to start the `LoginActivity` from the current activity
            and then initiates the activity transition using `startActivity(intent)`. */
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        btnRegister.setOnClickListener {
            pbLoading.visibility = View.VISIBLE
            val username = edtUsername.text.toString()
            val pwd = edtPassword.text.toString()
            val cnfPwd = edtCnfPassword.text.toString()
            if (pwd != cnfPwd) {
                Toast.makeText(this, "Please check both password", Toast.LENGTH_SHORT).show()
            } else if
                           (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(
                    cnfPwd
                )
            ) {
                Toast.makeText(this, "Text Input cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                mAuth.createUserWithEmailAndPassword(username,pwd).addOnCompleteListener{
                   if(it.isSuccessful()){
                       pbLoading.visibility = View.VISIBLE
                       Toast.makeText(this, "User Registered..", Toast.LENGTH_SHORT).show()
                       val intent = Intent(this, LoginActivity::class.java)
                       startActivity(intent)
                       finish()
                   }else {
                       pbLoading.visibility = View.GONE
                       Toast.makeText(this, "Fail to Register..", Toast.LENGTH_SHORT).show()
                   }
                }
            }
        }
    }
}