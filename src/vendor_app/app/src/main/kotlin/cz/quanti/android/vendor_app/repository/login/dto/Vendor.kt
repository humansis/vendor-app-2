package cz.quanti.android.vendor_app.repository.login.dto

data class Vendor(
    var id: String = "",
    var username: String = "",
    var password: String = "",
    var saltedPassword: String = "",
    var shop: String = "",
    var adress: String = "",
    var loggedIn: Boolean = false,
    var products: Array<String> = arrayOf(),
    var country: String = "",
    var language: String = ""
)
