package sample.settings.gensagames.samplejetpackmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_activity.*
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.MainActivityBinding
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sampleContextHelper: SampleContextHelper

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this);

        bind();
        observe()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setSupportActionBar(toolbar)

        val infoAdapter = MainGridInfoAdapter()
        binding.recyclerView.layoutManager =
            GridLayoutManager(this, 2)
        binding.recyclerView.adapter = infoAdapter


        val viewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun observe() {
        binding.viewModel!!.getLoadingInfoItems().observe(this,
            Observer<List<InfoObject>> { t ->
                (binding.recyclerView.adapter as MainGridInfoAdapter)
                    .setInfoCollection(t ?: emptyList())
            })

        binding.viewModel!!.getHeaderIntro().observe(this,
            Observer { t ->
                binding.textMainHeader1.text = t.header
                binding.textMainHeader2.text = t.message
            })
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume.")
        sampleContextHelper.logSampleInfo(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
