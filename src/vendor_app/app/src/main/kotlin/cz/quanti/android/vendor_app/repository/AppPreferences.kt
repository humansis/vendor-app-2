package cz.quanti.android.vendor_app.repository

import android.content.Context
import cz.quanti.android.vendor_app.repository.login.dto.Vendor
import org.koin.core.KoinComponent
import wtf.qase.appskeleton.core.BasePreferences
import wtf.qase.appskeleton.core.BasePreferencesMigration
import java.util.*

class AppPreferences(context: Context) : BasePreferences(context, VERSION, MIGRATIONS), KoinComponent {

    companion object {
        const val VERSION = 1

        val MIGRATIONS = TreeMap<Int, BasePreferencesMigration>()

        private const val VENDOR_ID = "pin_vendor_app_vendor_id"
        private const val VENDOR_USERNAME = "pin_vendor_app_vendor_username"
        private const val VENDOR_SALTED_PASSWORD = "pin_vendor_app_vendor_salted_password"
        private const val VENDOR_SHOP = "pin_vendor_app_vendor_shop"
        private const val VENDOR_ADDRESS = "pin_vendor_app_vendor_address"
        private const val VENDOR_COUNTRY = "pin_vendor_app_vendor_country"
        private const val VENDOR_LANGUAGE = "pin_vendor_app_vendor_language"
        private const val VENDOR_LOGGED_IN = "pin_vendor_app_vendor_logged_in"

        private const val LAST_SYNCED = "pin_vendor_app_last_synced"
    }

    override fun init() {
    }

    var lastSynced: Long
        get() {
            return settings.getLong(LAST_SYNCED, 0)
        }
        set(lastSynced) {
            settings.edit().putLong(LAST_SYNCED, lastSynced)
        }

    var vendor: Vendor
        get() {
            return Vendor().apply {
                this.id = settings.getString(VENDOR_ID, "")
                this.username = settings.getString(VENDOR_USERNAME, "")
                this.saltedPassword = settings.getString(VENDOR_SALTED_PASSWORD, "")
                this.shop = settings.getString(VENDOR_SHOP, "")
                this.address = settings.getString(VENDOR_ADDRESS, "")
                this.country = settings.getString(VENDOR_COUNTRY, "")
                this.language = settings.getString(VENDOR_LANGUAGE, "")
                this.loggedIn = settings.getBoolean(VENDOR_LOGGED_IN, false)
            }
        }
        set(vendor) {
            settings.edit().putString(VENDOR_ID, vendor.id).apply()
            settings.edit().putString(VENDOR_USERNAME, vendor.username).apply()
            settings.edit().putString(VENDOR_SALTED_PASSWORD, vendor.saltedPassword).apply()
            settings.edit().putString(VENDOR_SHOP, vendor.shop).apply()
            settings.edit().putString(VENDOR_ADDRESS, vendor.address).apply()
            settings.edit().putString(VENDOR_COUNTRY, vendor.country).apply()
            settings.edit().putString(VENDOR_LANGUAGE, vendor.language).apply()
            settings.edit().putBoolean(VENDOR_LOGGED_IN, vendor.loggedIn).apply()
        }
}
