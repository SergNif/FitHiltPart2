package com.sergnfitness.android.fit.presentation.part1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.Pg2NextBinding
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg2NextViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Pg2NextFragment : Fragment(R.layout.pg2_next) {

    private val args : Pg2NextFragmentArgs by navArgs()


    val taG = "Fragment Page3 DataAgeHightWeight MaFemale1"
    lateinit var binding: Pg2NextBinding
    private val viewModel: Pg2NextViewModel by viewModels<Pg2NextViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pg2_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Pg2NextBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser
        Log.e(taG, "budle bundle ${this.args}")
        binding.textBack.setOnClickListener {
//            if (modifiedButton){viewModel.createLocalDataPage5()}
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            val action: NavDirections =
                Pg2NextFragmentDirections.actionNext2ToDataAgeHightWeight2(currentUser = viewModel.userClass,
                    currentDataUser = viewModel.dataUser)        //.actionNext2ToDataAgeHightWeight2(args.currentUser as User)       //actionNext2ToDataAgeHightWeight2
//        if (modifiedButton){viewModel.createLocalDataPage5()}
            findNavController().navigate(action)
        }
    }

}