package ie.setu.cloudbalance_00.network

data class AwsResource(
    val id: Int,
    val user_id: Int,
    val service_name: String,
    val resource_id: String,
    val region: String,
    val last_updated: String
)

data class GetAwsResourcesResponse(
    val message: String,
    val resources: List<AwsResource>
)
