package com.alexzh.moodtracker.di

import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.MoodTrackerDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

val runtimeDatabase = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = MoodTrackerDatabase.Schema,
            context = InstrumentationRegistry.getInstrumentation().targetContext,
            name = null
        )
    }
}