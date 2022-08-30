package com.example.acronymapp.model

import androidx.lifecycle.MutableLiveData
import com.example.acronymapp.network.ApiClient
import com.example.acronymapp.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {

    private var apiInterface: ApiInterface?=null
    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllAcronymDetails(sf: String): MutableLiveData<List<Lfs>?> {
        val data = MutableLiveData<List<Lfs>?>()

        apiInterface?.fetchAllPosts(sf)?.enqueue(object : Callback<List<AcronymResultData>> {
            override fun onFailure(call: Call<List<AcronymResultData>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<AcronymResultData>>, response: Response<List<AcronymResultData>>) {
                println("The searched data is:${response.body()}")
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    if (res.isNotEmpty()) {
                        with(data) {
                            this.postValue(res[0].lfs)
                        }
                    } else {
                        data.value = null
                    }
                } else{
                    data.value = null
                }
            }
        })
        return data
    }
}