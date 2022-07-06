package com.example.mogawesubmission.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.mogawesubmission.R
import com.example.mogawesubmission.data.network.Res
import com.example.mogawesubmission.data.network.UserApi
import com.example.mogawesubmission.data.reponses.DataResponse
import com.example.mogawesubmission.data.reponses.Object
import com.example.mogawesubmission.data.repository.UserRepository
import com.example.mogawesubmission.databinding.FragmentHomeBinding
import com.example.mogawesubmission.ui.base.BaseFragment
import com.example.mogawesubmission.ui.visible
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel, UserRepository , FragmentHomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)

        viewModel.getuser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Res.Succes -> {
                    updateUI(it.value.`object`)
                }

                is Res.Loading -> {
                    binding.progressbar.visible(false)
                }
            }
        })

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }


    private fun updateUI(data: Object){
        with(binding){
            tvGaweanSelesai.text = data.gawean_selesai
            tvEmail.text = data.email
            tvBallance.text = data.balance
            tvName.text = data.full_name





        }

    }

    override fun getViewModel() = HomeViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container , false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {
            preference.authToken.first()
        }
        val api = dataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}