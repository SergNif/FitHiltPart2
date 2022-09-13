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
import com.sergnfitness.android.fit.databinding.FragmentPg6SourceFiberBinding
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg6SourceFiberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pg6SourceFiberFragment : Fragment() {

    companion object {
        fun newInstance() = Pg6SourceFiberFragment()
    }

    private val taG = "Fragment Pg6SourceFiberFragment"
    private val viewModel: Pg6SourceFiberViewModel by viewModels<Pg6SourceFiberViewModel>()
    lateinit var binding: FragmentPg6SourceFiberBinding
    private val args: Pg6SourceFiberFragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pg6_source_fiber, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPg6SourceFiberBinding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

        viewModel.initLive()

        with(binding) {
            textPage6Zukini.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Zukini(true)
//                    textPage6Zukini.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Zukini(false)
//                    textPage6Zukini.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Tomato.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Tomato(true)
//                    textPage6Tomato.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Tomato(false)
//                    textPage6Tomato.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Baklagan.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Baklagan(true)
//                    textPage6Baklagan.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Baklagan(false)
//                    textPage6Baklagan.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6ColorCabbage.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6ColorCabbage(true)
//                    textPage6ColorCabbage.setBackgroundResource(changeFonButtonPage5.execute())

                } else {
                    viewModel.changetextPage6ColorCabbage(false)
//                    textPage6ColorCabbage.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Ogurz.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Ogurz(true)
//                    textPage6Ogurz.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Ogurz(false)
//                    textPage6Ogurz.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Broccoli.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Broccoli(true)
//                    textPage6Broccoli.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Broccoli(false)
//                    textPage6Broccoli.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6Avocado.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6ButtonMuhroms(true)
//                    textPage6Avocado.setBackgroundResource(changeFonButtonPage5.execute())

                } else {
                    viewModel.changetextPage6ButtonMuhroms(false)
//                    textPage6Avocado.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
            textPage6ButtonMuhroms.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.changetextPage6Avocado(true)
//                    textPage6ButtonMuhroms.setBackgroundResource(changeFonButtonPage5.execute())
                } else {
                    viewModel.changetextPage6Avocado(false)
//                    textPage6ButtonMuhroms.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                }
            }
        }

        viewModel.live_text_page6_zukini.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Zukini.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Zukini.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_tomato.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Tomato.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Tomato.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_baklagan.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Baklagan.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Baklagan.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_color_cabbage.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6ColorCabbage.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6ColorCabbage.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_ogurz.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Ogurz.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Ogurz.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_broccoli.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Broccoli.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Broccoli.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_button_muhroms.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6Avocado.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6Avocado.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }
        viewModel.live_text_page6_avocado.observe(viewLifecycleOwner) {
            if (it) {
                binding.textPage6ButtonMuhroms.setBackgroundResource(changeFonButtonPage5.execute())
            } else {
                binding.textPage6ButtonMuhroms.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }
        }

        binding.textBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener {
            Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
            Log.e(taG, "${args.currentUser}")
            val action: NavDirections =
                Pg6SourceFiberFragmentDirections.actionPg6SourceFiberFragmentToPg7FoodsFragment(
                    viewModel.userClass, viewModel.dataUser)
            findNavController().navigate(action)
        }
    }
}