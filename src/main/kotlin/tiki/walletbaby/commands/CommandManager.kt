package tiki.walletbaby.commands

import eu.vendeli.tgbot.types.Message
import tiki.walletbaby.model.CostItem
import java.time.LocalDate

object CommandManager {
    fun getCommand(content: String?, message: Message): Command {
        return when (content) {
            CostCommand.NAME -> getCostCommand(message)
            else -> InvalidCommand(message.chat.id)
        }
    }

    fun getCostCommand(message: Message): Command {
        val user = message.from ?: return InvalidCommand(message.chat.id)
        val params = message.text?.split(" ") ?: return InvalidCommand(message.chat.id)
        if (params.size < 3) return InvalidCommand(message.chat.id)
        try {
            val category = params[1]
            val price = params[2].toFloat()
            val note = if (params.size >= 4) params[3] else ""
            val time = if(params.size == 5) LocalDate.parse(params[4]) else LocalDate.now()
            return CostCommand(
                CostItem(
                    userId = user.id,
                    chatId = message.chat.id,
                    value = price,
                    category = category,
                    date = time,
                    note = note
                )
            )
        } catch (e: Exception) {
            return InvalidCommand(message.chat.id)
        }
    }

}