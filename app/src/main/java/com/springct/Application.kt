package com.springct

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.google.gson.Gson
import com.springct.data.AuthRepositoryImpl
import com.springct.data.EmployeeRepositoryImpl
import com.springct.data.cache.AppDatabase
import com.springct.data.cache.SharedPreferencesDB
import com.springct.data.remote.AuthService
import com.springct.domain.*
import com.springct.ui.view_model.AddEmployeeViewModel
import com.springct.ui.view_model.ListEmployeesViewModel
import com.springct.ui.view_model.LoginViewModel
import com.springct.ui.view_model.SplashViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {

    private val baseUrl = "https://reqres.in/api/"

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin {
            androidContext(this@Application)

            modules(

                module {

                    single<AuthService> {

                        val gson = GsonConverterFactory.create(Gson())

                        val okHttpClient = OkHttpClient.Builder().build()

                        Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .client(okHttpClient)
                            .addConverterFactory(gson)
                            .build()
                            .create(AuthService::class.java)
                    }

                    single {
                        Room.databaseBuilder(get(), AppDatabase::class.java, "spring_ct_db.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    }

                    single {
                        SharedPreferencesDB(
                            context = get()
                        )
                    }
                },

                module {

                    single<AuthRepository> {
                        AuthRepositoryImpl(
                            authService = get(),
                            sharedPreferences = get()
                        )
                    }

                    single<EmployeeRepository> {
                        EmployeeRepositoryImpl(
                            employeeDao = get<AppDatabase>().getEmployeeDao()
                        )
                    }
                },

                module {
                    single {
                        GetLoginStatus(
                            authRepository = get()
                        )
                    }

                    single {
                        DoLogin(
                            authRepository = get()
                        )
                    }

                    single {
                        AddEmployee(employeeRepository = get())
                    }

                    single {
                        GetEmployees(employeeRepository = get())
                    }
                },

                module {

                    viewModel {
                        SplashViewModel(
                            getLoginStatus = get()
                        )
                    }

                    viewModel {
                        LoginViewModel(
                            doLogin = get()
                        )
                    }

                    viewModel {
                        ListEmployeesViewModel(
                            getEmployees = get()
                        )
                    }

                    viewModel {
                        AddEmployeeViewModel(
                            addEmployee = get()
                        )
                    }
                }
            )
        }
    }
}