package io.daybringer;

import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Utility class to simplify registration of events, commands, and permissions
 * in a Bukkit/Spigot plugin.
 */
public class Register
{
    private final Plugin plugin;
    /**
     * Constructs a new Register helper bound to a specific plugin.
     *
     * @param plugin The plugin instance using this helper.
     */
    public Register(Plugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Registers a Bukkit event listener with the plugin's PluginManager.
     *
     * @param listener The listener to register.
     *
     * @see org.bukkit.event.Listener
     */
    public void event(Listener listener)
    {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    /**
     * Registers a command, tab completer, and command permissions with the plugin.
     * <p>
     * The command must be defined in the plugin.yml. If the command is not found,
     * a warning is logged and registration is skipped.
     *
     * @param bngrCommand The command to register, implementing both CommandExecutor and TabCompleter.
     *
     * @see org.bukkit.command.CommandExecutor
     * @see org.bukkit.command.TabCompleter
     * @see org.bukkit.command.PluginCommand
     */
    public void command(BngrCommand bngrCommand)
    {
        PluginCommand command = plugin.getServer().getPluginCommand(bngrCommand.getName());

        if(command == null)
        {
            plugin.getLogger().warning(String.format("Command: %s not found, skipping registration.", bngrCommand.getName()));
            return;
        }

        command.setExecutor(bngrCommand);
        command.setTabCompleter(bngrCommand);

        for(Permission perm : bngrCommand.getCommandPermissions())
        {
            permission(perm);
        }
    }

    /**
     * Registers a permission with the server's PluginManager if it hasn't already been registered.
     *
     * @param permission The permission to register.
     *
     * @see org.bukkit.permissions.Permission
     * @see org.bukkit.plugin.PluginManager#addPermission(Permission)
     */
    public void permission(Permission permission)
    {
        PluginManager pm = plugin.getServer().getPluginManager();

        if(pm.getPermission(permission.getName()) == null)
            pm.addPermission(permission);
    }
}
