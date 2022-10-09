package tiki.walletbaby.model

import java.time.LocalDate

data class CostItem(
    val chatId: Long, // indicate in which chat(privacy or group)
    val userId: Long,
    val value: Float,
    val category: String,
    val date: LocalDate,
    val note: String?,
    val uniqueId: Long? = null, // unique CostItem id, auto increase
)
