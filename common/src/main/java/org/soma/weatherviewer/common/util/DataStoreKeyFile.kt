package org.soma.weatherviewer.common.util

/**
 * 사용자의 민감한 정보를 저장할 경우 gitignore를 통해 key값이 History에 노출되지 않도록 해야 합니다.
 *
 * 여기서 사용하는 temp_type_key / home_screen_option_key 는 단순한 데이터 값을 저장하기에 미노출 처리하지 않았습니다.
 */
const val DATASTORE_TEMP_TYPE_KEY = "temp_type_key"
const val DATASTORE_HOME_SCREEN_OPTION_KEY = "home_screen_option_key"