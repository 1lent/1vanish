package com.lent.Lvanish


import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        // Create instances of command and event classes
        val vanishCommand = VanishCommand()
        val vanishEvents = VanishEvents()

        // Register the command and events
        Bukkit.getPluginManager().registerEvents(vanishEvents, this)
        getCommand("vanish")?.setExecutor(vanishCommand)
    }
}

