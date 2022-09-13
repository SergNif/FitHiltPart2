package com.sergnfitness.android.fit.presentation.adapter

import android.content.ContentValues
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.sergnfitness.android.fit.R
import com.sergnfitness.data.repository.UserRepositoryCompanionImpl
import com.sergnfitness.data.repository.UserRepositoryImpl

import com.sergnfitness.domain.models.MenuDay
import com.sergnfitness.domain.repository.UserRepository

import javax.inject.Inject


class RecyclerViewAdapter @Inject constructor(
//    val userRepository: UserRepository
) : RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder>() {

    val TAG = "RecyclerViewAdapter Part2 "
    var menuList = mutableListOf<MenuDay>()
    private lateinit var itemListener: onItemClickListenerRecyclViewAdapter

    interface onItemClickListenerRecyclViewAdapter {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListenerRecyclViewAdapter) {
        itemListener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecycleViewHolder {
//        binding = FragmentMenuDayPart2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list, parent, false)
        Log.e(ContentValues.TAG, "onCreateViewHolder")
        return RecycleViewHolder(itemView, itemListener)
    }

    override fun getItemCount(): Int {
        Log.e(ContentValues.TAG, "getItemCount ${menuList.toString()}")
        return menuList.size

    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        Log.e(ContentValues.TAG, "onBindViewHolder")

        holder.bind(menuList[position])

//        holder.string1.text = menuList[position].user.toString()
//        holder.string2.text = menuList[position].data.toString()
//        holder.string3.text = menuList[position].menu.toString()
    }

    class RecycleViewHolder @Inject constructor(
       val itemView: View,
       val listener: onItemClickListenerRecyclViewAdapter,
//       val  userRepository: UserRepository,
    ) :
        RecyclerView.ViewHolder(itemView) {

        val string1: TextView = itemView.findViewById(R.id.textViewRecyclrString1Mmenu)
        val string2: TextView = itemView.findViewById(R.id.textViewRecyclrString2Mmenu)
        val string3: TextView = itemView.findViewById(R.id.textViewRecyclrString3Mmenu)
        val string4: TextView = itemView.findViewById(R.id.weight)
        val string5: TextView = itemView.findViewById(R.id.id_tab)

        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
companion object {
    fun conv(dt: String, i: Long, userRepository: UserRepositoryCompanionImpl = UserRepositoryCompanionImpl()): String? {
        return  userRepository.converStringToData(dt, i)
    }
}
        fun bind(data: MenuDay) {
           val f = object {
               val userRepositoryCom: UserRepositoryCompanionImpl=UserRepositoryCompanionImpl()
                fun conv(dt: String, i: Long): String? {
                    return  userRepositoryCom.converStringToData(dt, i)
                }
            }
            Log.e(ContentValues.TAG, "bind")
            Log.e(ContentValues.TAG, "Get RecycleViewHolder ${data.user.toString()}")
            Log.e(ContentValues.TAG, "Get RecycleViewHolder ${data.data.toString()}")
            Log.e(ContentValues.TAG, "Get RecycleViewHolder ${data.menu.toString()}")
string1.text = f.conv(data.data.toString(),1)
//            string1.text = userRepository.converStringToData(data.data.toString(),1)
            string2.text = data.user.toString()
            string3.text = data.menu.joinToString(separator = "/n")
            string4.text = "Вес ${data.weight.toString()}"
            string5.text = data.id_note.toString()

//            textView1 = view.findViewById(R.id.textViewRecyclrString1Mmenu)
//            textView2 = view.findViewById(R.id.textViewRecyclrString2Mmenu)
//            textView3 = view.findViewById(R.id.textViewRecyclrString3Mmenu)
//            textView4 = view.findViewById(R.id.id_tab)
//            textView5 = view.findViewById(R.id.weight)
//            if (AppCompatDelegate.getDefaultNightMode() == 2) {
                string1.setTextColor(Color.BLACK)
                string2.setTextColor(Color.BLACK)
                string3.setTextColor(Color.BLACK)
                string4.setTextColor(Color.BLACK)
                string5.setTextColor(Color.BLACK)
//            }
//            if (AppCompatDelegate.getDefaultNightMode() == 1) {
//                string1.setTextColor(Color.WHITE)
//                string2.setTextColor(Color.WHITE)
//                string3.setTextColor(Color.WHITE)
//                string4.setTextColor(Color.WHITE)
//                string5.setTextColor(Color.WHITE)
//            }

        }

        //        val nn = fun(userRepository: UserRepository) {}
//        fun conv(text: String, userRepository: UserRepository?): String? {
//            val userRepository: UserRepository by lazy { getName() }
//            val res = userRepository?.converStringToData(text, 1)
//            return res
//        }

    }
}