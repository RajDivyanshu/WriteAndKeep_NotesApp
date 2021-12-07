package com.kotlinninja.writeandkeepnotes.google_signin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kotlinninja.writeandkeepnotes.MainActivity
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.ui.fragments.HomeFragment


class SignInActivity : AppCompatActivity() {


    //companion object are like static variable in Java
    companion object {
        private const val RC_SIGN_IN = 25
    }

    lateinit var signInBtn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //hiding actionbar in splash
        supportActionBar?.hide()

        signInBtn = findViewById(R.id.btnSignIn)


        //GSO- google signIn option
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("644720149374-jhlqkj5377kph3ndnenqt6ih1r89v9sq.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        // auth=Firebase.auth
        //"644720149374-jhlqkj5377kph3ndnenqt6ih1r89v9sq.apps.googleusercontent.com"


        signInBtn.setOnClickListener {
            signIn()
        }

//        btnSignIn.setOnClickListener{
//            signIn()
//        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "SignInActivity:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "SignInActivity", e)
                }
            } else {
                Log.w(TAG, "SignInActivity", exception)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "SignInActivity")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    this.finish()

                } else {
                    Toast.makeText(this, "Authentication failed ", Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "SignInActivity", task.exception)

                }
            }
    }

}