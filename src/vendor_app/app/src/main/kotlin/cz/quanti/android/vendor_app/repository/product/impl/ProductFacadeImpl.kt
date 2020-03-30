package cz.quanti.android.vendor_app.repository.product.impl

import com.squareup.picasso.Picasso
import cz.quanti.android.vendor_app.repository.product.ProductFacade
import cz.quanti.android.vendor_app.repository.product.ProductRepository
import cz.quanti.android.vendor_app.repository.product.dto.Product
import cz.quanti.android.vendor_app.utils.VendorAppException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ProductFacadeImpl(
    private val productRepo: ProductRepository,
    private val picasso: Picasso
) : ProductFacade {

    override fun reloadProductFromServer(): Completable {
        return productRepo.getProductsFromServer().flatMapCompletable { response ->
            val responseCode = response.first
            val products = response.second
            if (responseCode == 200) {
                actualizeDatabase(products)
            } else {
                Completable.error(
                    VendorAppException(
                        "Could not get products from server."
                    ).apply {
                        apiError = true
                        apiResponseCode = responseCode
                    })
            }
        }
    }

    override fun getProducts(): Single<List<Product>> {
        return productRepo.getProducts()
    }

    private fun actualizeDatabase(products: List<Product>?): Completable {
        return if (products == null) {
            throw VendorAppException("Products returned from server were empty.")
        } else {
            productRepo.deleteProducts().andThen(
                Observable.fromIterable(products).flatMapCompletable { product ->
                    productRepo.saveProduct(product)
                        .andThen(Completable.fromCallable { picasso.load(product.image) })
                })
        }
    }
}
