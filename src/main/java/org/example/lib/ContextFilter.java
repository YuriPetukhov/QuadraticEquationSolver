package org.example.lib;


import org.example.solver.enums.ContextFilterType;

public interface ContextFilter<C> extends Authorizable<C> {

    void apply(C context);

    ContextFilterType getFilterType();

}
