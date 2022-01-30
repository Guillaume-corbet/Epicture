package com.example.epicture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LogActivity : AppCompatActivity() {

    private val clientId: String? = "360beb24696028b"
    private val clientSecret: String? = "f0bda692b40b36a39dad2a9834917446ba874f92"
    private val urlAuth = "https://api.imgur.com/oauth2/authorize"
    private var accessToken: String? = null
    private var accountUsername: String? = null
    private var refreshToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = Uri.parse(urlAuth).buildUpon()
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("response_type", "token")
            .appendQueryParameter("state", "init")
            .build()

        val intent = Intent()
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}