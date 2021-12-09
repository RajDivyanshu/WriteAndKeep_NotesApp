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

 class SignUpActivity : AppCompatActivity() {
     lateinit var etEmail:EditText
     lateinit var etPassword:EditText
     lateinit var etConfirmPassword:EditText
     lateinit var btnSignUp:Button
     lateinit var btnLogInFromSignup:TextView

     lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

       etEmail= findViewById(R.id.etEmailAddress)
        etPassword= findViewById(R.id.etPassword)
        etConfirmPassword= findViewById(R.id.etConfirmPassword)
        btnSignUp= findViewById(R.id.btnSignUp)
        btnLogInFromSignup= findViewById(R.id.btnLogInFromSignup)

        firebaseAuth= FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            signUp()
        }

        btnLogInFromSignup.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

     private fun signUp(){
        val email:String= etEmail.text.toString()
         val password:String= etPassword.text.toString()
         val confirmPassword= etConfirmPassword.text.toString()

         //validation
         if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
             Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
             return
         }
         if (password!=confirmPassword){
             Toast.makeText(this, "Password and Confirm password do not match", Toast.LENGTH_SHORT).show()
             return
         }

            // creating new user
         firebaseAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this){
                 if(it.isSuccessful){
                     Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                     val intent=Intent(this, MainActivity::class.java)
                     startActivity(intent)
                     finish()

                 } else{
                     Toast.makeText(this,"Error creating user", Toast.LENGTH_SHORT).show()
                 }
             }
     }
}