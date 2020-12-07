package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.usage.UsageEvents
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrWaitValue
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    lateinit var tasksViewModel: TasksViewModel
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel(){
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setNewTaskEvent(){
        tasksViewModel.addNewTask()
        val value = tasksViewModel.newTaskEvent.getOrWaitValue()

        assertThat(value.getContentIfNotHandled(),(not(nullValue())))
    }

    @Test
    fun setFilterAllTask_tasksAddViewVisible(){
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        assertThat(tasksViewModel.tasksAddViewVisible.getOrWaitValue(), `is`(true))

    }
}