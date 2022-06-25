import datamodel.DeliveryParams
import datamodel.Order
import datamodel.OrderAndDeliveryParams
import datamodel.PackageQuote
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

object CommandLineHandler {

    fun generateOrderWithDeliveryParams(inputs: List<String>): OrderAndDeliveryParams? {

        try {
            val orderParams = inputs.subList(0, inputs.size - 3)
            val maxCarriableWeight = inputs.last().toInt()
            val maxSpeed = inputs[inputs.size - 2].toInt()
            val numberOfVehicles = inputs[inputs.size - 3].toInt()
            val order = generateOrder(orderParams)

            order?.let { _order ->
                return OrderAndDeliveryParams(_order, DeliveryParams(numberOfVehicles, maxSpeed, maxCarriableWeight))
            }

        }
        catch (nfe: NumberFormatException) {
            return null
        } catch (ie: IndexOutOfBoundsException) {
            return null
        }
        return null
    }

    fun generateOrder(inputs: List<String>): Order? {
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