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
import com.sergnfitness.android.fit.databinding.FragmentPg9TypicalDayBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg9TypicalDayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pg9TypicalDayFragment : Fragment() {

    companion object {
        fun newInstance() = Pg9TypicalDayFragment()
    }

    private val viewModel: Pg9TypicalDayViewModel by viewModels<Pg9TypicalDayViewModel>()
    private lateinit var binding: FragmentPg9TypicalDayBinding
    private val taG = "Pg9TypicalDayFragment"
    private val args: Pg9TypicalDayFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg9_typical_day, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg9TypicalDayBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

        viewModel.initLive()

        with(binding) {
            page9WorkOffice.setOnClickListener {
                viewModel.changepage9WorkOffice()
            }
            page9InTravel.setOnClickListener {
                viewModel.changepage9InTravel()
            }
            page9DayFoot.setOnClickListener {
                viewModel.changepage9DayFoot()
            }
            page9InHouse.setOnClickListener {
                viewModel.changepage9InHouse()
            }
        }

        viewModel.live_page9_work_office.observe(viewLifecycleOwner) {
            if (it) {
                with(binding) {
                    page9WorkOffice.setBackgroundResource(changeFonButtonPage5.execute())
                    page9InTravel.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9DayFoot.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InHouse.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            } else {
            }
        }
        viewModel.live_page9_in_travel.observe(viewLifecycleOwner) {
            if (it) {
                with(binding) {
                    page9WorkOffice.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InTravel.setBackgroundResource(changeFonButtonPage5.execute())
                    page9DayFoot.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InHouse.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            } else {
            }
        }
        viewModel.live_page9_day_foot.observe(viewLifecycleOwner) {
            if (it) {
                with(binding) {
                    page9WorkOffice.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InTravel.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9DayFoot.setBackgroundResource(changeFonButtonPage5.execute())
                    page9InHouse.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            } else {
            }
        }
        viewModel.live_page9_in_house.observe(viewLifecycleOwner) {
            if (it) {
                with(binding) {
                    page9WorkOffice.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InTravel.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9DayFoot.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page9InHouse.setBackgroundResource(changeFonButtonPage5.execute())
                }
            } else {
            }
        }
        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            val action: NavDirections =
                Pg9TypicalDayFragmentDirections.actionPg9TypicalDayFragmentToPg10BadHabbitsFragment(
                    viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }
    }
}