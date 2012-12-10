package org.projectodd.nodej.bindings.buffer;

import org.dynjs.runtime.AbstractNativeFunction;
import org.dynjs.runtime.DynArray;
import org.dynjs.runtime.DynObject;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.PropertyDescriptor;
import org.dynjs.runtime.Types;
import org.projectodd.nodej.bindings.buffer.prototype.ByteLength;

public class BufferType extends  AbstractNativeFunction { 
    public BufferType(GlobalObject globalObject) {
        super(globalObject);
        this.setClassName("SlowBuffer");
        final DynObject prototype = initializePrototype(globalObject);
        defineOwnProperty(null, "prototype", new PropertyDescriptor() {
            {
                set("Value", prototype);
                set("Writable", false);
                set("Configurable", false);
                set("Enumerable", false);
            }
        }, false);
        setPrototype(prototype);
    }

    @Override
    public Object call(ExecutionContext context, Object self, Object... args) {
        long length = 0;
        Buffer buffer;
        if (args[0] instanceof DynArray) {
            DynArray items = (DynArray) args[0];
            length = Types.toUint32(context, items.get(context, "length"));
            buffer = new Buffer(context.getGlobalObject(), length);
            for(int i=0; i<length; i++) {
                buffer.write(Types.toString(context, items.get(context, "" + i)), Buffer.Encoding.UTF8, i, 1);
            }
        } else {
            length = (long) args[0];
            buffer = new Buffer(context.getGlobalObject(), length);
        }
        buffer.setPrototype(this.getPrototype());
        return buffer;
    }
    
    private DynObject initializePrototype(GlobalObject globalObject) {
        final DynObject prototype = new DynObject(globalObject);
        prototype.defineReadOnlyProperty(globalObject, "byteLength", new ByteLength(globalObject));
        prototype.defineOwnProperty(null, "constructor", new PropertyDescriptor() {
            {
                set("Value", this);
                set("Writable", false);
                set("Configurable", false);
                set("Enumerable", true);
            }
        }, false);
        // This is an internal function of SlowBuffer - atm, a noop is OK
        // https://github.com/joyent/node/blob/master/src/node_buffer.cc#L695
        prototype.defineReadOnlyProperty(globalObject, "makeFastBuffer", new AbstractNativeFunction(globalObject) {
            @Override
            public Object call(ExecutionContext context, Object self, Object... args) {
                return Types.UNDEFINED;
            }
            
        });
        return prototype;
    }
    
}