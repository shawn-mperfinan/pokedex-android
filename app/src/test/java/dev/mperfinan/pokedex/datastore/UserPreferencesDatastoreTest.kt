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
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPreferencesDatastoreTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())
    private lateinit var context: Context
    private lateinit var testFile: File
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var datastoreManager: PreferencesDatastoreManager
    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore

    @BeforeAll
    fun setupAll() {
        context = ApplicationProvider.getApplicationContext()
        testFile = context.preferencesDataStoreFile("user_prefs_test.preferences_pb")
        dataStore = PreferenceDataStoreFactory.create(produceFile = { testFile })

        datastoreManager = PreferencesDatastoreManager(dataStore)
        userPreferencesDatastore = UserPreferencesDatastore(datastoreManager)
    }

    @AfterAll
    fun afterAll() {
        if (testFile.exists()) testFile.delete()
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve (true) as IS_APP_FIRST_LAUNCH initial value when performed`() {
        testScope.runTest {
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isTrue()
            }
        }
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve IS_APP_FIRST_LAUNCH new value when setIsAppFirstLaunch is performed`() {
        testScope.runTest {
            userPreferencesDatastore.setIsAppFirstLaunch(false)
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isFalse()
            }
        }
    }
}
