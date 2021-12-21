package com.henallux.bellpou.model

import com.squareup.moshi.Json

data class BoughtReward(
    @field:Json(name="reward_id") val rewardId: Integer
)
