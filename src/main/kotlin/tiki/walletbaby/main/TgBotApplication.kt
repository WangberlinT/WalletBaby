package tiki.walletbaby.main

import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.EntityType
import tiki.walletbaby.commands.CommandManager


suspend fun main() {
    val bot = WalletBabyBot.instance()
    bot.handleUpdates {
        onCommand("/help") {
            message {
                "/cost <category> <price> <note?> <date? default=[current date] format=[yyyy-mm-dd]> \n" +
                        "For example: \n " +
                        "/cost lunch 13.4 \n"  +
                        "/cost lunch 13.4 noodles 2022-10-1"

            }.send(this.update.message?.chat?.id ?: 0, bot)
        }
        onMessage {

            val entity = this.data.entities?.get(0)
            val message = this.data
            if(entity?.type == EntityType.BotCommand) {
                val start = entity.offset
                val end = entity.length
                CommandManager.getCommand(message.text?.substring(start, end), message).excute()
            }
        }
    }
}
