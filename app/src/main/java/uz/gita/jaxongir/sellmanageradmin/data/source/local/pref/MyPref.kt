package uz.gita.jaxongir.sellmanageradmin.data.source.local.pref

import android.content.SharedPreferences
import uz.gita.jaxongir.sellmanageradmin.utills.Constants.IS_LOGIN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPref @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun setLogin(bool:Boolean) = sharedPreferences.edit().putBoolean(IS_LOGIN, bool).apply()

    fun isLogin():Boolean = sharedPreferences.getBoolean(IS_LOGIN, false) ?: false

}