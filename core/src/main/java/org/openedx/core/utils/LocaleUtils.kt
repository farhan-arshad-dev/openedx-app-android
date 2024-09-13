package org.openedx.core.utils

import org.openedx.core.AppDataConstants.USER_MAX_YEAR
import org.openedx.core.AppDataConstants.defaultLocale
import org.openedx.core.domain.model.RegistrationField
import java.util.*

object LocaleUtils {

    // Due to legal reasons we need to disable some countries
    private val disabledCountries: List<String> = listOf("RU")

    fun getBirthYearsRange(): List<RegistrationField.Option> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return (currentYear - USER_MAX_YEAR..currentYear - 0).reversed().map {
            RegistrationField.Option(it.toString(), it.toString(), "")
        }.toList()
    }

    fun isProfileLimited(inputYear: String?): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return if (!inputYear.isNullOrEmpty()) {
            currentYear - inputYear.toInt() < 13
        } else {
            true
        }
    }

    fun getCountries() = getAvailableCountries()

    fun getLanguages() = getAvailableLanguages()

    fun getLanguages(languages: List<String>) = getAvailableLanguages().filter {
        languages.contains(it.value)
    }

    fun getCountryByCountryCode(code: String): String? {
        val countryISO = Locale.getISOCountries().firstOrNull { it == code }
        return countryISO?.let {
            Locale("", it).getDisplayCountry(defaultLocale)
        }
    }

    fun getLanguageByLanguageCode(code: String): String? {
        val countryISO = Locale.getISOLanguages().firstOrNull { it == code }
        return countryISO?.let {
            Locale(it, "").getDisplayLanguage(defaultLocale)
        }
    }

    private fun getAvailableCountries() = Locale.getISOCountries()
        .asSequence()
        .minus(disabledCountries)
        .map {
            RegistrationField.Option(it, Locale("", it).getDisplayCountry(defaultLocale), "")
        }
        .sortedBy { it.name }
        .toList()


    private fun getAvailableLanguages() = Locale.getISOLanguages()
        .asSequence()
        .filter { it.length == 2 }
        .map {
            RegistrationField.Option(it, Locale(it, "").getDisplayLanguage(defaultLocale), "")
        }
        .sortedBy { it.name }
        .toList()

    fun getDisplayLanguage(languageCode: String): String {
        return Locale(languageCode, "").getDisplayLanguage(defaultLocale)
    }

}
