/*
 PureMVC Java MultiCore Port by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@org.puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurescale, Inc., Some rights reserved.
 Your reuse is governed by the Creative Commons Attribution 3.0 License
 */
package org.puremvc.java.multicore.core.model;

import org.puremvc.java.multicore.interfaces.IModel;
import org.puremvc.java.multicore.interfaces.IProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * A Multiton <code>IModel</code> implementation.
 *
 * <P>
 * In PureMVC, the <code>Model</code> class provides
 * access to model objects (Proxies) by named lookup.
 *
 * <P>
 * The <code>Model</code> assumes these responsibilities:</P>
 *
 * <UL>
 * <LI>Maintain a cache of <code>IProxy</code> instances.</LI>
 * <LI>Provide methods for registering, retrieving, and removing
 * <code>IProxy</code> instances.</LI>
 * </UL>
 *
 * <P>
 * Your application must register <code>IProxy</code> instances
 * with the <code>Model</code>. Typically, you use an
 * <code>ICommand</code> to create and register <code>IProxy</code>
 * instances once the <code>Facade</code> has initialized the Core
 * actors.</p>
 *
 * @see org.puremvc.java.multicore.patterns.proxy.Proxy Proxy
 * @see org.puremvc.java.multicore.interfaces.IProxy IProxy
 */
public class Model implements IModel {

	/**
	 * Singleton instance.
	 */
	protected static org.puremvc.java.multicore.core.model.Model instance;

	/**
	 * Mapping of proxyNames to IProxy instances.
	 */
	protected Map<String, IProxy> proxyMap;

	/**
	 * The Multiton Key for this Core.
	 */
	protected String multitonKey;

	protected static Map<String, org.puremvc.java.multicore.core.model.Model> instanceMap = new HashMap<String, org.puremvc.java.multicore.core.model.Model>();

	/**
	 * Constructor.
	 *
	 * <P>
	 * This <code>IModel</code> implementation is a Multiton
	 * so you should not call the constructor
	 * directly, but instead call the static Multiton
	 * Factory method <code>Model.getInstance( multitonKey )</code>
	 *
	 * @exception Error Error if instance for this Multiton key instance has already been constructed
	 */
	protected Model( String key ) {
		multitonKey = key;
		instanceMap.put( multitonKey, this );
		this.proxyMap = new HashMap<String, IProxy>();
		initializeModel();
	}

	/**
	 * Initialize the Singleton <code>Model</code> instance.
	 *
	 * <P>
	 * Called automatically by the constructor, this is your opportunity to
	 * initialize the Singleton instance in your subclass without overriding the
	 * constructor.
	 * </P>
	 */
	protected void initializeModel() {
	}

	/**
	 * <code>Model</code> Multiton Factory method.
	 *
	 * @return the instance for this Multiton key
	 */
	public synchronized static org.puremvc.java.multicore.core.model.Model getInstance( String key ) {
		if( instanceMap.get( key ) == null ) {
			new org.puremvc.java.multicore.core.model.Model( key );
		}
		return instanceMap.get( key );
	}

	/**
	 * Register an <code>Proxy</code> with the <code>Model</code>.
	 *
	 * @param proxy an <code>Proxy</code> to be held by the <code>Model</code>.
	 */
	public void registerProxy( IProxy proxy ) {
		proxy.initializeNotifier( multitonKey );
		this.proxyMap.put( proxy.getProxyName(), proxy );
		proxy.onRegister();
	}

	/**
	 * Remove an <code>Proxy</code> from the <code>Model</code>.
	 *
	 * @param proxy name of the <code>Proxy</code> instance to be removed.
	 */
	public IProxy removeProxy( String proxy ) {
		return this.proxyMap.remove( proxy );
	}

	/**
	 * Retrieve an <code>Proxy</code> from the <code>Model</code>.
	 *
	 * @param proxy
	 *
	 * @return the <code>Proxy</code> instance previously registered with the
	 * given <code>proxyName</code>.
	 */
	public IProxy retrieveProxy( String proxy ) {
		return this.proxyMap.get( proxy );
	}

	/**
	 * Remove an IModel instance
	 */
	public synchronized static void removeModel( String key ) {
		instanceMap.remove( key );
	}

	/**
	 * Check if a Proxy is registered
	 *
	 * @param proxyName
	 *
	 * @return whether a Proxy is currently registered with the given <code>proxyName</code>.
	 */
	public boolean hasProxy( String proxyName ) {
		return proxyMap.containsKey( proxyName );
	}
}
