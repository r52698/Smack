package com.example.smack.Controller

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.R
import com.example.smack.Services.AuthService
import com.example.smack.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility = View.INVISIBLE
    }

    fun loginLoginBtnClicked (view: View) {
        loginSpinner.visibility = View.VISIBLE
        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()
        println("Trying to login email $email password $password")
        AuthService.loginUser(this, email, password) { loginSuccess ->
            if (loginSuccess) {
                println("Login completed successfully")
                AuthService.findUserByEmail(this) { findUserSuccess ->
                    if (findUserSuccess) {
                        println("Find user by email completed successfully")
                        val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                        LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
                        finish()
                    } else {
                        errorToast()
                        loginSpinner.visibility = View.INVISIBLE
                        println("The find by email attempt was not successful")
                    }
                }
            } else {
                println("The login attempt was not successful")
                errorToast()
                loginSpinner.visibility = View.INVISIBLE
            }
        }
    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong, please try again.", Toast.LENGTH_LONG).show()
    }

    fun loginCreateUserBtnClicked (view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()
    }
}
