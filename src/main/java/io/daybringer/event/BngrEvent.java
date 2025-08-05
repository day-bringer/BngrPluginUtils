package io.daybringer.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An abstract base class for all custom BNGR events.
 * <p>
 * This class simplifies creating custom Bukkit events by handling common boilerplate
 * such as the static {@link HandlerList} and support for {@link Cancellable} behavior.
 * <p>
 * All custom events in the BNGR plugin system should extend this class.
 */
public abstract class BngrEvent extends Event implements Cancellable {

    private boolean cancelled;
    private static final HandlerList HANDLERS =  new HandlerList();

    /**
     * Constructs a new BngrEvent.
     * Uses synchronous event behavior by default.
     */
    public BngrEvent()
    {
        this(false);
    }

    /**
     * Constructs a new BngrEvent, allowing you to specify async behavior.
     *
     * @param async True if the event is asynchronous.
     */
    public BngrEvent(boolean async)
    {
        super(async);
        cancelled = false;
    }

    /**
     * Gets the list of handlers listening to this event.
     *
     * @return The {@link HandlerList} for this event instance.
     */
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the static handler list required by Bukkit.
     *
     * @return The global handler list for this event type.
     */
    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled {@code true} to cancel the event, {@code false} to allow it.
     */
    @Override
    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    /**
     * Checks whether the event has been cancelled.
     *
     * @return {@code true} if the event is cancelled; {@code false} otherwise.
     */
    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }
}