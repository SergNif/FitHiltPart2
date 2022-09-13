package com.sergnfitness.android.fit.presentation.part1

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.FragmentPg10BadHabbitsBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg10BadHabbitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pg10BadHabbitsFragment : Fragment() {

    companion object {
        fun newInstance() = Pg10BadHabbitsFragment()
    }

    private  val viewModel: Pg10BadHabbitsViewModel by viewModels<Pg10BadHabbitsViewModel>()
private lateinit var binding: FragmentPg10BadHabbitsBinding
    private val taG = "Pg10BadHabbitsFragment"
    private val args: Pg10BadHabbitsFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg10_bad_habbits, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg10BadHabbitsBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

        viewModel.initLive()

        with(binding) {
            page10FastFood.setOnClickListener{
                viewModel.changeFastFood()
            }
            page10EatNigth.setOnClickListener{
                viewModel.changeEatNigth()
            }
            page10FastShugar.setOnClickListener{
                viewModel.changeFastShugar()
            }
            page10NothingOfList.setOnClickListener{
                viewModel.changeNothingOfList()
            }
            binding.textBack.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.bar_menu_navigation_fon_violet)))
        }

        viewModel.live_page10_fast_food.observe(viewLifecycleOwner){
            if (it){
                with(binding){
                    page10FastFood.setBackgroundResource(changeFonButtonPage5.execute())
                    page10EatNigth.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10FastShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10NothingOfList.setBackgroundResource(changeFonButtonPage5NoPress.execute())

                    viewModel.dataUser.fastFood = true
                    viewModel.dataUser.laterNight = false
                    viewModel.dataUser.fastSugar = false
                    viewModel.dataUser.Nothing = false
                }
            }
        }
        viewModel.live_page10_eat_nigth.observe(viewLifecycleOwner){
            if (it){
                with(binding){
                    page10FastFood.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10EatNigth.setBackgroundResource(changeFonButtonPage5.execute())
                    page10FastShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10NothingOfList.setBackgroundResource(changeFonButtonPage5NoPress.execute())

                    viewModel.dataUser.fastFood = false
                    viewModel.dataUser.laterNight = true
                    viewModel.dataUser.fastSugar = false
                    viewModel.dataUser.Nothing = false
                }
            }
        }
        viewModel.live_page10_fast_shugar.observe(viewLifecycleOwner){
            if (it){
                with(binding){
                    page10FastFood.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10EatNigth.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10FastShugar.setBackgroundResource(changeFonButtonPage5.execute())
                    page10NothingOfList.setBackgroundResource(changeFonButtonPage5NoPress.execute())

                    viewModel.dataUser.fastFood = false
                    viewModel.dataUser.laterNight = false
                    viewModel.dataUser.fastSugar = true
                    viewModel.dataUser.Nothing = false
                }
            }
        }
        viewModel.live_page10_nothing_of_list.observe(viewLifecycleOwner){
            if (it){
                with(binding){
                    page10FastFood.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10EatNigth.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10FastShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                    page10NothingOfList.setBackgroundResource(changeFonButtonPage5.execute())

                    viewModel.dataUser.fastFood = false
                    viewModel.dataUser.laterNight = false
                    viewModel.dataUser.fastSugar = false
                    viewModel.dataUser.Nothing = true
                }
            }
        }
        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            args.currentUser.email?.let { it1 -> viewModel.createDataUserOnServer(it1) }
            val action: NavDirections =
                Pg10BadHabbitsFragmentDirections.actionPg10BadHabbitsFragmentToPart2Page1Fragment(
                   viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }
    }

}