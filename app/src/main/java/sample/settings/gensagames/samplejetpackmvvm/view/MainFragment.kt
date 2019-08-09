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
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.detail_fragment.*
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.MainFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.AdapterListSpec
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

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

        setupVm()
        observe()
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
    }

    private fun setupVm() {
        sampleContextHelper.logSampleInfo(context!!)

        val viewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }


    private fun observe() {
        binding.viewModel!!.apply {
            loadingInfoItems.observe(this@MainFragment,
                Observer<List<InfoObject>> { t ->
                    val view = AdapterListSpec.buildListItems(context!!, t)
                    binding.mainContent.recyclerHolderView.addView(view)
                })

            headerIntro.observe(this@MainFragment,
                Observer<HeaderIntroObject> { t ->
                    binding.mainContent.headerIntro = t
                })
        }

        /**
         * TODO(Click Event): Move to the VM
         */
        fab.setOnClickListener {
            val dialog = AlertDialog.Builder(context!!)
                .setTitle(getString(android.R.string
                    .VideoView_error_text_unknown))
                .setMessage("Not available right now.")
                .setNeutralButton(android.R.string.ok, null)

            dialog.create().show()
        }
    }
}