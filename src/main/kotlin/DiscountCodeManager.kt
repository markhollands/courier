import datamodel.DiscountCode
import datamodel.discounts.Offer001
import datamodel.discounts.Offer002
import datamodel.discounts.Offer003

object DiscountCodeManager {
    private val discountCodes: List<DiscountCode> = listOf(Offer001(), Offer002(), Offer003())
    fun getDiscountCode(discountText: String) = discountCodes.firstOrNull { code -> code.name == discountText }
}