package org.example.todolist.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

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
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = TodoDatabase.Schema,
        name = "todo.db"
    )
}