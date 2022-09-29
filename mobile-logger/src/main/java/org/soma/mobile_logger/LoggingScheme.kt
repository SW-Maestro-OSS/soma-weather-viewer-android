package org.soma.mobile_logger

interface LoggingScheme {
    fun makeJsonString(event: String): String
}