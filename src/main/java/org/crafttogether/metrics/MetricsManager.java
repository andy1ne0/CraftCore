package org.crafttogether.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

/**
 * @author theminecoder
 * @version 1.0
 */
public final class MetricsManager {
	
	private static MetricRegistry metricRegistry = new MetricRegistry();
	private static HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

	private MetricsManager() {
	}
	
	public static HealthCheckRegistry getHealthCheckRegistry() {
		return healthCheckRegistry;
	}
	
	public static MetricRegistry getMetricRegistry() {
		return metricRegistry;
	}
}
