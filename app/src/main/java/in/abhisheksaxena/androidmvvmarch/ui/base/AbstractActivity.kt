package `in`.abhisheksaxena.androidmvvmarch.ui.base

import `in`.abhisheksaxena.androidmvvmarch.R
import `in`.abhisheksaxena.androidmvvmarch.databinding.ActivityAbstractBinding
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

abstract class AbstractActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityAbstractBinding

    @get:NavigationRes
    abstract val graphRes: Int

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_abstract)

        initNavHostFragment(graphRes)

        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    private fun initNavHostFragment(graphRes: Int) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navInflater = navHostFragment.navController.navInflater
        navHostFragment.navController.graph = navInflater.inflate(graphRes)
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}