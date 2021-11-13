package be.faxifin.androidtv.presentation.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import be.faxifin.androidtv.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment: Fragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragmentContainer, fragment)
                .commit()
        }
    }
}