package com.henallux.bellpou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.Reward
import com.henallux.bellpou.repository.RewardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RewardViewModel: ViewModel() {

    fun getRewards(): List<Reward> {
        return RewardsRepository().getAllRewards()
    }
}