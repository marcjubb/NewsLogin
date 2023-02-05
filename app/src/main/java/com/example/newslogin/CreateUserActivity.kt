package com.example.newslogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class CreateUserActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var createButton: Button

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.create_user_page)
        createButton = findViewById(R.id.btn_create_user)
            createButton.setOnClickListener {
            email = findViewById(R.id.et_email)
            password = findViewById(R.id.et_password)

            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

                createUser(email.text.toString(),password.text.toString());

        }
    }



    private lateinit var auth: FirebaseAuth

    private fun createUser(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {


                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        } else {
            println("fuk")
        }
    }



}
