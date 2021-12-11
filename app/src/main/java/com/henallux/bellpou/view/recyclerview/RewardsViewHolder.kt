package com.henallux.bellpou.view.recyclerview

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.henallux.bellpou.R

class RewardsViewHolder(
    containerView: View
): RecyclerView.ViewHolder(containerView) {

    val brandNameView: TextView by lazy { containerView.findViewById(R.id.box_brand) }
    val rewardDescriptionView: TextView by lazy { containerView.findViewById(R.id.box_description) }
    val costView: TextView by lazy { containerView.findViewById(R.id.box_cost) }

}