package org.soma.mobile_logger

object MobileLogger {
    lateinit var type: LoggerType
    lateinit var data: LoggerData

    /**
     * [MobileLogger]를 사용하기 위해 필요한 데이터를 초기화합니다.
     */
    fun init(type: LoggerType, data: LoggerData) {
        MobileLogger.type = type
        MobileLogger.data = data
    }

    /**
     * 사용법은 다음과 같습니다.
     * ```
     * val jsonString = LoggingScheme.makeJsonString("ClickPlay")
     * sendClickLogging(jsonString)
     * ```
     */
    fun sendClickLogging(jsonString: String) {
        // TODO: Retrofit2
    }
    fun sendPresentLogging(jsonString: String) {
        // TODO: Retrofit2
    }
}