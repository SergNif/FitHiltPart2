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
import com.sergnfitness.android.fit.databinding.FragmentPart3Page3MealRegimenBinding
import com.sergnfitness.android.fit.presentation.part3.part3ViewModel.Part3Page3MealRegimenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Part3Page3MealRegimenFragment : Fragment() {

    companion object {
        fun newInstance() = Part3Page3MealRegimenFragment()
    }

    private val taG = "Part3Page3MealRegimenFragment "
    private val viewModel: Part3Page3MealRegimenViewModel by viewModels()
private lateinit var binding: FragmentPart3Page3MealRegimenBinding
    private val args: Part3Page3MealRegimenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part3_page3_meal_regimen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart3Page3MealRegimenBinding.bind(view)
        binding.houseButton.setOnClickListener{
            onClickHouse(view = view)
        }
        val txtHtml = "<h3>Режим приемов пищи для «жаворонков»<h3><h6>(людей, которые просыпаются, к примеру, в 6.00 часов, а ложатся в 22.00)</h6>" +
                "<lu><li>В 7.00 утра завтракайте</li><li>В 10.00 устройте себе второй легкий завтрак</li><li>В 13.00 отправляйтесь на обед</li>" +
                "<li>В 16.00 время для полдника</li><li>В 19.00 ужинайте</li></lu><br/>"
        val txtHtml2 =        "<h3>Пищевой режим для «сов»</h3>" +
                "<h6>(людей, встающих после 9.00 часов и ложащихся спать около 00.00 ночи)</h6>" +
                "<lu><li>В 10.00 утра завтракайте</li><li>В 13.00 время для ланча</li><li>В 15.00 пора обедать</li>В 17.00 отправляйтесь на полдник</li><li>В 20.00 пора ужинать</li></lu><br/>Таким образом, подстраивайте режим приема пищи под свой распорядок дня.<h3>Главные рекомендации</h3><lu><li>Завтракать следует через один час после подъема</li><li>С утра натощак пейте 250 мл теплой простой воды</li><li>Между любыми приемами пищи выдерживайте время в 2-3 часа</li><li>Садитесь ужинать раньше или не позднее двух часов до сна</li><li>Для правильного похудения необходимо вести учет калорий всех съеденных продуктов. Для этого в этом приложение в блокноте ставьте пометки даже о выпитом объеме воды или сока."

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


    }

    private fun onClickHouse(view: View) {
        val action: NavDirections =
            Part3Page3MealRegimenFragmentDirections.actionPart3Page3MealRegimenFragmentToPart2Page1Fragment(
                args.currentUser, args.currentDataUser            )
        findNavController().navigate(action)
    }
}