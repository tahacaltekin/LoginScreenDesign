package com.taha.loginscreendesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.taha.loginscreendesign.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
    }

    fun signUpButton(view: View) {

        val email = binding.signUpEmail.text.toString()
        val password = binding.signUpPassword.text.toString()
        val userName = binding.userName.text.toString()
        val terms = binding.signUpCheckbox

        if (email.equals("") || password.equals("") || userName.equals("")) {
            Toast.makeText(this, "Enter username,email and password!", Toast.LENGTH_LONG).show()
        } else if (!terms.isChecked) {
            Toast.makeText(this, "Accept the terms and conditions!", Toast.LENGTH_LONG).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}