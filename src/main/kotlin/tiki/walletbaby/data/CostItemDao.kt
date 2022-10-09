package tiki.walletbaby.data

import org.jetbrains.exposed.sql.*

object CostItemDao : Table("cost") {
    val uniqueId = long("uniqueId").autoIncrement()
    val chatId = long("chatId") // indicate in which chat(privacy or group)
    val userId = long("userId")
    val value = float("value") // cost value
    val category = varchar("category", 50)
    val date = varchar("date", 20) //ISO format yyyy-mm-dd or yyyy-mm-dd+hh:mm
    val note = varchar("note", 200)

    override val primaryKey = PrimaryKey(uniqueId, name = "PK_Cost_ID")
}