package com.example.smack.Controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smack.R
import com.example.smack.Services.AuthService
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        if (color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }

        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceId)
    }

    fun generateColorClicked(view: View) {
        val random = Random()
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))
        avatarColor = "[${r/256.0}, ${g/256.0}, ${b/256.0}, 1]"
        println(avatarColor)
    }

    fun createUserClicked(view: View) {
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()
        AuthService.registerUser(this, email, password) {registerSuccess ->
            if (registerSuccess) {
                println ("Create user completed successfully")
                AuthService.loginUser(this, email, password) { loginSuccess ->
                    if (loginSuccess) {
                        println ("Login user completed successfully")
                        println ("Token=${AuthService.authToken}, Email=${AuthService.userEmail}")
                    }
                }
            }
        }
    }
}
