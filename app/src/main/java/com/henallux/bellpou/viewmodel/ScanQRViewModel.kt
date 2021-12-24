package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.repository.TrashRepository

class ScanQRViewModel: ViewModel() {

    fun reportTrash(qrValue: String) {

        try {

            TrashRepository.reportTrash(qrValue)

        } catch (e: Exception) {

            throw e

        }

    }

}