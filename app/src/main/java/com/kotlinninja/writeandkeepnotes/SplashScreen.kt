package com.kotlinninja.writeandkeepnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.kotlinninja.writeandkeepnotes.google_signin.SignInActivity
import java.lang.Exception

class SplashScreen : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //hiding actionbar in splash
        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()
        val user =mAuth.currentUser

//        Handler().postDelayed({
//            if (user!=null){
//                val splashActivity=Intent(this, SplashScreen::class.java)
//                startActivity(splashActivity)
//            }
//        },2000)
//
/** if user is not authenticated, sed him to SighInActivity to authenticate first
 * then he goes to MainActivity */

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            // this code will be run after 2 seconds
           // startActivity(Intent(this, MainActivity::class.java))
            if (user!=null){

                startActivity(Intent(this, MainActivity::class.java))
                //from where to where( "this" to "MainActivity")
                this.finish()
            } else{
                startActivity(Intent(this, MainActivity::class.java))
                 // startActivity(Intent(this, SignInActivity::class.java))
            }


            finish()
        }, 3000)

//        val splashTimeOut = 3000
//
//        val splashThread: Thread = object : Thread() {
//            var wait = 0
//            override fun run() {
//                try {
//                    super.run()
//                    while (wait < splashTimeOut) {
//                        sleep(100)
//                        wait += 100
//                    }
//                } catch (e: Exception) {
//                } finally {
//                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
//                    finish()
//                }
//            }
//        }
//        splashThread.start()


        // "Handler" create the timer & set the time
        // Handler is deprecated, for removing it we use 'Looper.getMainLooper()'


    }

}