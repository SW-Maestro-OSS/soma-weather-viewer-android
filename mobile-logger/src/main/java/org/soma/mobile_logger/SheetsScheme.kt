package org.soma.mobile_logger

class SheetsScheme: LoggingScheme {
    override fun makeJsonString(event: String): String {
        return "" // TODO: event Sheet + 최하단을 range로, 현재 시간, device_id 등 필요한 데이터를 추가
    }
}