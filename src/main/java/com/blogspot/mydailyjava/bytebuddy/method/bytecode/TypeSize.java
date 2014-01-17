package com.blogspot.mydailyjava.bytebuddy.method.bytecode;

import com.blogspot.mydailyjava.bytebuddy.method.MethodDescription;

import java.util.Arrays;

public enum TypeSize {

    NONE(0),
    SINGLE(1),
    DOUBLE(2);

    public static TypeSize of(Class<?> type) {
        if (type == void.class) {
            return NONE;
        } else if (type == double.class || type == long.class) {
            return DOUBLE;
        } else {
            return SINGLE;
        }
    }

    public static int sizeOf(MethodDescription methodDescription) {
        int parameterSize = sizeOf(Arrays.asList(methodDescription.getParameterTypes()));
        return (methodDescription.isStatic() ? 0 : 1) + parameterSize;
    }

    public static int sizeOf(Iterable<? extends Class<?>> types) {
        int size = 0;
        for (Class<?> type : types) {
            size += of(type).getSize();
        }
        return size;
    }

    private final int size;

    private TypeSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
