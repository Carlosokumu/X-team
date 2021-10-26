package com.example.x_team

data  class ApiModel<out T>(val status: Status,val data: T?,val message: String?) {
    companion object {
        fun <T> success(data: T): ApiModel<T> =ApiModel(status=Status.SUCCESS,data = data,null)
        fun <T> error(data: T?, message: String): ApiModel<T> =
            ApiModel(status =Status.FAILURE, data = data, message = message)

        fun <T> loading(data: T?): ApiModel<T> = ApiModel(status = Status.LOADING, data = data, message = null)
    }
}