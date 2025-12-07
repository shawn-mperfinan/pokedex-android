package dev.mperfinan.pokedex.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import dev.mperfinan.pokedex.data.source.datastore.UserPreferencesDatastore
import dev.mperfinan.pokedex.utility.manager.PreferencesDatastoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import tech.apter.junit.jupiter.robolectric.RobolectricExtension
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(RobolectricExtension::class)
class UserPreferencesDatastoreTest {
    private lateinit var context: Context
    private lateinit var testFile: File
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var datastoreManager: PreferencesDatastoreManager
    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore

    @BeforeEach
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        testFile = context.preferencesDataStoreFile("user_prefs_test.preferences_pb")
        dataStore = PreferenceDataStoreFactory.create(produceFile = { testFile })
        datastoreManager = PreferencesDatastoreManager(dataStore)
        userPreferencesDatastore = UserPreferencesDatastore(datastoreManager)
    }

    @AfterEach
    fun tearDown() {
        if (testFile.exists()) testFile.delete()
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve (true) as IS_APP_FIRST_LAUNCH's initial value when performed`() {
        runTest {
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isTrue()
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve IS_APP_FIRST_LAUNCH's new value when setIsAppFirstLaunch is performed`() {
        runTest {
            userPreferencesDatastore.setIsAppFirstLaunch(false)
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
