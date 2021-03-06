package cz.quanti.android.vendor_app.main.vendor.viewmodel

import androidx.lifecycle.ViewModel
import cz.quanti.android.vendor_app.repository.AppPreferences
import cz.quanti.android.vendor_app.repository.product.ProductFacade
import cz.quanti.android.vendor_app.repository.product.dto.Product
import cz.quanti.android.vendor_app.repository.purchase.dto.SelectedProduct
import cz.quanti.android.vendor_app.repository.synchronization.SynchronizationFacade
import cz.quanti.android.vendor_app.utils.CurrentVendor
import cz.quanti.android.vendor_app.utils.ShoppingHolder
import cz.quanti.android.vendor_app.utils.getDefaultCurrency
import io.reactivex.Completable
import io.reactivex.Single

class VendorViewModel(
    private val shoppingHolder: ShoppingHolder,
    private val productFacade: ProductFacade,
    private val syncFacade: SynchronizationFacade,
    private val preferences: AppPreferences,
    private val currentVendor: CurrentVendor
) : ViewModel() {

    fun getLastCurrencySelection(): String {
        if (shoppingHolder.lastCurrencySelection == "") {
            shoppingHolder.lastCurrencySelection = getDefaultCurrency(currentVendor.vendor.country)
        }
        return shoppingHolder.lastCurrencySelection
    }

    fun setLastCurrencySelection(selected: String) {
        shoppingHolder.lastCurrencySelection = selected
    }

    fun getProducts(): Single<List<Product>> {
        return productFacade.getProducts()
    }

    fun synchronizeWithServer(): Completable {
        return syncFacade.synchronize()
    }

    fun getFirstCurrencies(): List<String> {
        return listOf("USD", "EUR", "SYP", "KHR", "UAH", "AMD", "MNT", "ETB")
    }

    fun removeFromCart(position: Int) {
        shoppingHolder.cart.removeAt(position)
    }

    fun clearCart() {
        shoppingHolder.cart.clear()
    }

    fun addToShoppingCart(product: SelectedProduct) {
        shoppingHolder.cart.add(product)
    }

    fun getShoppingCart(): List<SelectedProduct> {
        return shoppingHolder.cart
    }

    fun getCurrency(): String {
        return shoppingHolder.chosenCurrency
    }

    fun setCurrency(currency: String) {
        shoppingHolder.chosenCurrency = currency
    }
}
