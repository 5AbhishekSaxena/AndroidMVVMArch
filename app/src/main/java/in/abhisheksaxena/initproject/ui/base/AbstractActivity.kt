package `in`.abhisheksaxena.initproject.ui.base

import `in`.abhisheksaxena.initproject.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AbstractActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abstract)
    }
}