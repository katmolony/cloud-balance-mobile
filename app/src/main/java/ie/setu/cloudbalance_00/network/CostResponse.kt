package ie.setu.cloudbalance_00.network

data class AwsCost(
    val id: Int,
    val user_id: Int,
    val service_name: String,
    val cost: String,
    val currency: String,
    val period_start: String,
    val period_end: String,
    val last_updated: String
)

data class GetAwsCostsResponse(
    val message: String,
    val costs: List<AwsCost>
)
