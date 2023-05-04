package hu.bme.aut.android.bggapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.bggapplication.databinding.MainActivityBinding
import hu.bme.aut.android.bggapplication.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}