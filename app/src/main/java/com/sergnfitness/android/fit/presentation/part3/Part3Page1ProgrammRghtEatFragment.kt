package com.sergnfitness.android.fit.presentation.part3

import android.graphics.Color
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
import com.sergnfitness.android.fit.databinding.FragmentPart3Page1ProgrammRghtEatBinding
import com.sergnfitness.android.fit.presentation.part3.part3ViewModel.Part3Page1ProgrammRghtEatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Part3Page1ProgrammRghtEatFragment : Fragment() {

    companion object {
        fun newInstance() = Part3Page1ProgrammRghtEatFragment()
    }

    private val taG = "Part3Page1ProgrammRghtEatFragment "
    private val viewModel: Part3Page1ProgrammRghtEatViewModel by viewModels()
    private lateinit var binding: FragmentPart3Page1ProgrammRghtEatBinding
    private val args: Part3Page1ProgrammRghtEatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part3_page1_programm_rght_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart3Page1ProgrammRghtEatBinding.bind(view)
        binding.houseButton.setOnClickListener{
            onClickHouse(view = view)
        }

        val htmlString : String  = "TextView first line... <br />" +
                "<b>Bold Text</b> | <i>Italic Text</i> and <br/>" +
                "<u>Underlined text</u>"
        val htmlStr:String = "<ul><li>\tСочетайте мясо с овощами и фруктами.</li>" +

                "<li>\t  Если сильно хочется, то немного можно сладкого. Но не превышать допустимую норму сахоросодержащих продуктов в сутки — 5 чайных ложек. А лучше вовсе замените сахар на мед. Все десерты можно употреблять только в первой половине дня, чтобы успеть до вечера сжечь полученные калории.</li>" +
                "\n" +
                "<li>\t  Следите за тем, чтобы в ваш организм поступало достаточное количество белка (человеку его требуется не менее 100−150 г в сутки). Белок является строительным материалом, обновляющим клетки и поддерживающим работоспособность мышц. Если вы отказались от мяса и птицы, следует употреблять растительные белки, которые в большом количестве присутствуют в бобовых, орехах и сое.</li>" +
                "\n" +
                "<li>\t  Обходите стороной полуфабрикаты, фастфуд и соусы, а также консервы. Сахар и соль в большом количестве добавляется даже в кетчуп.<li>"
        // spanned is the interface for text that has
        // markup objects attached to ranges of it
        val spanned : Spanned = HtmlCompat
            // HtmlCompat is the backwards compatible version of Html  калории.
            .fromHtml(
                htmlStr, // source
                // flag that separate block-level elements with blank lines
                // (two newline characters) in between
                HtmlCompat.FROM_HTML_MODE_LEGACY // flags
            )

        val htmlStr1:String = "\tОриентируйтесь на «пищевую пирамиду», согласно которой:" +

                "<ul><li>\t  40% блюд на вашем столе должны содержать сложные углеводы (к ним относятся цельнозерновой хлеб, все виды крупы, кроме манной, а также злаки)</li>" +
                "\n" +
                "<li>\t  35% — это свежие и обработанные паром или запеканием овощи и фрукты</li>" +
                "\n" +
                "<li>\t  20% — это полезные белки (постное мясо, любой вид птицы и рыбы, кисломолочные и молочные продукты)</li>" +
                "\n" +
                "<li>\t  5% могут приходиться на жиры и сахар.<li>"
        // spanned is the interface for text that has
        // markup objects attached to ranges of it
        val spanned1 : Spanned = HtmlCompat
            // HtmlCompat is the backwards compatible version of Html  калории.
            .fromHtml(
                htmlStr1, // source
                // flag that separate block-level elements with blank lines
                // (two newline characters) in between
                HtmlCompat.FROM_HTML_MODE_LEGACY // flags
            )

        // finally, show html formatted text in text view  action_part2Page2Fragment_to_part2Page1Fragment
        binding.textDown.text = spanned
        binding.textUp.text = spanned1

        if (AppCompatDelegate.getDefaultNightMode() == 2) {
            binding.textDown.setTextColor(Color.WHITE)
            binding.textUp.setTextColor(Color.WHITE)

        }
        if (AppCompatDelegate.getDefaultNightMode() == 1) {
            binding.textDown.setTextColor(Color.BLACK)
            binding.textUp.setTextColor(Color.BLACK)

        }

    }

    private fun onClickHouse(view: View) {
            val action: NavDirections =
                Part3Page1ProgrammRghtEatFragmentDirections.actionPart3Page1ProgrammRghtEatFragmentToPart2Page1Fragment(
                    args.currentUser, args.currentDataUser            )
            findNavController().navigate(action)
            }
}