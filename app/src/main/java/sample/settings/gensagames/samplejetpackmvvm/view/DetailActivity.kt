package sample.settings.gensagames.samplejetpackmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import kotlinx.android.synthetic.main.main_activity.*
import sample.settings.gensagames.samplejetpackmvvm.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val INFO_OBJECT_TAG = "infoObject"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setSupportActionBar(toolbar)
    }

}
