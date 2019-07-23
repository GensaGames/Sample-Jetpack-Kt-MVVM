package sample.settings.gensagames.samplejetpackmvvm.view

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.databinding.DetailFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.databinding.MainFragmentBinding
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.DetailViewModel
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel

class DetailFragment : Fragment() {

    private lateinit var args : DetailFragmentArgs

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        args = DetailFragmentArgs.fromBundle(
            requireActivity().intent.extras!!)
        Log.d(TAG, "onCreateView with Args: $args")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(
            DetailViewModel::class.java)

        bind()
        observe()
    }

    private fun bind() {
        val viewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun observe() {
        binding.viewModel!!.textContent.observe(this, Observer<String>{
            t -> binding.detailContent.textViewContent.text = t
        })
    }

}
