import datamodel.DiscountCode
import datamodel.Order
import datamodel.PackageCost
import datamodel.PackageQuote
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

class OrderHandler {

    fun processOrder(order: Order): List<PackageCost> {
        return order.packages.map { packageQuote ->
            order.calculatePackageCost(packageQuote)
        }
    }

    fun generateOrder(inputs: List<String>): Order? {
        // base_delivery_cost no_of_packges
        //pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1

        val minimumRequiredArgs = 6
        val argumentsPerItem = 4
        val orderArguments = 2

        if (inputs.size < minimumRequiredArgs) {
            return null
        }
        else {

            try {
                val baseDeliveryCost = inputs[0].toInt()
                val numberOfPackages = inputs[1].toInt()

                val expectedNumberOfArguments = (numberOfPackages * argumentsPerItem) + orderArguments
                var index = orderArguments

                if (expectedNumberOfArguments != inputs.size) {
                    return null
                }

                val packages = mutableListOf<PackageQuote>()

                while (index < inputs.size) {

                    val packageId = inputs[index]
                    val weight = inputs[index + 1].toInt()
                    val distance = inputs[index + 2].toInt()
                    val code = inputs[index + 3]

                    packages.add(PackageQuote(packageId, weight, distance, code))
                    index += argumentsPerItem
                }

                return Order(baseDeliveryCost, packages)

            } catch (nfe: NumberFormatException) {
                return null
            } catch (ie: IndexOutOfBoundsException) {
                return null
            }
        }
    }
}