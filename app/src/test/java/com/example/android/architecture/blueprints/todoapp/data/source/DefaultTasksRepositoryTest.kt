package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultTasksRepositoryTest{

    private val task1 = Task("title1","desc1")
    private val task2 = Task("title2","desc2")
    private val task3 = Task("title3","desc3")
    private val task4 = Task("title4","desc4")

    private val remoteTasks = listOf<Task>(task1,task2,task3,task4)
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var taskRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource

    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun createRepository(){
        taskRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())

        tasksRepository = DefaultTasksRepository(taskRemoteDataSource,tasksLocalDataSource, Dispatchers.Unconfined)
    }

    @Test
    fun getTasks_requestAllTasksFromRemoteDataSource() = runBlocking{
        val tasks = tasksRepository.getTasks(true) as Result.Success

        assertThat(tasks.data,`is`(IsEqual(remoteTasks)))
    }

}

