package com.sergnfitness.android.fit.presentation.part3

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.FragmentPart2Page1Binding
import com.sergnfitness.android.fit.databinding.FragmentPart3Page4PhysicalActivityBinding
import com.sergnfitness.android.fit.databinding.FragmentPg4PhysicalActiveBinding
import com.sergnfitness.android.fit.presentation.part2.Part2Page1FragmentArgs
import com.sergnfitness.android.fit.presentation.part2.Part2Page1FragmentDirections
import com.sergnfitness.android.fit.presentation.part2.part2viewModel.Part2Page1ViewModel
import com.sergnfitness.android.fit.presentation.part3.part3ViewModel.Part3Page4PhysicalActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Part3Page4PhysicalActivityFragment : Fragment() {

    companion object {
        fun newInstance() = Part3Page4PhysicalActivityFragment()
    }

    private val taG = "Part3Page4PhysicalActivityFragment "
    private val viewModel: Part3Page4PhysicalActivityViewModel by viewModels()
    private lateinit var binding: FragmentPart3Page4PhysicalActivityBinding
    private val args: Part3Page4PhysicalActivityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part3_page4_physical_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart3Page4PhysicalActivityBinding.bind(view)
        val txtHtml =
            "<h4>Физические упражнения помогают сбросить вес без вреда для здоровья.</h4>" +
                    "Быстрый способ похудеть – одновременно заниматься спортом и придерживаться п/п. Физическая нагрузка не только помогает нарастить мышцы и убрать лишний жир, но также улучшить работу сердечно-сосудистой системы:"
        val txtHtml2 =
            "<ul><il>Выпады с гантелями – эффективный способ проработать область таза и бедер.</il><il>Приседания. Это отличные упражнения для похудения в борьбе с лишними сантиметрами не только на бедрах, но и талии, т.к. во время движения будут задействованы все группы мышц.</li><li>Укрепление мышц спины. Мало кто знает, что с помощью специальных упражнений для спины можно похудеть. Они способствуют не только сжиганию жира, но и построению крепкого мышечного каркаса.</li><li>Проработка пресса. Упражнения для мышц брюшного пресса помогут сбросить лишний вес и создать стройный силуэт. Получить желанный рельеф в нижней части живота можно, создав нагрузку на этот участок тела. С такой задачей великолепно справляются популярные упражнения «велосипед» и «планка».</li><li>Отжимание. Одно из базовых упражнений, которое рекомендуется включать в каждую тренировку и выполнять регулярно. Такой вид физической нагрузки полезен людям в любом возрасте. Если сочетать отжимания с бегом или интенсивной ходьбой (скандинавской), можно ускорить метаболизм. Вследствие этого будет происходить более быстрая потеря лишних килограммов.</li></ul>"

        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned
        val spanned2: Spanned = HtmlCompat
            .fromHtml(txtHtml2,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId2.text = spanned2


        if (AppCompatDelegate.getDefaultNightMode() == 2) {
            binding.someId.setTextColor(Color.WHITE)
            binding.someId2.setTextColor(Color.WHITE)

        }
        if (AppCompatDelegate.getDefaultNightMode() == 1) {
            binding.someId.setTextColor(Color.BLACK)
            binding.someId2.setTextColor(Color.BLACK)
        }


        binding.houseButton.setOnClickListener {
            onClickHouse()
        }
    }

    fun onClickHouse() {
        val action: NavDirections =
            Part3Page4PhysicalActivityFragmentDirections.actionPart3Page4PhysicalActivityFragmentToPart2Page1Fragment(
                args.currentUser, args.currentDataUser)
        findNavController().navigate(action)
    }
}