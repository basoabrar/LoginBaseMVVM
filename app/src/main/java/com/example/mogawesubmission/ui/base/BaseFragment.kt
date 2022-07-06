package com.example.mogawesubmission.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.mogawesubmission.data.UserPreference
import com.example.mogawesubmission.data.network.DataSource

import com.example.mogawesubmission.data.repository.BaseRepository
import com.example.mogawesubmission.ui.auth.AuthActivity
import com.example.mogawesubmission.ui.starNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel, R : BaseRepository, B : ViewBinding> : Fragment() {

    protected lateinit var preference: UserPreference
    protected lateinit var binding: B
    protected val dataSource = DataSource()
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preference = UserPreference(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())


        lifecycleScope.launch { preference.authToken.first() }
        return binding.root
    }

    fun logout() = lifecycleScope.launch{
        val atuhToken = preference.authToken.first()
        preference.clearToken()
        requireActivity().starNewActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R
}