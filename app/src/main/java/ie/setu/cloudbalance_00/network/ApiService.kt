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

data class SaveIamRoleRequest(
    val user_id: Int,
    val role_arn: String,
    val external_id: String? = null
)

data class SaveIamRoleResponse(
    val message: String,
    val iamRole: IamRole
)

data class IamRoleResponse(
    val iamRole: IamRole
)

data class IamRole(
    val id: Int,
    val user_id: Int,
    val role_arn: String,
    val external_id: String?,
    val created_at: String
)

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

    @POST("dev/api/aws/fetch/{user_id}")
    suspend fun fetchAwsForUser(
        @Path("user_id") userId: Int,
        @Body body: Map<String, String>
    ): FetchAwsResponse

    @GET("dev/api/aws/costs/{user_id}")
    suspend fun getAwsCostsByUserId(
        @Path("user_id") userId: Int
    ): GetAwsCostsResponse

    @POST("dev/api/iam-roles")
    suspend fun saveIamRole(
        @Body request: SaveIamRoleRequest
    ): SaveIamRoleResponse

    @GET("dev/api/iam-roles/{user_id}")
    suspend fun getIamRoleByUserId(@Path("user_id") userId: Int): IamRoleResponse

}
