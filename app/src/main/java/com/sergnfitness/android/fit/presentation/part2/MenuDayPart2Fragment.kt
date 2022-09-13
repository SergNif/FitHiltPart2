package com.sergnfitness.android.fit.presentation.part2

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.FragmentMenuDayPart2Binding
import com.sergnfitness.android.fit.presentation.adapter.RecyclerViewAdapter
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5
import com.sergnfitness.android.fit.presentation.controlUI.ChangeFonButtonPage5NoPress
import com.sergnfitness.android.fit.presentation.part2.part2viewModel.MenuDayPart2ViewModel
import com.sergnfitness.domain.models.MenuDay
import com.sergnfitness.domain.models.UserMenuDay
import com.sergnfitness.domain.models.MenuDayList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDayPart2Fragment : Fragment() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var iview: View
    lateinit var im: ImageButton
    lateinit var plusNextDay: ImageView
    var menuList: List<MenuDay> = mutableListOf<MenuDay>()

    companion object {

        fun newInstance() = MenuDayPart2Fragment()
    }

private lateinit var   recyclerView:RecyclerView
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private val taG = "MenuDayPart2Fragment "
    private val viewModel: MenuDayPart2ViewModel by viewModels()
    private lateinit var binding: FragmentMenuDayPart2Binding
    private val args: MenuDayPart2FragmentArgs by navArgs()

    private val changeFonButtonPage5NoPress = ChangeFonButtonPage5()
    private val changeFonButtonPage5 = ChangeFonButtonPage5NoPress()
    lateinit var oneMenuDay: UserMenuDay


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        return inflater.inflate(R.layout.fragment_menu_day_part2, container, false)
        iview = inflater.inflate(R.layout.fragment_menu_day_part2, container, false)
        im = iview.findViewById(R.id.plus_one_day_menu)
        plusNextDay = iview.findViewById(R.id.plus_next_day_menu_next)
//        initViewModel(iview)
//        generator(20)


        initRecyclerView(iview)

        //searchMenuDay()


        return iview
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuDayPart2Binding.bind(view)
        viewModel.dataUser = args.currentDataUser
        viewModel.userClass = args.currentUser


//        viewModel.dataUserMenuDay = args.currentUserMenuDay
//        viewModel.dataMenuDay = args.currentMenuDay
        binding.textDataRightPart2Page1.text =
            "c ${viewModel.startData.value} по ${viewModel.endData.value}"

        binding.textIdPart2Page1.text = "ID: ${viewModel.dataUser.id.toString()}"
        binding.part2page1ButtonHeight.text = "Рост ${viewModel.dataUser.height.toString()}"
        binding.part2page1ButtonAge.text = "Возраст ${viewModel.dataUser.age.toString()}"
        binding.part2page1ButtonWeight.text = "Вес ${viewModel.dataUser.weight.toString()}"
        viewModel.getMenuListObserverable().observe(viewLifecycleOwner, Observer<MenuDayList> {
            if (it == null) {
                Log.e(taG, "Get Menu List NULL ${it}")
                Toast.makeText(context, "no result found...", Toast.LENGTH_LONG).show()
            } else {
//                Toast.makeText(context,     "${it.listMenuDay[3]} YES result found...",  Toast.LENGTH_LONG).show()
                Log.e(taG, "Get Menu List NOT NULL ${it.listMenuDay}")
                viewModel.funListWeightForChart(it.listMenuDay)
                recyclerViewAdapter.menuList = it.listMenuDay.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()

            }
        })

        Log.e(taG, "Get Menu List")
//        Log.e(taG, "${}  Get Menu List ${}")
        viewModel.formatDataPickerAPI()
        viewModel.launchGetMenuList(viewModel.startDataAPI.value.toString(),
            viewModel.endDataAPI.value.toString())

        im.setOnClickListener {
            val action: NavDirections =
                MenuDayPart2FragmentDirections.actionMenuDayPart2FragmentToNewOneMenuDayFragment(
                    viewModel.userClass, viewModel.dataUser
                )
            Log.e(taG, "To next fragment")
            findNavController().navigate(action)
        }
        viewModel.endDataAPI.observe(viewLifecycleOwner, Observer {
            viewModel.launchGetMenuList(viewModel.startDataAPI.value.toString(),
                viewModel.endDataAPI.value.toString())
        })

        binding.houseButton.setOnClickListener {
            onClickHouse()
        }
        binding.plusNextDayMenuBack.setOnClickListener {
            onClickBackDay()
        }
        binding.plusNextDayMenuNext.setOnClickListener {
            onClickNextDay()
        }
        binding.settingButton.setOnClickListener {
            onClickSetting()
        }
    }

    fun initRecyclerView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recycl)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = RecyclerViewAdapter()//(menuList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.setOnItemClickListener(object :
            RecyclerViewAdapter.onItemClickListenerRecyclViewAdapter {
            override fun onItemClick(position: Int) {
                viewModel.positionRecycklerViewEdit = position
//                findNavController().navigate(R.id.action_menuDayPart2Fragment_to_newOneMenuDayFragment)
                Toast.makeText(activity, "click on ${position}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onClickHouse() {
        val action: NavDirections =
            MenuDayPart2FragmentDirections.actionMenuDayPart2FragmentToPart2Page1Fragment(
                viewModel.userClass, viewModel.dataUser
            )
        findNavController().navigate(action)
    }

    private fun onClickSetting() {
        val action: NavDirections =
            MenuDayPart2FragmentDirections.actionMenuDayPart2FragmentToPart2Fragment1ToUser(
                viewModel.userClass, viewModel.dataUser
            )
        findNavController().navigate(action)
    }

    fun onClickNextFragment() {
//        findNavController().navigate(R.id.action_menuDayPart2Fragment_to_part2Page2Fragment)
    }

    fun onClickNextDay() {
        Log.e(taG, "onClickNextDay ${viewModel.startDataAPI.value}")
        Log.e(taG, "onClickNextDay ${viewModel.endDataAPI.value}")
        viewModel.oneChangeStartEndData(1)
    }

    fun onClickBackDay() {
        viewModel.oneChangeStartEndData(-1)
    }
}