package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun generateActiveAndCompletedStats_noCompleted_returnsHundredZero(){
        val tasks = listOf<Task>(
                Task("title","desc",isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)

        /*assertEquals(result.completedTasksPercent,0f)
        assertEquals(result.activeTasksPercent,100f)*/

        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))

    }

    @Test
    fun generateActiveAndCompletedStats_empty_returnsZeros(){
        val tasks = ArrayList<Task>()
        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun generateActiveAndCompletedStats_noActive_returnsZeroHundred(){
        val tasks = arrayListOf<Task>(
                Task("Test1","desc1",isCompleted = true),
                Task("Test2","desc2",isCompleted = true)
        )
        val results = getActiveAndCompletedStats(tasks)

        assertThat(results.completedTasksPercent, `is`(100f))
        assertThat(results.activeTasksPercent, `is`(0f))
    }

    @Test
    fun generateActiveAndCompletedStats_error_returnsZeros(){
        val results = getActiveAndCompletedStats(null)
        assertThat(results.activeTasksPercent, `is`(0f))
        assertThat(results.completedTasksPercent, `is`(0f))
    }

    @Test
    fun generateActiveAndCompletedStats_both_returnsFortySixty(){
        val tasks = listOf(
                Task("test1","desc1",isCompleted = true),
                Task("test2","desc2",isCompleted = true),
                Task("test3","desc3",isCompleted = true),
                Task("test4","desc4",isCompleted = false),
                Task("test5","desc5",isCompleted = false)
        )

        val results = getActiveAndCompletedStats(tasks)
        assertThat(results.completedTasksPercent, `is`(60f))
        assertThat(results.activeTasksPercent, `is`(40f))
    }
}