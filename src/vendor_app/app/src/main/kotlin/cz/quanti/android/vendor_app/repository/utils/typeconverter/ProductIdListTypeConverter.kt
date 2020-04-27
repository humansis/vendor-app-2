package cz.quanti.android.vendor_app.repository.utils.typeconverter

import androidx.room.TypeConverter
import cz.quanti.android.vendor_app.repository.utils.wrapper.ProductIdListWrapper

class ProductIdListTypeConverter {
    val DELIMITER = "|"

    @TypeConverter
    fun toString(productIdList: ProductIdListWrapper): String {
        return productIdList.products.joinToString(DELIMITER)
    }

    @TypeConverter
    fun toProductIdList(productIdList: String): ProductIdListWrapper {
        var list = productIdList.split(DELIMITER).toTypedArray().toList().map {
            it.toLong()
        }

        return ProductIdListWrapper(
            list
        )
    }
}