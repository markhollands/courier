import datamodel.DiscountCode
import datamodel.discounts.Offer001

object DiscountCodeManager {

    private val discountCodes: List<DiscountCode> = listOf(Offer001())

    fun getDiscountCode(discountText: String) =
        discountCodes.firstOrNull { code -> code.name == discountText }
}