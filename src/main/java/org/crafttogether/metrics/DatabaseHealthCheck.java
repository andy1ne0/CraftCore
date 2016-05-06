package org.crafttogether.metrics;

import org.crafttogether.CraftCore;

import com.codahale.metrics.health.HealthCheck;

/**
 * Checks the connection status of the RethinkDB database.
 * 
 * @author theminecoder
 * @version 1.0
 */
public class DatabaseHealthCheck extends HealthCheck {
	@Override
	protected Result check() throws Exception {
		return CraftCore.getInstance().getDatabaseConnection().isOpen()?
				Result.healthy():Result.unhealthy("Database connection lost");
	}
}
