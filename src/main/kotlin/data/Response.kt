package data

import io.ktor.client.call.receive
import io.ktor.client.statement.HttpResponse

data class Response<T>(val status: Int, val data: T)

suspend inline fun <reified T> HttpResponse.statusReceive(): Response<T> = Response(status.value, receive())
