package ie.setu.cloudbalance_00.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
)

data class CreateUserRequest(
    val name: String,
    val email: String
)

// Wrapper to match backend shape
data class GetUsersResponse(
    val users: List<UserResponse>
)

data class CreateUserResponse(
    val message: String,
    val user: UserResponse
)

data class FetchAwsResponse(
    val message: String,
    val lambda_result: LambdaResult
)

data class LambdaResult(
    val user_id: String,
//    val cost: List<CostResponse>
)


interface ApiService {
    @GET("dev/api/users")
    suspend fun getAllUsers(): GetUsersResponse

    @POST("dev/api/users")
    suspend fun createUser(@Body request: CreateUserRequest): CreateUserResponse

    @GET("dev/api/users")
    suspend fun getUserByEmail(@Query("email") email: String): UserResponse

    @GET("dev/api/aws/costs/{user_id}")
    suspend fun getAwsCostsByUserId(
        @Path("user_id") userId: Int,
        @Header("Authorization") token: String
    ): GetAwsCostsResponse

    @POST("dev/api/aws/fetch/{user_id}")
    suspend fun fetchAwsForUser(
        @Path("user_id") userId: Int,
        @Body body: Map<String, String>
    ): FetchAwsResponse

}
