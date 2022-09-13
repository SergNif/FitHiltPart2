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
import com.sergnfitness.android.fit.databinding.FragmentPart3Page6MistakeBinding
import com.sergnfitness.android.fit.presentation.part3.part3ViewModel.Part3Page6MistakeViewModel

class Part3Page6MistakeFragment : Fragment() {

    companion object {
        fun newInstance() = Part3Page6MistakeFragment()
    }

    private val taG = "Part3Page6MistakeFragment "
    private val viewModel: Part3Page6MistakeViewModel by viewModels()
    private lateinit var binding: FragmentPart3Page6MistakeBinding
    private val args: Part3Page6MistakeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part3_page6_mistake, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart3Page6MistakeBinding.bind(view)
        val txtHtml = "<ul><li>Срывы на сладкое и мучное (их не стоит исключать совсем, но дозируйте приемы, чтобы не нарушить норму дневного потребления калорий).</li>" +
                "<li>Жареное и копченое. Такая термическая обработка пищи возможна, если жарить без масла, на открытом огне, а коптить не более 20 минут натуральным способом (не искусственным дымом).</li>"
        val txtHtml2 = "<il>Вареной и печеной еде предпочтите сырые овощи и фрукты, потребляйте максимум зелени всех разновидностей.</li>" +
                "<li>Тяжелый ужин с большими порциями. Мясо или рыбу отварите или потушите, обязательно добавьте свежий овощ (например, 200 г припущенной говядины с одним свежим огурцом).</li>" +
                "<li>Частое употребление алкоголя. Следует избегать, так как он довольно калориен и может спровоцировать сильное чувство голода.</li>" +
                "<li>Во время еды воду пить нельзя. Равно как и чай или сок. Заварите стаканчик чая лишь за час до еды и спустя полчаса после.</li>" +
                "<li>Аккуратнее с солью, приправами и соусами. Все это сильно стимулирует аппетит и может привести к нарушению режима и перееданию.</li>" +
                "<li>Приемы пищи не стоит пропускать. Пусть у вас с собой всегда будет упаковка орешков, вода с лимоном или горсть изюма. Так вы усмирите аппетит и избежите переедания во время отложенного приема пищи.</li></ul>"

        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        val spanned2: Spanned = HtmlCompat
            .fromHtml(txtHtml2,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned
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
            Part3Page6MistakeFragmentDirections.actionPart3Page6MistakeFragmentToPart2Page1Fragment(
                args.currentUser, args.currentDataUser)
        findNavController().navigate(action)
    }

}