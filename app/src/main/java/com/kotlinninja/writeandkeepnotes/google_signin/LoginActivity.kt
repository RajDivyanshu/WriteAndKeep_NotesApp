package com.kotlinninja.writeandkeepnotes.google_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kotlinninja.writeandkeepnotes.MainActivity
import com.kotlinninja.writeandkeepnotes.R

class LoginActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnSignUp: Button
    lateinit var btnSignUpFromLogin:TextView

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        etEmail= findViewById(R.id.etEmailAddress)
        etPassword= findViewById(R.id.etPassword)
        btnSignUp= findViewById(R.id.btnLogIn)
        btnSignUpFromLogin= findViewById(R.id.btnSignUpFromLogin)

        firebaseAuth= FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            login()
        }

        btnSignUpFromLogin.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun login(){
        val email:String= etEmail.text.toString()
        val password:String= etPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        //signIn for existing user
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){

                    Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else{

                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}