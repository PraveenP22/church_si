package com.example.church_si.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataManager(private val context: Context) {

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val PREFERENCE_NAME = "MyDataStore"
        val Name = "NAME"
        val Token = "TOKEN"
        val Mobile = "MOBILE"
        //Instance of DataStore
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    }

    /**
     * Add string data to data Store
     */
    suspend fun writeString(key: String, value: String) {
        context.dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
    }


    /**
     * Read string from the data store preferences
     */
    fun readString(key: String): Flow<String> {
        return context.dataStore.data.map{ pref ->
            pref[stringPreferencesKey(key)] ?: ""
        }
    }

}