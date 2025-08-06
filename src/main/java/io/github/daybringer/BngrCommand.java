package io.github.daybringer;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permission;

import java.util.Collection;

public interface BngrCommand extends CommandExecutor, TabCompleter
{
    String getName();
    Collection<Permission> getCommandPermissions();
}