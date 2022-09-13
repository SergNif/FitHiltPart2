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
import com.sergnfitness.android.fit.databinding.FragmentPart3Page5PrincipalEatBinding
import com.sergnfitness.android.fit.presentation.part2.Part2Page1FragmentArgs
import com.sergnfitness.android.fit.presentation.part2.Part2Page1FragmentDirections
import com.sergnfitness.android.fit.presentation.part2.part2viewModel.Part2Page1ViewModel
import com.sergnfitness.android.fit.presentation.part3.part3ViewModel.Part3Page5PrincipalEatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Part3Page5PrincipalEatFragment : Fragment() {

    companion object {
        fun newInstance() = Part3Page5PrincipalEatFragment()
    }

    private val taG = "Part3Page5PrincipalEatFragment "
    private val viewModel: Part3Page5PrincipalEatViewModel by viewModels()
    private lateinit var binding: FragmentPart3Page5PrincipalEatBinding
    private val args: Part3Page5PrincipalEatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part3_page5_principal_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart3Page5PrincipalEatBinding.bind(view)
        val txtHtml = "<h3>Нужно придерживаться, чтобы начать худеть:</h3>" +
                "<ul><il>Пьем воду. Каждое утро, сразу после пробуждения, необходимо выпивать стакан воды комнатной температуры.</li>"
        val txtHtml2 =
            "<li>Кушаем часто. Питаться нужно согласно принципу дробности. При этом общее количество подходов к столу должно приравниваться к пяти. Соблюдение этого принципа помогает желудку быстрее и качественнее справляться с поступающими в него продуктами.</li>" +
                    "<li>Соблюдаем баланс. Нужно употреблять овощи в таком же количестве, что и продукты – источники ненасыщенных жирных кислот. Таковыми являются семечки, орехи, авокадо, растительные масла.</li>"
        val txtHtml3 =
            "<li>Углеводы – утром, белки – вечером. Продукты, которые богаты углеводами, нужно потреблять в первой половине дня. В вечернее время следует отдавать предпочтение белковым блюдам.</li>" +
                    "<li>Щадящая термическая обработка. Продукты можно отваривать, тушить, запекать и готовить на пару. Жарка под запретом.</li>" +
                    "<li>Два литра воды в день – это обязательный ее лимит.</li>" +
                    "<li>Упор на медленные углеводы. Они дольше перевариваются, поэтому помогают худеть. В меню должны присутствовать крупы, овощи с низким содержанием сахара, макароны из твердых сортов пшеницы. Сочетать эти продукты с животными и растительными жирами не следует.</li></ul>"
        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

        val spanned2: Spanned = HtmlCompat
            .fromHtml(txtHtml2,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        val spanned3: Spanned = HtmlCompat
            .fromHtml(txtHtml3,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned
        binding.someId2.text = spanned2
        binding.someId3.text = spanned3


        if (AppCompatDelegate.getDefaultNightMode() == 2) {
            binding.someId.setTextColor(Color.WHITE)
            binding.someId2.setTextColor(Color.WHITE)
            binding.someId3.setTextColor(Color.WHITE)
        }
        if (AppCompatDelegate.getDefaultNightMode() == 1) {
            binding.someId.setTextColor(Color.BLACK)
            binding.someId2.setTextColor(Color.BLACK)
            binding.someId3.setTextColor(Color.BLACK)
        }
        if (AppCompatDelegate.getDefaultNightMode() == 1) {
            binding.someId.setTextColor(Color.BLACK)
            binding.someId2.setTextColor(Color.BLACK)
            binding.someId3.setTextColor(Color.BLACK)
        }

        binding.houseButton.setOnClickListener {
            onClickHouse()
        }
    }

    fun onClickHouse() {
        val action: NavDirections =
            Part3Page5PrincipalEatFragmentDirections.actionPart3Page5PrincipalEatFragmentToPart2Page1Fragment(
                args.currentUser, args.currentDataUser)
        findNavController().navigate(action)
    }

}