package com.example.mogawesubmission.ui.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mogawesubmission.databinding.FragmentLoginBinding
import com.example.mogawesubmission.data.network.AuthApi
import com.example.mogawesubmission.data.network.LoginRequest
import com.example.mogawesubmission.data.network.Res
import com.example.mogawesubmission.data.reponses.LoginResponse
import com.example.mogawesubmission.data.repository.AuthRepository
import com.example.mogawesubmission.ui.base.BaseFragment
import com.example.mogawesubmission.ui.enable
import com.example.mogawesubmission.ui.handleApiError
import com.example.mogawesubmission.ui.home.HomeActivity
import com.example.mogawesubmission.ui.starNewActivity
import com.example.mogawesubmission.ui.visible
import kotlinx.coroutines.launch
import java.security.MessageDigest


class LoginFragment : BaseFragment<AuthViewModel, AuthRepository, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Res.Loading)
            when (it) {
                is Res.Succes -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.token)
                        viewModel.saveReturnValue(it.value.returnValue)

                        if (viewModel.returnValue == "000"){
                            requireActivity().starNewActivity(HomeActivity::class.java)
                        }
                        else if(viewModel.returnValue == "004"){
                            Toast.makeText(requireContext(), "Password Salah", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Periksa Data Login anda", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                is Res.Fail -> handleApiError(it)
            }
        })

        binding.edtPassword.addTextChangedListener {
            val email = binding.edtEmail.text.toString().trim()
            binding.btnLogin.enable((email.isNotEmpty() && it.toString().isNotEmpty()))
        }

        binding.btnLogin.setOnClickListener {
            val request = LoginRequest()
            request.email = binding.edtEmail.text.toString().trim()
            request.password = binding.edtPassword.text.toString().trim()

            if (binding.edtEmail.text.toString().isNotEmpty() &&
                binding.edtPassword.text.toString().isNotEmpty()
            ) {
                val hashing = request.password!!
                val hashedPass = sha256(hashing)
                request.password = hashedPass
                if (hashedPass == hashedPass){
                    request.password = hashedPass
                }
                binding.progressbar.visible(true)
                viewModel.login(request)
                Log.d(TAG, "onViewCreated: ${request.password} ")
            } else {
                Toast.makeText(requireContext(), "Lengkapi", Toast.LENGTH_SHORT).show()
            }


        }
    }


    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(dataSource.buildApi(AuthApi::class.java), preference)


    private fun sha256(input: String) = hashString("SHA-256", input)
    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789abcdef"
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }
}