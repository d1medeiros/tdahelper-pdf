package com.example.tdahelper.core

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class TimeCapsule(val stateManager: StateManager){
   private val unit: DurationUnit = DurationUnit.MILLISECONDS
   private val minHour: Int = 1
   private val maxHour: Int = 12
   private val counter: AtomicInteger = AtomicInteger(minHour)
   private val days: AtomicInteger = AtomicInteger(0)
   private val weeks: AtomicInteger = AtomicInteger(0)
   private val months: AtomicInteger = AtomicInteger(0)
   private val years: AtomicInteger = AtomicInteger(0)

    fun next(monster: Monster, list: List<Action>? = null): Monster {
        Thread.sleep(minHour.toDuration(unit).inWholeMilliseconds)
        val countDays = this.counter.get()
        if (countDays > maxHour ) {
            this.days.incrementAndGet()
            this.counter.set(minHour)
        }
        this.counter.andIncrement
        weeks.set(days.get().daysToWeeks())
        months.set(weeks.get().weeksToMonths())
        years.set(months.get().monthsToYears())
        return stateManager.execute(
            monster,
            days.get(),
            counter.get(),
            list,
        )
    }

    fun nextDay(monster: Monster, map: Map<Int, List<Action>>? = null): Monster {
        return (1 until  12).fold(monster){ acc, _ ->
            next(acc, map?.getOrDefault(dayWithHours.invoke(this.counter.get(), this.days.get()), null))
        }
    }

    fun nextWeek(monster: Monster, map: Map<Int, List<Action>>? = null): Monster {
        return (0 until  8).fold(monster){ acc, _ ->
            nextDay(acc, map)
        }
    }

    fun nextMonth(monster: Monster, map: Map<Int, List<Action>>? = null): Monster {
        return (0 until  5).fold(monster){ acc, _ ->
            nextWeek(acc, map)
        }
    }

    fun nextYear(monster: Monster, map: Map<Int, List<Action>>? = null): Monster  {
        return (0 until  12).fold(monster){ acc, _ ->
            nextMonth(acc, map)
        }
    }
}