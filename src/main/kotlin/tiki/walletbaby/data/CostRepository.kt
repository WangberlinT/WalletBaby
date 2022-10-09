package tiki.walletbaby.data

import tiki.walletbaby.data.CostItemDao.category
import tiki.walletbaby.data.CostItemDao.chatId
import tiki.walletbaby.data.CostItemDao.date
import tiki.walletbaby.data.CostItemDao.note
import tiki.walletbaby.data.CostItemDao.uniqueId
import tiki.walletbaby.data.CostItemDao.userId
import tiki.walletbaby.data.CostItemDao.value
import tiki.walletbaby.model.CostItem
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CostRepository {
    init {
        Database.connect("jdbc:sqlite:data/data.db", "org.sqlite.JDBC")
    }
    fun insertCostItem(costItem: CostItem) {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(CostItemDao)

            val id = CostItemDao.insert {
                it[chatId] = costItem.chatId
                it[userId] = costItem.userId
                it[category] = costItem.category
                it[value] = costItem.value
                it[note] = costItem.note ?: ""
                it[date] = costItem.date.format(DateTimeFormatter.ISO_DATE)
            }
        }
    }

    fun getTotalCost(): Float {
        var result = 0f
        transaction {
            addLogger(StdOutSqlLogger)
            result = CostItemDao.selectAll().sumOf {
                it[value].toDouble()
            }.toFloat()
        }
        print("Tiki: $result")
        return result
    }

    fun getAllCostItem(): List<CostItem> {
        val result = mutableListOf<CostItem>()
        transaction {
            CostItemDao.selectAll().forEach {
                result.add(
                    CostItem(
                        uniqueId = it[uniqueId],
                        chatId = it[chatId],
                        userId = it[userId],
                        value = it[value],
                        category = it[category],
                        date = LocalDate.parse(it[date], DateTimeFormatter.ISO_DATE),
                        note = it[note]
                    )
                )
            }
        }

        return result
    }
}