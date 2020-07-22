package cz.quanti.android.vendor_app.repository.card.impl

import cz.quanti.android.vendor_app.repository.card.CardFacade
import cz.quanti.android.vendor_app.repository.card.CardRepository
import cz.quanti.android.vendor_app.utils.VendorAppException
import cz.quanti.android.vendor_app.utils.isPositiveResponseHttpCode
import io.reactivex.Completable
import io.reactivex.Observable

class CardFacadeImpl(private val cardRepo: CardRepository) : CardFacade {

    override fun syncWithServer(): Completable {
        return actualizeBlockedCardsFromServer()
    }

    private fun actualizeBlockedCardsFromServer(): Completable {
        return cardRepo.getBlockedCardsFromServer().flatMapCompletable { response ->
            val responseCode = response.first
            if (isPositiveResponseHttpCode(responseCode)) {
                val blockedCards = response.second
                cardRepo.deleteAllBlockedCards()
                    .andThen(
                        Observable.fromIterable(blockedCards).flatMapCompletable { blockedCard ->
                            cardRepo.saveBlockedCard(blockedCard)
                        })
            } else {
                throw VendorAppException("Could not load blocked cards").apply {
                    this.apiResponseCode = responseCode
                    this.apiError = true
                }
            }
        }
    }
}
