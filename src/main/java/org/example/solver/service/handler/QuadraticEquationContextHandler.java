package org.example.solver.service.handler;

import org.example.lib.ContextFilter;
import org.example.lib.ContextHandler;
import org.example.solver.entity.QuadraticEquationContext;

import java.util.List;

/**
 * Handler for processing and applying filters to QuadraticEquationContext.
 * This class extends the base ContextHandler and passes the context filters
 * for the quadratic equation context.
 */
public class QuadraticEquationContextHandler extends ContextHandler<QuadraticEquationContext> {

    /**
     * Constructor that accepts a list of filters for processing the context.
     *
     * @param contextFilters the list of filters to apply to the context
     */
    public QuadraticEquationContextHandler(List<ContextFilter<QuadraticEquationContext>> contextFilters) {
        // Pass the filters to the superclass ContextHandler
        super(contextFilters);
    }
}
