@file:Suppress("NOTHING_TO_INLINE")

import data.api.Cpu
import data.api.Message
import data.api.Storage
import data.api.User
import data.statusReceive
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun main() = coroutineScope<Unit> {
    val httpClient = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                val cpuList = listOf(
                    Cpu("Ryzen 7 3700X", 8, 16, 4.4, 65),
                    Cpu("Ryzen 5 3600", 6, 12, 4.2, 65),
                    Cpu("Ryzen 5 3500", 6, 6, 4.1, 65),
                    Cpu("Core i5 10400F", 6, 12, 4.3, 65),
                    Cpu("Core i7 10700K", 8, 16, 5.1, 125),
                    Cpu("Core i5 10400", 6, 12, 4.3, 65),
                )

                val storageList = listOf(
                    Storage("WESTERN DIGITAL", 6L * 1024 * 1024 * 1024 * 1024, Storage.Type.HDD),
                    Storage("crucial", 500L * 1024 * 1024 * 1024, Storage.Type.SSD),
                    Storage("Samsung", 1L * 1024 * 1024 * 1024 * 1024, Storage.Type.NVM_EXPRESS),
                    Storage("TOSHIBA", 1L * 1024 * 1024 * 1024 * 1024, Storage.Type.SSHD),
                )

                when (request.url.fullPath) {
                    "/user" -> {
                        respondJson(User(1, "MockUser"))
                    }
                    "/cpu" -> {
                        respondJson(cpuList.random())
                    }
                    "/all_cpu" -> {
                        respondJson(cpuList)
                    }
                    "/storage" -> {
                        respondJson(storageList.random())
                    }
                    "/all_storage" -> {
                        respondJson(storageList)
                    }
                    "/unauthorized" -> respondErrorJson(HttpStatusCode.Unauthorized)
                    "/internal_server_error" -> respondErrorJson(HttpStatusCode.InternalServerError)
                    else -> respondErrorJson(HttpStatusCode.NotFound)
                }
            }
        }

        expectSuccess = false
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // 正常受け取り
    httpClient.get<HttpResponse>(path = "/user").statusReceive<User>().p()
    httpClient.get<HttpResponse>(path = "/cpu").statusReceive<Cpu>().p()
    httpClient.get<HttpResponse>(path = "/all_cpu").statusReceive<List<Cpu>>().p()
    httpClient.get<HttpResponse>(path = "/storage").statusReceive<Storage>().p()
    httpClient.get<HttpResponse>(path = "/all_storage").statusReceive<List<Storage>>().p()

    // エラー受け取り
    httpClient.get<HttpResponse>(path = "/unauthorized").statusReceive<User>().p()
    httpClient.get<HttpResponse>(path = "/internal_server_error").statusReceive<Cpu>().p()
    httpClient.get<HttpResponse>(path = "/typo").statusReceive<Storage>().p()

    // エラーメッセージ受け取り
    httpClient.get<HttpResponse>(path = "/unauthorized").statusReceive<Message>().p()
    httpClient.get<HttpResponse>(path = "/internal_server_error").statusReceive<Message>().p()
    httpClient.get<HttpResponse>(path = "/typo").statusReceive<Message>().p()
}

inline fun <T> T.p() = also(::println)
inline fun MockRequestHandleScope.respondErrorJson(status: HttpStatusCode): HttpResponseData = respondErrorJson(status, Message(status.toString()))
inline fun <reified T> MockRequestHandleScope.respondErrorJson(status: HttpStatusCode, data: T): HttpResponseData = respond(Json.encodeToString(data), status, headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()))
inline fun <reified T> MockRequestHandleScope.respondJson(data: T): HttpResponseData = respond(Json.encodeToString(data), headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()))
