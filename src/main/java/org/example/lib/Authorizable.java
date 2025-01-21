package org.example.lib;

public interface Authorizable<E> {
    boolean isAuthorized(E entity);
}
