package com.github.ninjetor.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.inject.Inject;

/**
 * Created by pestano on 29/11/15.
 */
public class NinjectorExtension implements Extension {


    void processAnnotatedType(@Observes ProcessAnnotatedType<?> pat) {
        final AnnotatedType<?> at = pat.getAnnotatedType();

        if(!at.getJavaClass().getSimpleName().equals("RunnableDecorator")) {//cmon Weld ;)
            validateContructors(at);
        }


    }

    private void validateContructors(AnnotatedType<?> annotatedType) {

        for (AnnotatedConstructor<?> constructor : annotatedType.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                throw new RuntimeException("Use field injection!. Offending class: " + annotatedType.getJavaClass().getName());
            }
        }
    }
}
