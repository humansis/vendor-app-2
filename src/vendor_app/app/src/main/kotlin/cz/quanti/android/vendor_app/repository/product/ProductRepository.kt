package cz.quanti.android.vendor_app.repository.product

import cz.quanti.android.vendor_app.repository.product.dto.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun getProductsFromServer(): Single<Pair<Int, List<Product>>>

    fun getProducts(): Single<List<Product>>

    fun deleteProducts(): Completable

    fun saveProduct(product: Product): Completable
}
