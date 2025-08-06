package io.github.daybringer.text;

import net.md_5.bungee.api.ChatColor;

/**
 * Utility class for handling color formatting and plugin message prefixes in a Bukkit/Spigot plugin.
 * <p>
 * This class provides static methods for applying color codes to text using the '&amp;' symbol,
 * and for managing a configurable plugin message prefix.
 */
public final class TextUtils
{
    private static String PLUGIN_PREFIX = "";
    /**
     * Translates alternate color codes in the given string using '&amp;' as the prefix character.
     * <p>
     * For example, {@code "&amp;cHello"} becomes red-colored text.
     *
     * @param text The text containing '&amp;' color codes.
     * @return The colored string with Bukkit ChatColor formatting applied.
     *
     * @see org.bukkit.ChatColor
     */
    public static String toColor(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    /**
     * Applies the current plugin prefix and translates any '&amp;' color codes in the resulting message.
     *
     * @param text The message to format, excluding the prefix.
     * @return The formatted message with the plugin prefix and color codes applied.
     */
    public static String toColorPrefix(String text)
    {
        return toColor( PLUGIN_PREFIX + text);
    }
    /**
     * Sets the plugin-wide prefix used in {@link #toColorPrefix(String)}.
     * <p>
     * You can include '&amp;' color codes in the prefix string.
     *
     * @param prefix The prefix string to set, including any color codes.
     */
    public static void setPluginPrefix(String prefix)
    {
        PLUGIN_PREFIX = prefix;
    }
    /**
     * Returns the current plugin message prefix.
     *
     * @return The raw prefix string (may contain '&amp;' color codes).
     */
    public static String getPluginPrefix()
    {
        return PLUGIN_PREFIX;
    }
}
