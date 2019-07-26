package sample.settings.gensagames.samplejetpackmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.detail_fragment.*
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.MainFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    companion object {
        const val GRID_SIZE = 2
    }

    @Inject
    lateinit var sampleContextHelper: SampleContextHelper

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        setupToolbar()
        bind()
        observe()
    }
    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
    }

    private fun bind() {
        sampleContextHelper.logSampleInfo(context!!)

        val infoAdapter = MainGridInfoAdapter()
        binding.mainContent.recyclerView.layoutManager =
            GridLayoutManager(context,
                GRID_SIZE
            )
        binding.mainContent.recyclerView.adapter = infoAdapter

        val viewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)
        binding.viewModel = viewModel

        fab.setOnClickListener {
            val dialog = AlertDialog.Builder(context!!)
                .setTitle(getString(android.R.string
                    .VideoView_error_text_unknown))
                .setMessage("Not available right now.")
                .setNeutralButton(android.R.string.ok, null)

            dialog.create().show()
        }
    }


    private fun observe() {
        binding.viewModel!!.getLoadingInfoItems().observe(this,
            Observer<List<InfoObject>> { t ->
                (binding.mainContent.recyclerView.adapter as MainGridInfoAdapter)
                    .setInfoCollection(t ?: emptyList())
            })

        binding.viewModel!!.getHeaderIntro().observe(this,
            Observer { t ->
                binding.mainContent.textMainHeader1.text = t.header
                binding.mainContent.textMainHeader2.text = t.message
            })
    }
}