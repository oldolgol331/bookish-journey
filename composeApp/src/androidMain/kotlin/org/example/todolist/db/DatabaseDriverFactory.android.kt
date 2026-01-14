package org.example.todolist.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/**
 * PackageName : org.example.todolist.db
 * FileName    : DatabaseDriverFactory
 * Author      : oldolgol331
 * Date        : 26. 1. 14.
 * Description :
 * =====================================================================================================================
 * DATE          AUTHOR               DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------
 * 26. 1. 14.    oldolgol331          Initial creation
 */
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = TodoDatabase.Schema,
        context = context,
        name = "todo.db"
    )
}