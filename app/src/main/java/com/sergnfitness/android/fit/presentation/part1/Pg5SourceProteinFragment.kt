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
import com.sergnfitness.android.fit.databinding.FragmentPg5SourceProteinBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg5SourceProteinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pg5SourceProteinFragment : Fragment() {

    companion object {
        fun newInstance() = Pg5SourceProteinFragment()
    }
    private val taG = "Fragment Pg5SourceProteinFragment"
    private val viewModel: Pg5SourceProteinViewModel by viewModels()
    private lateinit var binding: FragmentPg5SourceProteinBinding
    private val args:Pg5SourceProteinFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg5_source_protein, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg5SourceProteinBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

        viewModel.initLive()

        with(binding){
            textPage5Chiken.setOnClickListener{
                    viewModel.changeChiken()
            }
            textPage5Turkey.setOnClickListener{
                    viewModel.changeTurkey()
            }
            page5ButtonPork.setOnClickListener{
                    viewModel.changePork()
            }
            page5Beef.setOnClickListener{
                    viewModel.changeBeef()
            }
            page5ButtonFish.setOnClickListener{
                    viewModel.changeFish()
            }
            page5Seafood.setOnClickListener{
                    viewModel.changeSeafood()
            }
            page5ButtonWithoutMeat.setOnClickListener{
                    viewModel.changeWithoutMeat()
            }
            textPage5WithoutFish.setOnClickListener{
                    viewModel.changeWithoutFish()
            }
        }

        viewModel.live_text_page5_chiken.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage5Chiken.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage5Chiken.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page5_turkey.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage5Turkey.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage5Turkey.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_page5_button_pork.observe(viewLifecycleOwner) {
            if (it) {
                binding.page5ButtonPork.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.page5ButtonPork.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_page5_beef.observe(viewLifecycleOwner) {
            if (it) {
                binding.page5Beef.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.page5Beef.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_page5_button_fish.observe(viewLifecycleOwner) {
            if (it) {
                binding.page5ButtonFish.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.page5ButtonFish.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_page5_seafood.observe(viewLifecycleOwner) {
            if (it) {
                binding.page5Seafood.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.page5Seafood.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_page5_button_without_meat.observe(viewLifecycleOwner) {
            if (it) {
                binding.page5ButtonWithoutMeat.setBackgroundResource(changeFonButtonPage5.execute())
                binding.textPage5Chiken.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.textPage5Turkey.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page5ButtonPork.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page5Beef.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            } else {
                binding.page5ButtonWithoutMeat.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page5_without_fish.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage5WithoutFish.setBackgroundResource(changeFonButtonPage5.execute())
                binding.page5ButtonFish.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page5Seafood.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            } else {
                binding.textPage5WithoutFish.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }


        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            val action: NavDirections =
                Pg5SourceProteinFragmentDirections.actionPg5SourceProteinFragmentToPg6SourceFiberFragment(
                    viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }

    }

}