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
import com.sergnfitness.android.fit.databinding.FragmentPg7FoodsBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg7FoodsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Pg7FoodsFragment : Fragment() {

    companion object {
        fun newInstance() = Pg7FoodsFragment()
    }

    private val taG = "Pg7FoodsFragment"
    private val viewModel: Pg7FoodsViewModel by viewModels<Pg7FoodsViewModel>()
    private lateinit var binding: FragmentPg7FoodsBinding
    private val args: Pg7FoodsFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg7_foods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg7FoodsBinding.bind(view)

        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser
        viewModel.initLive()
//
//        textPage6Egg
//        textPage6Chees
//        textPage6Nut
//        textPage6CottageCheese
//        textPage6Kefir
//        textPage6Yogurt

        with(binding) {
            textPage6Egg.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Egg(true)
//                    textPage6Egg.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Egg(false)
//                    textPage6Egg.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Chees.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Chees(true)
//                    textPage6Chees.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Chees(false)
//                    textPage6Chees.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Nut.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Nut(true)
//                    textPage6Nut.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Nut(false)
//                    textPage6Nut.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6CottageCheese.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6CottageCheese(true)
//                    textPage6CottageCheese.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6CottageCheese(false)
//                    textPage6CottageCheese.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Kefir.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Kefir(true)
//                    textPage6Kefir.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Kefir(false)
//                    textPage6Kefir.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Yogurt.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Yogurt(true)
//                    textPage6Yogurt.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Yogurt(false)
//                    textPage6Yogurt.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
        }
        viewModel.live_text_page6_egg.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6Egg.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6Egg.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_chees.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6Chees.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6Chees.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_nut.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6Nut.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6Nut.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_cottage_cheese.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6CottageCheese.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6CottageCheese.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_kefir.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6Kefir.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6Kefir.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_yogurt.observe(viewLifecycleOwner){
            if (it){
                binding.textPage6Yogurt.setBackgroundResource(changeFonButtonPage5.execute())
            }else{
                binding.textPage6Yogurt.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            val action: NavDirections =
                Pg7FoodsFragmentDirections.actionPg7FoodsFragmentToPg8WaterDrinkFragment(
                    viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }
    }
}
