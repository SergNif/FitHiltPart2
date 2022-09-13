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
import com.sergnfitness.android.fit.databinding.FragmentPg8WaterDrinkBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg8WaterDrinkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pg8WaterDrinkFragment : Fragment() {

    companion object {
        fun newInstance() = Pg8WaterDrinkFragment()
    }

    private val taG = "Pg8WaterDrinkFragment "
    private val viewModel: Pg8WaterDrinkViewModel by viewModels<Pg8WaterDrinkViewModel>()
    private lateinit var binding: FragmentPg8WaterDrinkBinding
    private val args: Pg8WaterDrinkFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg8_water_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg8WaterDrinkBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

        viewModel.initLive()

        with(binding) {
            page8ButtonWaterWithoutGas.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changepage8ButtonWaterWithoutGas(true)

                } else {
                    viewModel.changepage8ButtonWaterWithoutGas(false)
                    page8ButtonWaterShugar.isChecked = false

                    page8ButtonMoreCofee.isChecked = false
                    page8ButtonOnlyTea.isChecked = false
                }
            }
            page8ButtonWaterShugar.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changepage8ButtonWaterShugar(true)
//                    page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5.execute())
//                    page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                } else {
                    viewModel.changepage8ButtonWaterShugar(false)
                    page8ButtonWaterWithoutGas.isChecked = false
                    page8ButtonMoreCofee.isChecked = false
                    page8ButtonOnlyTea.isChecked = false
                }
            }
            page8ButtonMoreCofee.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changepage8ButtonMoreCofee(true)
//                    page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5.execute())
//                    page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                } else {
                    viewModel.changepage8ButtonMoreCofee(false)
                    page8ButtonWaterWithoutGas.isChecked = false
                    page8ButtonWaterShugar.isChecked = false
                    page8ButtonOnlyTea.isChecked = false
                }
            }
            page8ButtonOnlyTea.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changepage8ButtonOnlyTea(true)
//                    page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                    page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changepage8ButtonOnlyTea(false)
                    page8ButtonWaterWithoutGas.isChecked = false
                    page8ButtonWaterShugar.isChecked = false
                    page8ButtonMoreCofee.isChecked = false
                }
            }
        }

        viewModel.live_page8_button_water_without_gas.observe(viewLifecycleOwner){
            if (it){
                binding.page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5.execute())
                binding.page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }else{

            }
        }
        viewModel.live_page8_button_water_shugar.observe(viewLifecycleOwner){
            if (it){
                binding.page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5.execute())
                binding.page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }else{

            }
        }
        viewModel.live_page8_button_more_cofee.observe(viewLifecycleOwner){
            if (it){
                binding.page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5.execute())
                binding.page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }else{

            }
        }
        viewModel.live_page8_button_only_tea.observe(viewLifecycleOwner){
            if (it){
                binding.page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5.execute())
            }else{

            }
        }
        viewModel.live_page4_button_every_day_fitness.observe(viewLifecycleOwner){
            if (it){
                binding.page8ButtonWaterWithoutGas.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonWaterShugar.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonMoreCofee.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.page8ButtonOnlyTea.setBackgroundResource(changeFonButtonPage5.execute())
            }else{

            }
        }

        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            val action: NavDirections =
                Pg8WaterDrinkFragmentDirections.actionPg8WaterDrinkFragmentToPg9TypicalDayFragment(
                    viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }
      }



    fun onClickHouse(view: View) {}
    fun onClickHistoryWeightDiagramm(view: View) {}
}