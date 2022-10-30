package org.soma.weatherviewer.home.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.soma.weatherviewer.common.domain.model.WeatherDescription
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common_ui.Month
import org.soma.weatherviewer.home.R
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailInfoBinding
import java.util.*

class WeatherDetailInfoFragment : Fragment() {

    private var _binding: FragmentWeatherDetailInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var weatherModel: WeatherModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherModel = WeatherModel(
            arguments?.getLong(ID_KEY) ?: 0L,
            WeatherDescription.NULL,
            arguments?.getString(YEAR_KEY) ?: "",
            arguments?.getString(MONTH_KEY) ?: "",
            arguments?.getString(DAY_KEY) ?: "",
            arguments?.getString(DESCRIPTION_KEY) ?: "",
            arguments?.getString(ICON_KEY) ?: "",
            arguments?.getFloat(TEMP_KEY) ?: 0f,
            arguments?.getFloat(TEMP_MAX_KEY) ?: 0f,
            arguments?.getFloat(TEMP_MIN_KEY) ?: 0f,
            arguments?.getInt(HUMIDITY_KEY) ?: 0
        )
        _binding = FragmentWeatherDetailInfoBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.weather = weatherModel
        }

        localization()


        return binding.root
    }

    private fun localization() {
        val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val primaryLocale: Locale = requireContext().resources.configuration.locales.get(0)
            primaryLocale.language
        } else {
            Locale.getDefault().language
        }
        Log.d("Detail", "localization: language: $language")
        if (language == "ko") {
            binding.textDate.text = getString(R.string.date_format_y_m_d, weatherModel.year, weatherModel.month, weatherModel.day)
        } else {
            binding.textDate.text = getString(R.string.date_format_y_m_d, Month.values()[weatherModel.month.toInt()-1], weatherModel.day, weatherModel.year)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ID_KEY = "ID_KEY"
        private const val MAIN_RES_ID_KEY = "MAIN_RES_ID_KEY"
        private const val YEAR_KEY = "YEAR_KEY"
        private const val MONTH_KEY = "MONTH_KEY"
        private const val DAY_KEY = "DAY_KEY"
        private const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        private const val ICON_KEY = "ICON_KEY"
        private const val TEMP_KEY = "TEMP_KEY"
        private const val TEMP_MAX_KEY = "TEMP_MAX_KEY"
        private const val TEMP_MIN_KEY = "TEMP_MIN_KEY"
        private const val HUMIDITY_KEY = "HUMIDITY_KEY"

        fun newInstance(weatherModel: WeatherModel) : Fragment {
            Log.d("weatherData", "newInstance model: $weatherModel")
            val fragment = WeatherDetailInfoFragment()
            val bundle = Bundle()
            bundle.putLong(ID_KEY, weatherModel.id)
            bundle.putInt(MAIN_RES_ID_KEY, weatherModel.main.resId ?: 0)
            bundle.putString(YEAR_KEY, weatherModel.year)
            bundle.putString(MONTH_KEY, weatherModel.month)
            bundle.putString(DAY_KEY, weatherModel.day)
            bundle.putString(DESCRIPTION_KEY, weatherModel.description)
            bundle.putString(ICON_KEY, weatherModel.iconUrl)
            bundle.putFloat(TEMP_KEY, weatherModel.temp)
            bundle.putFloat(TEMP_MAX_KEY, weatherModel.tempMax)
            bundle.putFloat(TEMP_MIN_KEY, weatherModel.tempMin)
            bundle.putInt(HUMIDITY_KEY, weatherModel.humidity)

            fragment.arguments = bundle
            return fragment
        }
    }
}