package org.soma.weatherviewer.common.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.soma.weatherviewer.common.util.DATASTORE_HOME_SCREEN_OPTION_KEY
import org.soma.weatherviewer.common.util.DATASTORE_TEMP_TYPE_KEY
import javax.inject.Inject



/*
* TODO 현재 String 값으로 저장된 것들을 Enum Class or Sealed Class로 변경해야할 듯.
*/
class DataStoreUseCase @Inject constructor(
    private val dataStore: DataStore<Preferences>
){

    // Celsius, Fahrenheit
    val tempType: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(DATASTORE_TEMP_TYPE_KEY)] ?: TempType.Celsius.name
        }

    // Current, FiveDays
    val homeScreenOption: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(DATASTORE_HOME_SCREEN_OPTION_KEY)] ?: HomeScreenOptionType.Current.name
        }

    suspend fun editTempType(tempType: TempType) {
        dataStore.edit {
            it[stringPreferencesKey(DATASTORE_TEMP_TYPE_KEY)] = tempType.name
        }
    }

    suspend fun editHomeScreenOption(homeScreenOptionType: HomeScreenOptionType) {
        dataStore.edit {
            it[stringPreferencesKey(DATASTORE_HOME_SCREEN_OPTION_KEY)] = homeScreenOptionType.name
        }
    }

    suspend fun removeTempType() {
        dataStore.edit {
            it.remove(stringPreferencesKey(DATASTORE_TEMP_TYPE_KEY))
        }
    }

    suspend fun removeHomeScreenOption() {
        dataStore.edit {
            it.remove(stringPreferencesKey(DATASTORE_HOME_SCREEN_OPTION_KEY))
        }
    }

    companion object {
        enum class TempType(string: String) {
            Fahrenheit("Fahrenheit"),
            Celsius("Celsius")
        }

        enum class HomeScreenOptionType(string: String) {
            Current("Current"),
            FiveDays("FiveDays")
        }
    }
}