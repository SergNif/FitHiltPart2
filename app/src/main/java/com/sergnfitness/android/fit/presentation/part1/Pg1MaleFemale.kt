package com.sergnfitness.android.fit.presentation.part1

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.Pg1FragmentMaFemale1Binding
import com.sergnfitness.android.fit.presentation.activity.MainActivity


import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part1.part1viewModel.Pg1MaleFemaleViewModel
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Pg1MaleFemale : Fragment() {



    lateinit var paramUser: User

    val taG = "Fragment Page1 MaFemale1 "
    lateinit var binding: Pg1FragmentMaFemale1Binding
    private val args: Pg1MaleFemaleArgs by navArgs<Pg1MaleFemaleArgs>()

    private val viewModel: Pg1MaleFemaleViewModel by viewModels()

    //    lateinit var viewModelFactory: Pg1MaleFemaleViewModelFactory
    val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pg1_fragment_ma_female1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Pg1FragmentMaFemale1Binding.bind(view)
        val bundle = Bundle()

        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser

//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            setTheme(R.style.darkTheme)
//        } else {
//            setTheme(R.style.AppTheme)
//        }
        binding.switchtheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.text1.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
                binding.text2.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
                binding.text3.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))

                Log.e(taG, "AppCompatDelegate  ${AppCompatDelegate.getDefaultNightMode()}")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Log.e(taG, "AppCompatDelegate else ${AppCompatDelegate.getDefaultNightMode()}")
                binding.text1.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
                binding.text2.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
                binding.text3.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
            }

        }
        binding.text1.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
        binding.text2.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))
        binding.text3.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_login_grey))

        showParamOnDisplay()
        Log.e(taG, "woman ${viewModel.dataUser.woman}  man    ${viewModel.dataUser.man}")
        binding.imageViewBoy.setOnClickListener {
            viewModel.changeimageViewBoy()
//            viewModel.save(binding.editTextPage1.text.toString())
//            viewModel.queryOfId(binding.editTextPage1.text.toString().toInt())
            goNext()
        }

        binding.imageViewGirl.setOnClickListener {
            viewModel.changeimageViewGirl()
//            viewModel.load()  // "Save ${userName.email} = ${userName.password}"
//            binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5.execute())
//            binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            goNext()
        }

        binding.editTextPage1.isVisible = false
        binding.textPage1.isVisible = false

//        binding.image.setOnClickListener {
//            val action: NavDirections =
//                Pg1MaleFemaleDirections.actionPg1MaleFemale1ToNext2(args.currentUser,
//                    args.currentDataUser)
////        findNavController().navigate(R.id.action_pg1MaleFemale1_to_next2)
//            findNavController().navigate(action)
//        }

        viewModel.userResourceLiveData.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                is Resource.Success -> {
                    Log.e(taG, " Resource.Success  ${responce.data.toString()}")


//                    viewModel.userLiveData.value.toString().let { binding.textPage1.text = it.toString()}
                    responce.data?.let {
                        Log.e(taG, "$it")
                        //newsAdapter.differ.submitList(it.articles)
                        paramUser = it as User

                        binding.textPage1.text = it.toString()
                    }
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Error -> {
                    Log.e(taG, " Resource.Error  ${responce.message.toString()}")

                    Toast.makeText(requireContext(), responce.message, Toast.LENGTH_LONG).show()
//                    responce.data?.let {
//                        Log.e("checkData", "MainFragment: error: ${it}")
//                    }
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    Log.e(taG, " Resource.Loading  $responce")
                    binding.loading.visibility = View.VISIBLE
                }
            }
        }

        viewModel.live_man.observe(viewLifecycleOwner) {
            if (it) {
                Log.e(taG, "showLiveMan(man)  woman ${viewModel.dataUser.woman}  man    ${viewModel.dataUser.man}")
                binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5.execute())
                binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            }else{
                binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5NoPress.execute())
                binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5.execute())
            }
        }
//        viewModel.live_woman.observe(viewLifecycleOwner) {
//            if (it) {
//                Log.e(taG, "showLiveWoman(woman)  woman ${viewModel.dataUser.woman}  man    ${viewModel.dataUser.man}")
//                binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5NoPress.execute())
//                binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5.execute())
//            }
//        }
    }



    private fun goNext() {
        Log.e(taG, "viewModel.dataUser   ${viewModel.dataUser}")
        val action: NavDirections =
            Pg1MaleFemaleDirections.actionPg1MaleFemale1ToNext2(args.currentUser,
                args.currentDataUser)
//        findNavController().navigate(R.id.action_pg1MaleFemale1_to_next2)
        findNavController().navigate(action)
    }

    private fun showParamOnDisplay() {
        if (viewModel.dataUser.man) {
            Log.e(taG, "showParamOnDisplay(man)  woman ${viewModel.dataUser.woman}  man    ${viewModel.dataUser.man}")
            binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5.execute())
            binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5NoPress.execute())
        }
        else{
            Log.e(taG, "showParamOnDisplay(woman)  woman ${viewModel.dataUser.woman}  man    ${viewModel.dataUser.man}")
            binding.imageViewBoy.setBackgroundResource(changeFonButtonPage5NoPress.execute())
            binding.imageViewGirl.setBackgroundResource(changeFonButtonPage5.execute())
        }
    }
}