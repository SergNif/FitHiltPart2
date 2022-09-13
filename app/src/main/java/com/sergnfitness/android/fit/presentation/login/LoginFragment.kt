package com.sergnfitness.android.fit.presentation.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sergnfitness.android.fit.R
import com.sergnfitness.android.fit.databinding.FragmentLoginBinding
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var emailQuery: String
    lateinit var passwQuery: String
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginFragmentViewModel by viewModels<LoginFragmentViewModel>()
    private val args: LoginFragmentArgs by navArgs<LoginFragmentArgs>()

    val taG = "Fragment Login"
    lateinit var emailEditText: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        emailEditText = binding.Email
        emailEditText.setPressed(true)
        emailEditText.isPressed = true
        emailEditText.setClickable(true)
        emailEditText.isClickable = true
//        view.setOnClickListener {
//            Toast.makeText(context, "Welcome page 1", Toast.LENGTH_SHORT).show()
//        }


        val passwordEditText = binding.password
        val loginButton = binding.loginBtn
        val loadingProgressBar = binding.loading


        binding.noAccountText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2)
        }

        binding.loginBtn.setOnClickListener {
            when {  //если поле email или password пустое, то ничего не делать
                TextUtils.isEmpty(binding.Email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        activity,
                        "Пожалуйста введите Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        activity,
                        "Пожалуйста введите Пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> { // если поле email или password не пустое, то запрашивается на сервере эти поля
                    emailQuery = binding.Email.text.toString().trim() { it <= ' ' }
                    passwQuery = binding.password.text.toString().trim() { it <= ' ' }
                    viewModel.queryOfEmaiPassword(email = emailQuery, password = passwQuery)
                }
            }
        }

        viewModel.userResourceLiveData.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                is Resource.Success -> { // пришел хороший ответ
                    Log.e(taG, " Resource.Success  ${responce.data.toString()}")

                    responce.data?.let {
                        if (it is User) { // сравниваются оля email и password в активити с базой на сервере
                            if (it.email.toString() == emailQuery && it.password.toString() == passwQuery) {
//                                viewModel.createDataUserOnServer(it.email.toString())
                                it.id?.let { it1 -> viewModel.queryOfEmaiPasswordDataUser(it1) }
                                viewModel.saveUserToSharedPref(it)
                                viewModel.user = it
                                // при совпадении почты и пароля -> переход на следующий фрагмент
                            }
                            Log.e(taG, "response is User")
                        }
                    }
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Error -> {  // при запросе на сервер пришла ошибка
                    Log.e(taG, " Resource.Error  ${responce.message.toString()}")

                    Toast.makeText(requireContext(), responce.message, Toast.LENGTH_LONG).show()
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    Log.e(taG, " Resource.Loading  $responce")
                    binding.loading.visibility = View.VISIBLE
                }
            }
        }


        viewModel.dataUserResourceLiveData.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                is Resource.Success -> { // пришел хороший ответ
                    Log.e(taG, " Resource.Success  ${responce.data.toString()}")

                    responce.data?.let {

                        viewModel.dataUser = it as DataUser
                        val action: NavDirections =
                            LoginFragmentDirections.actionLoginFragment2ToPg1MaleFemale1(
                                viewModel.user,
                                viewModel.dataUser)
                        findNavController().navigate(action)//(R.id.action_loginFragment2_to_pg1MaleFemale1)
                        // при совпадении почты и пароля -> переход на следующий фрагмент
                    }
                    Log.e(taG, "response is User")
  
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Error -> {  // при запросе на сервер пришла ошибка
                    Log.e(taG, " Resource.Error  ${responce.message.toString()}")

                    Toast.makeText(requireContext(), responce.message, Toast.LENGTH_LONG).show()
                    binding.loading.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    Log.e(taG, " Resource.Loading  $responce")
                    binding.loading.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(taG, "destroy login fragment")
    }
}


