package org.nakedobjects.metamodel.facets.object.dirty;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.nakedobjects.metamodel.adapter.NakedObject;
import org.nakedobjects.metamodel.facets.FacetHolder;
import org.nakedobjects.metamodel.java5.ImperativeFacet;
import org.nakedobjects.metamodel.util.InvokeUtils;


public class MarkDirtyObjectFacetViaMethod extends MarkDirtyObjectFacetAbstract implements ImperativeFacet {

    private final Method method;

    public MarkDirtyObjectFacetViaMethod(final Method method, final FacetHolder holder) {
        super(holder);
        this.method = method;
    }

    public List<Method> getMethods() {
    	return Collections.singletonList(method);
    }

	public boolean impliesResolve() {
		return false;
	}

	public boolean impliesObjectChanged() {
		return false;
	}

    public void invoke(final NakedObject object) {
        InvokeUtils.invokeStatic(method);
    }

}

// Copyright (c) Naked Objects Group Ltd.
