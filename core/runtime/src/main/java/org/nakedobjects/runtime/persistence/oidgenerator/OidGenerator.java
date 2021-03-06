package org.nakedobjects.runtime.persistence.oidgenerator;

import org.nakedobjects.metamodel.adapter.NakedObject;
import org.nakedobjects.metamodel.adapter.oid.Oid;
import org.nakedobjects.metamodel.adapter.oid.stringable.OidStringifier;
import org.nakedobjects.metamodel.adapter.oid.stringable.directly.DirectlyStringableOid;
import org.nakedobjects.metamodel.adapter.oid.stringable.hex.OidStringifierHex;
import org.nakedobjects.metamodel.commons.component.Injectable;
import org.nakedobjects.metamodel.commons.component.SessionScopedComponent;
import org.nakedobjects.metamodel.commons.debug.DebugInfo;
import org.nakedobjects.metamodel.commons.encoding.Encodable;
import org.nakedobjects.runtime.persistence.adaptermanager.AdapterManager;
import org.nakedobjects.runtime.persistence.oidgenerator.simple.SerialOid;


public interface OidGenerator extends DebugInfo, SessionScopedComponent, Injectable {


    /**
     * Create a new {@link Oid#isTransient() transient} {@link Oid} for the
     * supplied pojo, uniquely distinguishable from any other {@link Oid}.
     */
    Oid createTransientOid(Object pojo);

    /**
     * Convert the {@link Oid} from {@link Oid#isTransient() transient} to
     * persistent, storing the previous {@link Oid} as {@link Oid#getPrevious()}
     * 
     * <p>
     * Implementation notes:
     * <ul>
     * <li>Note that the {@link AdapterManager} will need to be updated so that the
     *     {@link NakedObject adapter} is re-mapped by the new {@link Oid}.  However,
     *     the <i>not</i> the responsibility of the {@link OidGenerator} to do this; 
     *     it merely ensures that the {@link Oid} is in the correct new state.
     * <li>Some implementations may require that state be set up on the {@link Oid}
     *     prior to call.  Check for details.
     * </ul>
     */
    void convertTransientToPersistentOid(Oid oid);

    /**
     * An {@link OidStringifier} to use for stringifying instances of the concrete {@link Oid}
     * generated by this generator.
     * 
     * <p>
     * All {@link Oid}s (ultimately being {@link Encodable}) can be stringified using {@link OidStringifierHex}.
     * However, some implementations (for example {@link SerialOid}) implement {@link DirectlyStringableOid} which
     * means that we can get a more user-friendly string representation of the {@link Oid}.
     */
    OidStringifier getOidStringifier();
    
}
// Copyright (c) Naked Objects Group Ltd.
