package ie.setu.cloudbalance_00.network

import retrofit2.http.GET
import retrofit2.http.Header

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String
)

// Wrapper to match backend shape
data class GetUsersResponse(
    val users: List<UserResponse>
)

interface ApiService {
    @GET("dev/api/users")
    suspend fun getAllUsers(): GetUsersResponse
}