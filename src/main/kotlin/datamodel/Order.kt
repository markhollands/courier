package datamodel

data class Order(val baseDeliveryCost: Int, val packages: List<PackageQuote>)