package com.lent.Lvanish

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VanishCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player && sender.hasPermission("lvanish.access")) {
            val player: Player = sender
            if (VanishManager.vanished.contains(player.uniqueId)) { // If the player is vanished
                VanishManager.vanished.remove(player.uniqueId) // Unvanish the player
                for (target: Player in Bukkit.getOnlinePlayers()) {
                    target.showPlayer(player)
                }
                sendInfoMessage(player, "You are now unvanished!")
            } else { // Vanish the player
                VanishManager.vanished.add(player.uniqueId)
                for (target: Player in Bukkit.getOnlinePlayers()) {
                    target.hidePlayer(player)
                }
                sendInfoMessage(player, "You are now vanished!")
            }
        } else {
            sender.sendMessage("${ChatColor.RED}You do not have permissions to execute this command!")
        }
        return true
    }

    // Utility function to send formatted "INFO!" messages
    private fun sendInfoMessage(player: Player, message: String) {
        val formattedMessage = "${ChatColor.RED}${ChatColor.BOLD}Attention! ${ChatColor.GRAY}$message"
        player.sendMessage(formattedMessage)
    }
}
