package io.daybringer;

import io.daybringer.annotations.MustCallSuper;
import io.daybringer.text.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An abstract base class for Bukkit/Spigot plugins using the BNGR framework.
 * <p>
 * This class initializes a {@link Register} utility for simplified registration
 * of commands, events, and permissions. It also provides helper methods to send
 * colored messages to the console and players.
 */
public abstract class BngrPlugin extends JavaPlugin
{
    private Register register;

    /**
     * Called when the plugin is enabled. Initializes the {@link Register} instance
     * used for command/event/permission registration.
     * <p><strong>Subclasses overriding this must call {@code super.onEnable()}.</strong>
     */
    @MustCallSuper
    @Override
    public void onEnable()
    {
        register = new Register(this);
    }

    /**
     * Called when the plugin is disabled.
     * <p><strong>Subclasses overriding this must call {@code super.onEnable()}.</strong>
     */
    @MustCallSuper
    @Override
    public void onDisable()
    {
    }

    /**
     * Returns the {@link Register} instance for this plugin.
     * <p>
     * Use this to easily register commands, events, and permissions.
     *
     * @return The Register helper instance.
     */
    public Register register()
    {
        return register;
    }

    /**
     * Sends a color-formatted message to the server console.
     * <p>
     * Uses {@link TextUtils#toColor(String)} to apply color codes.
     *
     * @param message The message to send (supports '&' color codes).
     */
    public void sendConsole(String message)
    {
        this.getServer().getConsoleSender().sendMessage(TextUtils.toColor(message));
    }

    /**
     * Sends a color-formatted message to a specific player.
     * <p>
     * If the player is null or offline, no message is sent.
     * Uses {@link TextUtils#toColor(String)} to apply color codes.
     *
     * @param message The message to send (supports '&' color codes).
     * @param player  The target player to receive the message.
     */
    public void sendPlayer(String message, Player player)
    {
        if(player != null && player.isOnline())
        {
            player.sendMessage(TextUtils.toColor(message));
        }
    }
}
