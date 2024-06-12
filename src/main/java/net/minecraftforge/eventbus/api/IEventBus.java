package net.minecraftforge.eventbus.api;

import java.util.function.Consumer;

/**
 * EventBus API.
 *
 * Register for events and post events.
 *
 * Contains factory methods to construct an instance {#create()} and {#create(IEventExceptionHandler)}
 */
public interface IEventBus {
    /**
     * Register an instance object or a Class, and add listeners for all {SubscribeEvent} annotated methods
     * found there.
     *
     * Depending on what is passed as an argument, different listener creation behaviour is performed.
     *
     * <dl>
     *     <dt>Object Instance</dt>
     *     <dd>Scanned for <em>non-static</em> methods annotated with {SubscribeEvent} and creates listeners for
     *     each method found.</dd>
     *     <dt>Class Instance</dt>
     *     <dd>Scanned for <em>static</em> methods annotated with {SubscribeEvent} and creates listeners for
     *     each method found.</dd>
     * </dl>
     *
     * @param target Either a {Class} instance or an arbitrary object, for scanning and event listener creation
     */
    void register(Object target);

    /**
     * Add a consumer listener with default {EventPriority#NORMAL} and not recieving cancelled events.
     *
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {Event} subclass to listen for
     */
    <T extends Event> void addListener(Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and not receiving cancelled events.
     *
     * @param priority {EventPriority} for this listener
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {Event} subclass to listen for
     */
    <T extends Event> void addListener(EventPriority priority, Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and potentially cancelled events.
     *
     * @param priority {EventPriority} for this listener
     * @param receiveCancelled Indicate if this listener should receive events that have been {Cancelable} cancelled
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {Event} subclass to listen for
     */
    <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and potentially cancelled events.
     *
     * Use this method when one of the other methods fails to determine the concrete {Event} subclass that is
     * intended to be subscribed to.
     *
     * @param priority {EventPriority} for this listener
     * @param receiveCancelled Indicate if this listener should receive events that have been {Cancelable} cancelled
     * @param eventType The concrete {Event} subclass to subscribe to
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {Event} subclass to listen for
     */
    <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer);

    /**
     * Add a consumer listener for a {GenericEvent} subclass, filtered to only be called for the specified
     * filter {Class}.
     *
     * @param genericClassFilter A {Class} which the {GenericEvent} should be filtered for
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {GenericEvent} subclass to listen for
     * @param <F> The {Class} to filter the {GenericEvent} for
     */
    <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and not receiving cancelled events,
     * for a {GenericEvent} subclass, filtered to only be called for the specified
     * filter {Class}.
     *
     * @param genericClassFilter A {Class} which the {GenericEvent} should be filtered for
     * @param priority {EventPriority} for this listener
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {GenericEvent} subclass to listen for
     * @param <F> The {Class} to filter the {GenericEvent} for
     */
    <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and potentially cancelled events,
     * for a {GenericEvent} subclass, filtered to only be called for the specified
     * filter {Class}.
     *
     * @param genericClassFilter A {Class} which the {GenericEvent} should be filtered for
     * @param priority {EventPriority} for this listener
     * @param receiveCancelled Indicate if this listener should receive events that have been {Cancelable} cancelled
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {GenericEvent} subclass to listen for
     * @param <F> The {Class} to filter the {GenericEvent} for
     */
    <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Consumer<T> consumer);

    /**
     * Add a consumer listener with the specified {EventPriority} and potentially cancelled events,
     * for a {GenericEvent} subclass, filtered to only be called for the specified
     * filter {Class}.

     * Use this method when one of the other methods fails to determine the concrete {GenericEvent} subclass that is
     * intended to be subscribed to.
     *
     * @param genericClassFilter A {Class} which the {GenericEvent} should be filtered for
     * @param priority {EventPriority} for this listener
     * @param receiveCancelled Indicate if this listener should receive events that have been {Cancelable} cancelled
     * @param eventType The concrete {GenericEvent} subclass to subscribe to
     * @param consumer Callback to invoke when a matching event is received
     * @param <T> The {GenericEvent} subclass to listen for
     * @param <F> The {Class} to filter the {GenericEvent} for
     */
    <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer);

    /**
     * Unregister the supplied listener from this EventBus.
     *
     * Removes all listeners from events.
     *
     * NOTE: Consumers can be stored in a variable if unregistration is required for the Consumer.
     *
     * @param object The object, {Class} or {Consumer} to unsubscribe.
     */
    void unregister(Object object);

    /**
     * Submit the event for dispatch to appropriate listeners
     *
     * @param event The event to dispatch to listeners
     * @return true if the event was {Cancelable} cancelled
     */
    boolean post(Event event);

    /**
     * Submit the event for dispatch to listeners. The invoke wrapper allows for
     * wrap handling of the actual dispatch, to allow for monitoring of individual event
     * dispatch
     *
     * @param event The event to dispatch to listeners
     * @param wrapper A wrapper function to handle actual dispatch
     * @return true if the event was {Cancelable} cancelled
     */
    boolean post(Event event, IEventBusInvokeDispatcher wrapper);

    /**
     * Shuts down this event bus.
     *
     * No future events will be fired on this event bus, so any call to {#post(Event)} will be a no op after this method has been invoked
     */
    void shutdown();


    void start();
}
