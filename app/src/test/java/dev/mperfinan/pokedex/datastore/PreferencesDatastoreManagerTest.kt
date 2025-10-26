package dev.mperfinan.pokedex.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.utility.manager.PreferencesDatastoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import tech.apter.junit.jupiter.robolectric.RobolectricExtension
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(RobolectricExtension::class)
class PreferencesDatastoreManagerTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())
    private val testKey = booleanPreferencesKey("test_key")

    private lateinit var context: Context
    private lateinit var testFile: File
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var datastoreManager: PreferencesDatastoreManager

    @BeforeEach
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        testFile = context.preferencesDataStoreFile("prefs_test.preferences_pb")
        dataStore = PreferenceDataStoreFactory.create(produceFile = { testFile })
        datastoreManager = PreferencesDatastoreManager(dataStore)
    }

    @AfterEach
    fun tearDown() {
        if (testFile.exists()) testFile.delete()
    }

    @Test
    fun `retrieve should return prefs default value on its initial state`() {
        testScope.runTest {
            datastoreManager.retrieve(testKey, true).test {
                val value = awaitItem()
                assertThat(value).isTrue()
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `retrieve should return newly assigned prefs value`() {
        testScope.runTest {
            datastoreManager.store(testKey, false)

            datastoreManager.retrieve(testKey, true).test {
                val value = awaitItem()
                assertThat(value).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `retrieve should return default value once assigned prefs value is cleared`() {
        testScope.runTest {
            datastoreManager.store(testKey, false)

            datastoreManager.clear(testKey)

            datastoreManager.retrieve(testKey, true).test {
                val value = awaitItem()
                assertThat(value).isTrue()
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
