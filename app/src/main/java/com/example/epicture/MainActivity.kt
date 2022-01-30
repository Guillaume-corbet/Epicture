package com.example.epicture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior.getTag
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urires = intent.data
        var allData: String
        var token: String
        var account: String
        var begin: Int
        var end: Int

        allData = urires.toString()
        begin = allData.indexOf("access_token=") + 13
        end = allData.indexOf("expires_in=") - 1
        token = allData.substring(begin, end)
        begin = allData.indexOf("account_username=") + 17
        end = allData.indexOf("account_id=") - 1
        account = allData.substring(begin, end)
        println("access Token = $token")
        println("account_username = $account")
        println(urires.toString())
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_bar)
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.HomeFragment -> {
                    var argument = NavArgument.Builder().setDefaultValue(token).build()
                    destination.addArgument("access_token", argument)
                    argument = NavArgument.Builder().setDefaultValue(account).build()
                    destination.addArgument("account_username", argument)
                }
                R.id.SearchFragment -> {
                    var argument = NavArgument.Builder().setDefaultValue(token).build()
                    destination.addArgument("access_token", argument)
                    argument = NavArgument.Builder().setDefaultValue(account).build()
                    destination.addArgument("account_username", argument)
                }
                R.id.ProfilFragment -> {
                    var argument = NavArgument.Builder().setDefaultValue(token).build()
                    destination.addArgument("access_token", argument)
                    argument = NavArgument.Builder().setDefaultValue(account).build()
                    destination.addArgument("account_username", argument)
                }
                R.id.UploadFragment -> {
                    var argument = NavArgument.Builder().setDefaultValue(token).build()
                    destination.addArgument("access_token", argument)
                    argument = NavArgument.Builder().setDefaultValue(account).build()
                    destination.addArgument("account_username", argument)
                }
                R.id.favorisFragment -> {
                    var argument = NavArgument.Builder().setDefaultValue(token).build()
                    destination.addArgument("access_token", argument)
                    argument = NavArgument.Builder().setDefaultValue(account).build()
                    destination.addArgument("account_username", argument)
                }
            }
        }
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}