package cz.quanti.android.vendor_app.repository.voucher.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.quanti.android.vendor_app.repository.VendorDb

@Entity(tableName = VendorDb.TABLE_VOUCHER)
data class VoucherDbEntity(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long = 0,
    var id: Long = 0,
    var purchaseId: Long = 0
)
