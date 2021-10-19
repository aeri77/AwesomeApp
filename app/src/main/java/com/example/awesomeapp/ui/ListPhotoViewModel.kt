package com.example.awesomeapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomeapp.api.ApiConfig
import com.example.awesomeapp.model.PhotosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author Aeri on 10/19/2021.
 */
class ListPhotoViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _listPhotoResponse = MutableLiveData<PhotosResponse>()
    private val _errMsg = MutableLiveData<String>()
    private val client = ApiConfig.getApiService()
    val listPhotoResponse : LiveData<PhotosResponse> = _listPhotoResponse
    val isLoading: LiveData<Boolean> = _isLoading
    val errMsg: LiveData<String> = _errMsg

    fun getAllPhotos(){
        client.getListPhoto().enqueue(object : Callback<PhotosResponse>{
            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                if(response.isSuccessful){
                    _listPhotoResponse.value = response.body()
                } else {
                    _errMsg.value = response.message()
                }
            }

            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                _errMsg.value = t.message
            }
        })
    }
}