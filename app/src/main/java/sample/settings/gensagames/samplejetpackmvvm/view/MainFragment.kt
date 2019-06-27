package sample.settings.gensagames.samplejetpackmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.MainFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.MainViewModel
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
        bind()
        observe()
    }

    private fun bind() {
        val infoAdapter = MainGridInfoAdapter()
        binding.recyclerView.layoutManager =
            GridLayoutManager(context,
                GRID_SIZE
            )
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
}