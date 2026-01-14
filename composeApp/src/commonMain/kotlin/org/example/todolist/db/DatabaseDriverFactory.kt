package org.example.todolist.db

import app.cash.sqldelight.db.SqlDriver

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
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}