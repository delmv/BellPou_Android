package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.Trash
import com.henallux.bellpou.repository.TrashRepository
import java.lang.Exception

class MapsViewModel : ViewModel() {
    fun getTrashesAndLocations(): List<Trash>? {
        try {
            return TrashRepository().getTrashesAndLocation()
        } catch (e: Exception) {
            throw(e)
        }
    }
}