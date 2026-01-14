package org.example.todolist

import androidx.compose.runtime.Immutable

/**
 * PackageName : org.example.todolist
 * FileName    : Todo
 * Author      : oldolgol331
 * Date        : 26. 1. 14.
 * Description : 할 일(Todo) 데이터를 담는 객체입니다.
 * @property id 고유 ID
 * @property content 할 일 내용
 * @property isDone 할 일 완료 여부
 * =====================================================================================================================
 * DATE          AUTHOR               DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------
 * 26. 1. 14.    oldolgol331          Initial creation
 */
@Immutable
data class Todo(
    val id: Long,
    val content: String,
    val isDone: Boolean = false
)