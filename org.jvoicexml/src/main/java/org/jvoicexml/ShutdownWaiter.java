/*
 * JVoiceXML - A free VoiceXML implementation.
 *
 * Copyright (C) 2005-2017 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.jvoicexml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Waiter for a JVoiceXML shutdown request. This is necessary to decouple a
 * remote shutdown request via {@link JVoiceXml#shutdown()} from the actual
 * shutdown. While the JNDI call returns immediately, the shutdown is executed
 * asynchronously.
 * @author Dirk Schnelle-Walka
 * @version $Revision$
 * @since 0.7
 */
class ShutdownWaiter extends Thread {
    /** Logger for this class. */
    private static final Logger LOGGER =
            LogManager.getLogger(ShutdownWaiter.class);

    /** Reference to JVoiceXML. */
    private final JVoiceXmlMain jvxml;

    /** Semaphore to handle the shutdown request. */
    private final Object shutdownRequestSemaphore;

    /**
     * Constructs a new object.
     * @param main reference to JVoiceXML.
     */
    ShutdownWaiter(final JVoiceXmlMain main) {
        jvxml = main;
        shutdownRequestSemaphore = new Object();
        setName(ShutdownWaiter.class.getSimpleName());
        setDaemon(true);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("initialized shutdown sequence");
        }
        synchronized (shutdownRequestSemaphore) {
            try {
                shutdownRequestSemaphore.wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        jvxml.shutdownSequence();
    }

    /**
     * Triggers the shutdown sequence to start.
     */
    public void triggerShutdown() {
        synchronized (shutdownRequestSemaphore) {
            shutdownRequestSemaphore.notifyAll();
        }
    }
}
