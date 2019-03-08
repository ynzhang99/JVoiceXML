/*
 * JVoiceXML - A free VoiceXML implementation.
 *
 * Copyright (C) 2011-2017 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.implementation.jsapi20;

import javax.speech.synthesis.SynthesizerMode;

/**
 * A factory for a {@link SynthesizerMode}.
 *
 * <p>
 * Objects implementing this interface can be used to create a
 * {@link SynthesizerMode} for the
 * {@link org.jvoicexml.implementation.SystemOutputImplementation} objects that are
 * created by the
 * {@link Jsapi20SystemOutputImplementationFactory}.
 * </p>
 *
 * @author Dirk Schnelle-Walka
 * @since 0.7.5
 */
public interface SynthesizerModeFactory {

    /**
     * Creates a new descriptor.
     * @return configured descriptor.
     */
    SynthesizerMode createDescriptor();

}
