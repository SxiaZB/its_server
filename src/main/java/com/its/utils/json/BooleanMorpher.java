package com.its.utils.json;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.primitive.AbstractPrimitiveMorpher;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Disc:
 * Created by xinxinran on 14/11/25.
 */
public class BooleanMorpher extends AbstractPrimitiveMorpher {
    private Boolean defaultValue;

    public BooleanMorpher() {
    }

    public BooleanMorpher(Boolean defaultValue) {
        super(true);
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(!(obj instanceof BooleanMorpher)) {
            return false;
        } else {
            BooleanMorpher other = (BooleanMorpher)obj;
            EqualsBuilder builder = new EqualsBuilder();
            if(this.isUseDefault() && other.isUseDefault()) {
                builder.append(this.getDefaultValue(), other.getDefaultValue());
                return builder.isEquals();
            } else {
                return !this.isUseDefault() && !other.isUseDefault()?builder.isEquals():false;
            }
        }
    }

    public Boolean getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        if(this.isUseDefault()) {
            builder.append(this.getDefaultValue());
        }

        return builder.toHashCode();
    }

    public Object morph(Object value) {
        if (value == null) {
            if (super.isUseDefault()) {
                return this.defaultValue;
            }
            throw new MorphException("value is null");
        }

        if (value instanceof Boolean)
            return value;
        if (value instanceof Number) {
            if ((value instanceof Double)
                    && (((Double.isInfinite(((Number) value).doubleValue())) || (Double
                    .isNaN(((Number) value).doubleValue()))))) {
                return true;
            }
            if ((value instanceof Float)
                    && (((Float.isInfinite(((Number) value).floatValue())) || (Float
                    .isNaN(((Number) value).floatValue()))))) {
                return true;
            }
            long longValue = ((Number) value).longValue();
            return longValue != 0L;
        }
        String s = String.valueOf(value);

        if ((s.equalsIgnoreCase("true")) || (s.equalsIgnoreCase("yes"))
                || (s.equalsIgnoreCase("on")) || (s.equalsIgnoreCase("1")))
            return true;
        if ((s.equalsIgnoreCase("false")) || (s.equalsIgnoreCase("no"))
                || (s.equalsIgnoreCase("off")) || (s.equalsIgnoreCase("0")))
            return false;
        if (super.isUseDefault()) {
            return this.defaultValue;
        }

        throw new MorphException("Can't morph value: " + value);
    }

    @Override
    public Class morphsTo() {
        return Boolean.TYPE;
    }
}
