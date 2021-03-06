/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.process.traversal.step.map;

import org.apache.tinkerpop.gremlin.FeatureRequirement;
import org.apache.tinkerpop.gremlin.LoadGraphWith;
import org.apache.tinkerpop.gremlin.process.AbstractGremlinProcessTest;
import org.apache.tinkerpop.gremlin.process.GremlinProcessRunner;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.apache.tinkerpop.gremlin.LoadGraphWith.GraphData.MODERN;
import static org.apache.tinkerpop.gremlin.process.traversal.Order.decr;
import static org.apache.tinkerpop.gremlin.process.traversal.Scope.local;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.V;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.bothE;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.inE;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.outE;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.select;
import static org.apache.tinkerpop.gremlin.structure.Column.keys;
import static org.apache.tinkerpop.gremlin.structure.Column.values;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
@RunWith(GremlinProcessRunner.class)
public abstract class AddEdgeTest extends AbstractGremlinProcessTest {

    public abstract Traversal<Vertex, Edge> get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX(final Object v1Id);

    public abstract Traversal<Vertex, Edge> get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X(final Object v1Id);

    public abstract Traversal<Vertex, Edge> get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX();

    public abstract Traversal<Vertex, Edge> get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X();

    public abstract Traversal<Vertex, Edge> get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX();

    public abstract Traversal<Vertex, Edge> get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX();

    public abstract Traversal<Vertex, Edge> get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X(final Vertex a, final Vertex b);

    public abstract Traversal<Vertex, Edge> get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X(final Vertex a, final Vertex b);

    public abstract Traversal<Edge, Edge> get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X(final Vertex a, final Vertex b);

    public abstract Traversal<Vertex, Edge> get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX();

    public abstract Traversal<Edge, Edge> get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_decrX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX();

    ///////

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX() {
        final Traversal<Vertex, Edge> traversal = get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX(convertToVertexId("marko"));
        printTraversalForm(traversal);
        int count = 0;
        while (traversal.hasNext()) {
            final Edge edge = traversal.next();
            assertEquals("createdBy", edge.label());
            assertEquals(0, IteratorUtils.count(edge.properties()));
            count++;

        }
        assertEquals(1, count);
        assertEquals(7, IteratorUtils.count(g.E()));
        assertEquals(6, IteratorUtils.count(g.V()));
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X() {
        final Traversal<Vertex, Edge> traversal = get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X(convertToVertexId("marko"));
        printTraversalForm(traversal);
        int count = 0;
        while (traversal.hasNext()) {
            final Edge edge = traversal.next();
            assertEquals("createdBy", edge.label());
            assertEquals(2.0d, edge.<Number>value("weight").doubleValue(), 0.00001d);
            assertEquals(1, IteratorUtils.count(edge.properties()));
            count++;


        }
        assertEquals(1, count);
        assertEquals(7, IteratorUtils.count(g.E()));
        assertEquals(6, IteratorUtils.count(g.V()));
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX() {
        final Traversal<Vertex, Edge> traversal = get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX();
        printTraversalForm(traversal);
        int count = 0;
        while (traversal.hasNext()) {
            final Edge edge = traversal.next();
            assertEquals("existsWith", edge.label());
            assertEquals("now", edge.value("time"));
            assertEquals(1, IteratorUtils.count(edge.properties()));
            count++;
        }
        assertEquals(36, count);
        assertEquals(42, IteratorUtils.count(g.E()));
        for (final Vertex vertex : IteratorUtils.list(g.V())) {
            assertEquals(6, IteratorUtils.count(vertex.edges(Direction.OUT, "existsWith")));
            assertEquals(6, IteratorUtils.count(vertex.edges(Direction.IN, "existsWith")));
        }
        assertEquals(6, IteratorUtils.count(g.V()));
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X() {
        final Traversal<Vertex, Edge> traversal = get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X();
        printTraversalForm(traversal);
        int count = 0;
        while (traversal.hasNext()) {
            final Edge edge = traversal.next();
            assertEquals("codeveloper", edge.label());
            assertEquals(2009, (int) edge.value("year"));
            assertEquals(1, IteratorUtils.count(edge.properties()));
            assertEquals("person", edge.inVertex().label());
            assertEquals("person", edge.outVertex().label());
            assertFalse(edge.inVertex().value("name").equals("vadas"));
            assertFalse(edge.outVertex().value("name").equals("vadas"));
            assertFalse(edge.inVertex().equals(edge.outVertex()));
            count++;

        }
        assertEquals(6, count);
        assertEquals(12, IteratorUtils.count(g.E()));
        assertEquals(6, IteratorUtils.count(g.V()));
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X() {
        final Vertex a = g.V().has("name", "marko").next();
        final Vertex b = g.V().has("name", "peter").next();

        final Traversal<Vertex, Edge> traversal = get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X(a, b);
        final Edge edge = traversal.next();
        assertFalse(traversal.hasNext());
        assertEquals(edge.outVertex(), convertToVertex(graph, "marko"));
        assertEquals(edge.inVertex(), convertToVertex(graph, "peter"));
        assertEquals("knows", edge.label());
        assertEquals(1, IteratorUtils.count(edge.properties()));
        assertEquals(0.5d, edge.value("weight"), 0.1d);
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(7L, g.E().count().next().longValue());
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX() {
        final Traversal<Vertex, Edge> traversal = get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX();
        printTraversalForm(traversal);
        int count = 0;
        while (traversal.hasNext()) {
            final Edge edge = traversal.next();
            assertEquals("createdBy", edge.label());
            assertEquals(2009, (int) edge.value("year"));
            assertEquals("public", edge.value("acl"));
            assertEquals(2, IteratorUtils.count(edge.properties()));
            assertEquals("person", edge.inVertex().label());
            assertEquals("software", edge.outVertex().label());
            if (edge.outVertex().value("name").equals("ripple"))
                assertEquals("josh", edge.inVertex().value("name"));
            count++;

        }
        assertEquals(4, count);
        assertEquals(10, IteratorUtils.count(g.E()));
        assertEquals(6, IteratorUtils.count(g.V()));
    }

    @Test
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    @FeatureRequirement(featureClass = Graph.Features.VertexFeatures.class, feature = Graph.Features.VertexFeatures.FEATURE_ADD_VERTICES)
    public void g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX() {
        final Traversal<Vertex, Edge> traversal = get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX();
        printTraversalForm(traversal);
        assertEquals("next", traversal.next().label());
        assertFalse(traversal.hasNext());
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(6L, g.E().count().next().longValue());
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 2L, 2L), g.V().map(bothE().count()).toList());
        assertEquals(Arrays.asList(1L, 1L, 1L, 1L, 1L, 1L), g.V().map(inE().count()).toList());
        assertEquals(Arrays.asList(1L, 1L, 1L, 1L, 1L, 1L), g.V().map(outE().count()).toList());
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X() {
        final Vertex a = g.V().has("name", "marko").next();
        final Vertex b = g.V().has("name", "peter").next();

        final Traversal<Vertex, Edge> traversal = get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X(a, b);
        printTraversalForm(traversal);
        final Edge edge = traversal.next();
        assertEquals(edge.outVertex(), convertToVertex(graph, "marko"));
        assertEquals(edge.inVertex(), convertToVertex(graph, "peter"));
        assertEquals("knows", edge.label());
        assertEquals(1, IteratorUtils.count(edge.properties()));
        assertEquals(0.1d, edge.value("weight"), 0.1d);
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(7L, g.E().count().next().longValue());
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X() {
        final Vertex a = g.V().has("name", "marko").next();
        final Vertex b = g.V().has("name", "peter").next();

        final Traversal<Edge, Edge> traversal = get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X(a, b);
        printTraversalForm(traversal);
        final Edge edge = traversal.next();
        assertEquals(edge.outVertex(), convertToVertex(graph, "marko"));
        assertEquals(edge.inVertex(), convertToVertex(graph, "peter"));
        assertEquals("knows", edge.label());
        assertEquals(1, IteratorUtils.count(edge.properties()));
        assertEquals(0.1d, edge.value("weight"), 0.1d);
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(7L, g.E().count().next().longValue());
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX() {
        final Traversal<Vertex, Edge> traversal = get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX();
        printTraversalForm(traversal);
        final Edge edge = traversal.next();
        assertFalse(traversal.hasNext());
        assertEquals("created", edge.label());
        assertEquals(convertToVertexId("marko"), edge.inVertex().id());
        assertEquals(convertToVertexId("lop"), edge.outVertex().id());
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(7L, g.E().count().next().longValue());
    }

    @Test
    @LoadGraphWith(MODERN)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_decrX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX() {
        final Traversal<Edge, Edge> traversal = get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_decrX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX();
        printTraversalForm(traversal);
        final Edge edge = traversal.next();
        assertFalse(traversal.hasNext());
        assertEquals("created", edge.label());
        assertEquals(convertToVertexId("vadas"), edge.outVertex().id());
        assertEquals(convertToVertexId("lop"), edge.inVertex().id());
        assertEquals(6L, g.V().count().next().longValue());
        assertEquals(7L, g.E().count().next().longValue());
    }

    public static class Traversals extends AddEdgeTest {

        @Override
        public Traversal<Vertex, Edge> get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX(final Object v1Id) {
            return g.V(v1Id).as("a").out("created").addE("createdBy").to("a");
        }

        @Override
        public Traversal<Vertex, Edge> get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X(final Object v1Id) {
            return g.V(v1Id).as("a").out("created").addE("createdBy").to("a").property("weight", 2.0d);
        }

        @Override
        public Traversal<Vertex, Edge> get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX() {
            return g.V().aggregate("x").as("a").select("x").unfold().addE("existsWith").to("a").property("time", "now");
        }

        @Override
        public Traversal<Vertex, Edge> get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X() {
            return g.V().as("a").out("created").in("created").where(P.neq("a")).as("b").addE("codeveloper").from("a").to("b").property("year", 2009);
        }

        @Override
        public Traversal<Vertex, Edge> get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX() {
            return g.V().as("a").in("created").addE("createdBy").from("a").property("year", 2009).property("acl", "public");
        }

        @Override
        public Traversal<Vertex, Edge> get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X(final Vertex a, final Vertex b) {
            return g.withSideEffect("b", b).V(a).addE("knows").to("b").property("weight", 0.5d);
        }

        @Override
        public Traversal<Vertex, Edge> get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX() {
            return g.addV().as("first").repeat(__.addE("next").to(__.addV()).inV()).times(5).addE("next").to(select("first"));
        }

        @Override
        public Traversal<Vertex, Edge> get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X(final Vertex a, final Vertex b) {
            return g.V(a).addE("knows").to(b).property("weight", 0.1d);
        }

        @Override
        public Traversal<Edge, Edge> get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X(final Vertex a, final Vertex b) {
            return g.addE("knows").from(a).to(b).property("weight", 0.1d);
        }

        @Override
        public Traversal<Vertex, Edge> get_g_V_hasXname_markoX_asXaX_outEXcreatedX_asXbX_inV_addEXselectXbX_labelX_toXaX() {
            return g.V().has("name", "marko").as("a").outE("created").as("b").inV().addE(select("b").label()).to("a");
        }

        @Override
        public Traversal<Edge, Edge> get_g_addEXV_outE_label_groupCount_orderXlocalX_byXvalues_decrX_selectXkeysX_unfold_limitX1XX_fromXV_hasXname_vadasXX_toXV_hasXname_lopXX() {
            return g.addE(V().outE().label().groupCount().order(local).by(values, decr).select(keys).<String>unfold().limit(1)).from(V().has("name", "vadas")).to(V().has("name", "lop"));
        }
    }
}
