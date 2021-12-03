package com.kotlinninja.writeandkeepnotes.google_signin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.kotlinninja.writeandkeepnotes.MainActivity
import com.kotlinninja.writeandkeepnotes.R
import com.kotlinninja.writeandkeepnotes.R.string.default_web_client_id
import com.kotlinninja.writeandkeepnotes.ui.fragments.HomeFragment

class SignInActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN=120
    }

    lateinit var sign_in_btn:Button
    private lateinit var mAuth:FirebaseAuth
    private lateinit var googleSignInClient:GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sign_in_btn= findViewById(R.id.btnSignIn)



        sign_in_btn.setOnClickListener {
            signIn()
        }
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth= FirebaseAuth.getInstance()
//"644720149374-jhlqkj5377kph3ndnenqt6ih1r89v9sq.apps.googleusercontent.com"





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
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "SignInActivity")
                 val intent = Intent(this, HomeFragment::class.java)
                    startActivity(intent)
                    this.finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "SignInActivity", task.exception)

                }
            }
    }

}