package com.example.petsapp.data.repo

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.security.KeyStore

private const val PREFS = "prefs"
private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
private const val KEY_TOKEN = "Token"


class PreferencesImpl(context: Context) : IPreferences {

    private val settings = try {
        createSharedPreferences(context)
    } catch (e: Exception) {
        deleteSharedPreferences(context)
        createSharedPreferences(context)
    }

    private fun createSharedPreferences(context: Context) = EncryptedSharedPreferences.create(
        context,
        PREFS,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private fun deleteSharedPreferences(context: Context) {
        try {
            val sharedPrefsFile = File("${context.filesDir.parent}/shared_prefs/$PREFS.xml")
            if (sharedPrefsFile.exists()) {
                sharedPrefsFile.delete()
            }
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
            keyStore.load(null)
            keyStore.deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        } catch (e: Exception) {
            Log.d(
                "AAA",
                "EncryptedSharedPref:  Error occurred while trying to reset shared pref=$e"
            )
        }
    }

    override suspend fun setToken(id: String?) {
        withContext(Dispatchers.IO) {
            settings.edit { putString(KEY_TOKEN, id) }
        }
    }

    override suspend fun getToken(): String? {
        return withContext(Dispatchers.IO) {
            settings.getString(KEY_TOKEN, null)
        }
    }
}