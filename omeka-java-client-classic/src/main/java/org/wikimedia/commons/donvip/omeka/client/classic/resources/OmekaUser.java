package org.wikimedia.commons.donvip.omeka.client.classic.resources;

import org.wikimedia.commons.donvip.omeka.client.classic.OmekaResourceType;

public record OmekaUser(

		/**
		 * Unique ID for the record.
		 */
		int id,

		String url,

		OmekaResourceType resource,

		/**
	     * This User's username.
	     */
	    String username,

	    /**
	     * The hashed password.
	     *
	     * This field should never contain the plain-text password.  Always
	     * use setPassword() to change the user password.
	     */
	    String password,

	    /**
	     * The salt for the hashed password.
	     */
	    String salt,

	    /**
	     * Whether this user is active and can log in.
	     */
	    boolean active,

	    /**
	     * This user's role.
	     */
	    String role,

	    /**
	     * This user's full or display name.
	     */
	    String name,

	    /**
	     * This user's email address.
	     */
	    String email

) implements OmekaRecord {
}
