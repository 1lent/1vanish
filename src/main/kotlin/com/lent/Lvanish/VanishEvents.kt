package com.lent.Lvanish

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageEvent

class VanishEvents : Listener {

    // Utility function to send formatted "Attention!" messages
    private fun sendInfoMessage(player: Player, message: String) {
        val formattedMessage = "${ChatColor.RED}${ChatColor.BOLD}Attention! ${ChatColor.GRAY}$message"
        player.sendMessage(formattedMessage)
    }

    @EventHandler
    fun onPlayerBreakBlock(event: BlockBreakEvent) {
        val player: Player = event.player
        if (VanishManager.vanished.contains(player.uniqueId)) { // Check if the player is vanished
            event.isCancelled = true // Cancel the block break event
            player.sendMessage("${ChatColor.RED}[!] You can't break blocks while vanished!")
        }
    }

    @EventHandler
    fun onPlayerPlace(event: BlockPlaceEvent) {
        val player: Player = event.player
        if (VanishManager.vanished.contains(player.uniqueId)) { // Check if the player is vanished
            event.isCancelled = true
            player.sendMessage("${ChatColor.RED}[!] You can't place blocks while vanished!")
        }
    }

    @EventHandler
    fun onPlayerHit(event: EntityDamageEvent) {
        if (event.entity is Player) {
            val player: Player = event.entity as Player
            if (VanishManager.vanished.contains(player.uniqueId)) { // Check if the player is vanished
                event.isCancelled = true
                player.sendMessage("${ChatColor.RED}[!] You can't hit others while vanished!")
            }
        }
    }

    @EventHandler
    fun onPlayerFallDamage(event: EntityDamageEvent) {
        if (event.entity is Player) {
            val player: Player = event.entity as Player
            if (VanishManager.vanished.contains(player.uniqueId)) { // Check if the player is vanished
                event.isCancelled = true
            }
        }
    }
}
