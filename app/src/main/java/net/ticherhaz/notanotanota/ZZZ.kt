package net.ticherhaz.notanotanota

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object ZZZ {

    private var instance: ZZZ? = null
    private var mSharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getInstance(): ZZZ? {
        if (instance == null) {
            synchronized(ZZZ::class.java) {
                if (instance == null) {
                    instance = ZZZ
                }
            }
        }
        return instance
    }

    fun <T> saveObjectsList(key: String?, objectList: MutableList<T>?) {
        val objectString: String = Gson().toJson(objectList)
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    fun getObjectsList(key: String): MutableList<YYY>? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            return Gson().fromJson(objectString, Array<YYY>::class.java).toMutableList()
        }
        return null
    }

    fun <T> getObjectsListCustom(key: String): MutableList<T>? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)

            val finalList: MutableList<T>
            val gson = Gson()


            val type: Type = object : TypeToken<List<T?>?>() {}.type

            finalList = gson.fromJson(objectString, type)

            return finalList
        }
        return null
    }

    fun isKeyExists(key: String): Boolean {
        val map = mSharedPreferences!!.all
        return map.containsKey(key)
    }
}