package com.henallux.bellpou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.Reward
import com.henallux.bellpou.repository.RewardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RewardViewModel: ViewModel() {

    fun getRewards(): List<Reward> {

        try {

            return RewardsRepository().getAllRewards()

        } catch (e: Exception) {

            throw e

        }

    }

}