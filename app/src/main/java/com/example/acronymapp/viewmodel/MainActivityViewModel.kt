package com.example.acronymapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.acronymapp.model.Lfs
import com.example.acronymapp.model.MainRepository

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    fun fetchAllPosts(tags:String): MutableLiveData<List<Lfs>?> {
        return MainRepository.fetchAllAcronymDetails(tags)
    }

}