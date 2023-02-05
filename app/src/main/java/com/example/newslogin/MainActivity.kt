package com.example.newslogin


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var create_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)





        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.login_btn)
        create_btn = findViewById(R.id.create_btn)






        loginButton.setOnClickListener {

            val email = email.text.toString()
            val pass = password.text.toString()
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            login(email,pass);

        }
        create_btn.setOnClickListener {
            switchActivitiesCreate();
        }
    }





    private fun switchActivitiesCreate() {
        val switchActivityIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(switchActivityIntent)
    }
    private fun switchActivitiesHome() {
        val switchActivityIntent = Intent(this, NewsActivity::class.java)
        startActivity(switchActivityIntent)
    }


    private lateinit var auth: FirebaseAuth

    fun login(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            switchActivitiesHome()
        } else {
            switchActivitiesHome()// User is signed out, update UI accordingly
        }
    }
}