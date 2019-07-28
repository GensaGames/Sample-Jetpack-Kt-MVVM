package sample.settings.gensagames.samplejetpackmvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.view.base.BaseActivity

class DetailActivity : BaseActivity() {

    companion object {
        const val INFO_OBJECT_TAG = "infoObject"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
    }

}
