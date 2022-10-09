package tiki.walletbaby.commands

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.Chat
import eu.vendeli.tgbot.types.Message
import eu.vendeli.tgbot.types.User

class CommandCenter {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message {
            "Hello!!! I am WalletBaby, your cute finance tracker :) \n" +
                    "try /help to get more usage"
        }.send(user, bot)
    }

    @CommandHandler(["/help"])
    suspend fun help(chat: Chat, bot: TelegramBot) {
        message {
            "/cost <category> <price> <note?> <date? default=[current date] format=[yyyy-mm-dd]> \n" +
                    "For example: \n " +
                    "/cost lunch 13.4 \n"  +
                    "/cost lunch 13.4 noodles 2022-10-1"

        }.send(chat.id, bot)
    }

    @CommandHandler(["/cost"])
    suspend fun cost(message: Message) {
        CommandManager.getCostCommand(message).excute()
    }
}