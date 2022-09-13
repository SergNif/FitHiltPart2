package com.sergnfitness.android.fit.presentation.part2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
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
import com.sergnfitness.android.fit.databinding.FragmentMenuDayPart2Binding
import com.sergnfitness.android.fit.databinding.FragmentNewOneMenuDayBinding
import com.sergnfitness.android.fit.presentation.part2.part2viewModel.MenuDayPart2ViewModel
import com.sergnfitness.android.fit.presentation.part2.part2viewModel.NewOneMenuDayViewModel
import com.sergnfitness.domain.models.UserMenuDay
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class NewOneMenuDayFragment : Fragment() {

    companion object {
        fun newInstance() = NewOneMenuDayFragment()
    }

    private val taG = "NewOneMenuDayFragment "
    private val viewModel: NewOneMenuDayViewModel by viewModels()
    private lateinit var binding: FragmentNewOneMenuDayBinding
    private val args: NewOneMenuDayFragmentArgs by navArgs()

    lateinit var oneMenuDay: UserMenuDay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_new_one_menu_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser
        binding = FragmentNewOneMenuDayBinding.bind(view)
        binding.time.hint =
            LocalDateTime.now().toString().split(".")[0].split("T")[1].split(":").slice(0..1)
                .joinToString(
                    //   prefix = "[",
                    separator = ":",
                    // postfix = "]",
                    //limit = 3,
                    //truncated = "...",
                    //transform = { it.uppercase() }
                )
        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
        if (viewModel.positionRecycklerViewEdit != null) {
            binding.headerMenuDay.setText(viewModel.listMenuDay[viewModel.positionRecycklerViewEdit!!].data.toString())
            binding.menuDay.setText(viewModel.listMenuDay[viewModel.positionRecycklerViewEdit!!].menu.toString())
            binding.weightOneMenuDay.setText(viewModel.listMenuDay[viewModel.positionRecycklerViewEdit!!].weight.toString())
        }
        binding.houseButton.setOnClickListener {
            val action: NavDirections =
                NewOneMenuDayFragmentDirections.actionNewOneMenuDayFragmentToPart2Page1Fragment(
                    viewModel.userClass, viewModel.dataUser
                )
            findNavController().navigate(action)
        }
        binding.enterMenuDay.setOnClickListener {

//            Toast.makeText(activity, "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show()

//            binding.weightOneMenuDay.text.toString().toDouble().let {
//               val weig = it
//            }
            if (binding.weightOneMenuDay.text.isNullOrEmpty()) binding.weightOneMenuDay.text = viewModel.dataUser.weight.toEditable()
            println("enter Menu Day ${viewModel.dataUser}")

            oneMenuDay =
                viewModel.userClass.id.let { it1 ->
                    viewModel.createUserMenuDay(
                        id = it1!!,
                        age = viewModel.dataUser.age,
                        date = funcData(),
                        time = funcTime(),
                        desired_weight = viewModel.dataUser.desired_weight.toDouble(),
                        height = viewModel.dataUser.height,
                        weight = binding.weightOneMenuDay.text?.toString()?.toDouble().let {
                            val weig = it
                            weig
                        } ?: viewModel.dataUser.weight.toString().toDouble(),
                        //userParam.weight.toDouble(),
                        header = binding.headerMenuDay.text.toString(),
                        fitness_id = it1,
                        menu = binding.menuDay.text.toString(),
                    )
                }
            if (viewModel.positionRecycklerViewEdit != null) {
                viewModel.launchPostMenuDay(oneMenuDay,
                    viewModel.listMenuDay[viewModel.positionRecycklerViewEdit!!].id_note + 1)
            } else {
                viewModel.launchPostMenuDay(oneMenuDay, 0)
            }

            viewModel.positionRecycklerViewEdit = null
            Log.e(taG, "Query ${viewModel.positionRecycklerViewEdit}")
            viewModel.launchGetMenuList(viewModel.startDataAPI.value.toString(),
                viewModel.endDataAPI.value.toString())
            Log.e(taG, "Query2 ${viewModel.positionRecycklerViewEdit}")
            val action: NavDirections =
                NewOneMenuDayFragmentDirections.actionNewOneMenuDayFragmentToMenuDayPart2Fragment(
                    viewModel.userClass, viewModel.dataUser
                )
            findNavController().navigate(action)
        }

        binding.basketGarbage.setOnClickListener {
            if (viewModel.positionRecycklerViewEdit != null) {

                viewModel.dataUser.id?.let { it1 ->
                    viewModel.launchDeleteOneMenuDay(it1,
                        viewModel.listMenuDay[viewModel.positionRecycklerViewEdit!!].id_note)
                }
            }
            viewModel.positionRecycklerViewEdit = null
            val action: NavDirections =
                NewOneMenuDayFragmentDirections.actionNewOneMenuDayFragmentToMenuDayPart2Fragment(
                    viewModel.userClass, viewModel.dataUser
                )
            findNavController().navigate(action)
        }
    }

    private fun funcTime(): String {
        return LocalDateTime.now().toString().split(".")[0].split("T")[1].split(":").slice(0..1)
            .joinToString(
                //   prefix = "[",
                separator = ":",
                // postfix = "]",
                //limit = 3,
                //truncated = "...",
                //transform = { it.uppercase() }
            )
    }

    private fun funcData(): String {
        return LocalDateTime.now().toString().split(".")[0].split("T")[0]
    }
}