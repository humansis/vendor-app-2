package cz.quanti.android.vendor_app.repository.voucher.dto.api

import cz.quanti.android.vendor_app.repository.product.dto.api.SelectedProductApiEntity

data class VoucherPurchaseApiEntity(
    var products: List<SelectedProductApiEntity> = listOf(),
    var vouchers: List<Long> = listOf(),
    var vendorId: Long = 0,
    var createdAt: String = ""
)
