/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.ejb.client;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A selector which selects and returns a node from the available nodes in a cluster to use for discovery. Typical usage of a
 * {@link DiscoveryNodeSelector} involve load balancing of calls to various nodes in the cluster during discovery.
 *
 * @author Kristoffer Lundberg
 */
public interface DiscoveryNodeSelector {

    /**
     * Returns a node from among the {@code totalAvailableNodes}, as the target node for EJB discovery.
     *
     * @param clusterName         the name of the cluster to which the nodes belong (will not be {@code null})
     * @param totalAvailableNodes all available nodes in the cluster (will not be empty or {@code null})
     * @return the selected node name (must not be {@code null})
     */
    String selectNode(final String clusterName, final String[] totalAvailableNodes);

    /**
     * Always use the first available node, regardless of whether it is connected.
     */
    DiscoveryNodeSelector FIRST_AVAILABLE = (clusterName, totalAvailableNodes) -> totalAvailableNodes[0];

    /**
     * Use a random available node, regardless of whether it is connected.
     */
    DiscoveryNodeSelector RANDOM = (clusterName, totalAvailableNodes) -> totalAvailableNodes[ThreadLocalRandom.current().nextInt(totalAvailableNodes.length)];
}
