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
import com.kotlinninja.writeandkeepnotes.databinding.ActivitySignUpBinding

 class SignUpActivity : AppCompatActivity() {
//     lateinit var etEmail:EditText
//     lateinit var etPassword:EditText
//     lateinit var etConfirmPassword:EditText
//     lateinit var btnSignUp:Button
//     lateinit var btnLogInFromSignup:TextView

     //using data binding for binding views
     lateinit var binding:ActivitySignUpBinding
     lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        supportActionBar?.hide()

     //  etEmail= findViewById(R.id.etEmailAddress)
     //   etPassword= findViewById(R.id.etPassword)
       // etConfirmPassword= findViewById(R.id.etConfirmPassword)
      //  btnSignUp= findViewById(R.id.btnSignUp)
       // btnLogInFromSignup= findViewById(R.id.btnLogInFromSignup)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            signUp()
        }

        binding.btnLogInFromSignup.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

     private fun signUp(){
        val email:String= binding.etEmailAddress.text.toString()
         val password:String= binding.etPassword.text.toString()
         val confirmPassword= binding.etConfirmPassword.text.toString()

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