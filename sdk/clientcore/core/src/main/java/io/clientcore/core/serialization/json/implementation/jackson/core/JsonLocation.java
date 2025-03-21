// Original file from https://github.com/FasterXML/jackson-core under Apache-2.0 license.
/*
 * Jackson JSON-processor.
 *
 * Copyright (c) 2007- Tatu Saloranta, tatu.saloranta@iki.fi
 */

package io.clientcore.core.serialization.json.implementation.jackson.core;

import io.clientcore.core.serialization.json.implementation.jackson.core.io.ContentReference;

/**
 * Object that encapsulates Location information used for reporting
 * parsing (or potentially generation) errors, as well as current location
 * within input streams.
 *<p>
 * NOTE: users should be careful if using {@link #equals} implementation as
 * it may or may not compare underlying "content reference" for equality.
 * Instead if would make sense to explicitly implementing equality checks
 * using specific criteria caller desires.
 */
public class JsonLocation implements java.io.Serializable {
    private static final long serialVersionUID = 2L; // in 2.13

    /**
     * Shared immutable "N/A location" that can be returned to indicate
     * that no location information is available.
     *<p>
     * NOTE: before 2.9, Location was given as String "N/A"; with 2.9 it was
     * removed so that source should be indicated as "UNKNOWN".
     */
    public final static JsonLocation NA = new JsonLocation(ContentReference.unknown(), -1L, -1L, -1, -1);

    protected final long _totalBytes;
    protected final long _totalChars;

    protected final int _lineNr;
    protected final int _columnNr;

    /**
     * Reference to input source; never null (but may be that of
     * {@link ContentReference#unknown()}).
     *
     * @since 2.13 (before we have {@code _sourceRef} (Object-valued)
     */
    protected final ContentReference _contentReference;

    /**
     * Lazily constructed description for source; constructed if and
     * when {@link #sourceDescription()} is called, retained.
     *
     * @since 2.13
     */
    protected transient String _sourceDescription;

    /*
     * /**********************************************************************
     * /* Life cycle
     * /**********************************************************************
     */

    public JsonLocation(ContentReference contentRef, long totalChars, int lineNr, int colNr) {
        this(contentRef, -1L, totalChars, lineNr, colNr);
    }

    public JsonLocation(ContentReference contentRef, long totalBytes, long totalChars, int lineNr, int columnNr) {
        // 14-Mar-2021, tatu: Defensive programming, but also for convenience...
        if (contentRef == null) {
            contentRef = ContentReference.unknown();
        }
        _contentReference = contentRef;
        _totalBytes = totalBytes;
        _totalChars = totalChars;
        _lineNr = lineNr;
        _columnNr = columnNr;
    }

    /*
     * /**********************************************************************
     * /* Simple accessors
     * /**********************************************************************
     */

    /**
     * Accessor for getting a textual description of source reference
     *<p>
     * Note: implementation will simply call
     * {@link ContentReference#buildSourceDescription()})
     *<p>
     * NOTE: not added as a "getter" to prevent it from getting serialized.
     *
     * @return Description of the source reference
     *
     * @since 2.9
     */
    public String sourceDescription() {
        // 04-Apr-2021, tatu: Construct lazily but retain
        if (_sourceDescription == null) {
            _sourceDescription = _contentReference.buildSourceDescription();
        }
        return _sourceDescription;
    }

    // @since 2.13
    public StringBuilder appendOffsetDescription(StringBuilder sb) {
        // 04-Apr-2021, tatu: [core#694] For binary content, we have no line
        // number or column position indicators; try using what we do have
        // (if anything)

        if (_contentReference.hasTextualContent()) {
            sb.append("line: ");
            // should be 1-based, but consider -1 to be canonical "got none"
            if (_lineNr >= 0) {
                sb.append(_lineNr);
            } else {
                sb.append("UNKNOWN");
            }
            sb.append(", column: ");
            if (_columnNr >= 0) { // same here
                sb.append(_columnNr);
            } else {
                sb.append("UNKNOWN");
            }
        } else {
            // 04-Apr-2021, tatu: Ideally byte formats would not need line/column
            // info, but for backwards-compatibility purposes (Jackson 2.x),
            // will leave logic here
            if (_lineNr > 0) { // yes, require 1-based in case of allegedly binary content
                sb.append("line: ").append(_lineNr);
                if (_columnNr > 0) {
                    sb.append(", column: ");
                    sb.append(_columnNr);
                }
            } else {
                sb.append("byte offset: #");
                // For binary formats, total bytes should be the canonical offset
                // for token/current location
                if (_totalBytes >= 0) {
                    sb.append(_totalBytes);
                } else {
                    sb.append("UNKNOWN");
                }
            }
        }
        return sb;
    }

    /*
     * /**********************************************************************
     * /* Standard method overrides
     * /**********************************************************************
     */

    @Override
    public int hashCode() {
        int hash = (_contentReference == null) ? 1 : 2;
        hash ^= _lineNr;
        hash += _columnNr;
        hash ^= (int) _totalChars;
        hash += (int) _totalBytes;
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (other == null)
            return false;
        if (!(other instanceof JsonLocation))
            return false;
        JsonLocation otherLoc = (JsonLocation) other;

        if (_contentReference == null) {
            if (otherLoc._contentReference != null)
                return false;
        } else if (!_contentReference.equals(otherLoc._contentReference)) {
            return false;
        }

        return (_lineNr == otherLoc._lineNr)
            && (_columnNr == otherLoc._columnNr)
            && (_totalChars == otherLoc._totalChars)
            && (_totalBytes == otherLoc._totalBytes);
    }

    @Override
    public String toString() {
        final String srcDesc = sourceDescription();
        StringBuilder sb = new StringBuilder(40 + srcDesc.length()).append("[Source: ").append(srcDesc).append("; ");
        return appendOffsetDescription(sb).append(']').toString();
    }
}
