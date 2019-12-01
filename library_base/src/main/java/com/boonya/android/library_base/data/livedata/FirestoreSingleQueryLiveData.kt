package com.boonya.android.library_base.data.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

/**
 * Created by Boonya Kitpitak on 6/1/18.
 */
class FirestoreSingleQueryLiveData(private val query: Query) : LiveData<QuerySnapshot>() {

    private val LOG_TAG = "FireStoreQueryLiveData"

    override fun onActive() {
        query.get().addOnCompleteListener {
            if (it.isComplete) {
                value = it.result
            } else {
                Log.e(LOG_TAG, "Query Error")
            }
        }
    }
}