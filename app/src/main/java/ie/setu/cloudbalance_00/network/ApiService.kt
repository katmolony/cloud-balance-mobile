package ie.setu.cloudbalance_00.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
)

//data class AwsCostResponse(
//    val id: Int,
//    val user_id: Int,
//    val service_name: String,
//    val cost: String,
//    val currency: String,
//    val period_start: String,
//    val period_end: String,
//    val last_updated: String
//)
//
//data class GetAwsCostsResponse(
//    val message: String,
//    val costs: List<AwsCostResponse>
//)


// Wrapper to match backend shape
data class GetUsersResponse(
    val users: List<UserResponse>
)

interface ApiService {
    @GET("dev/api/users")
    suspend fun getAllUsers(): GetUsersResponse

    @GET("dev/api/aws/costs/{user_id}")
    suspend fun getAwsCostsByUserId(
        @Path("user_id") userId: Int,
        @Header("Authorization") token: String
    ): GetAwsCostsResponse
}
