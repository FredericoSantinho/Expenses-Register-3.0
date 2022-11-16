package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.PlaceProduct

interface SaveProduct {
  fun saveProduct(placeProduct: PlaceProduct): Completable
}