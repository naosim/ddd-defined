package com.naosim.ddd.defined.common;

import java.util.function.Function;
import java.util.stream.Stream;

public class DefinedOrUnDefinedFactory<U, D, R> {
    private final Function<String, U> undefinedFactory;
    private final D[] definedArray;
    private final Function<D, String> eqValue;
    private final Function<U, R> callbackForUndefined;
    private final Function<D, R> callbackForDefined;

    public DefinedOrUnDefinedFactory(
            Function<String, U> undefinedFactory,
            D[] definedArray,
            Function<D, String> eqValue,
            Function<U, R> callbackForUndefined,
            Function<D, R> callbackForDefined
    ) {
        this.undefinedFactory = undefinedFactory;
        this.definedArray = definedArray;
        this.eqValue = eqValue;
        this.callbackForUndefined = callbackForUndefined;
        this.callbackForDefined = callbackForDefined;
    }

    public R create(String value) {
        return Stream.of(definedArray)
                .filter(v -> value.equals(eqValue.apply(v)))
                .findFirst()
                .map(v -> callbackForDefined.apply(v))
                .orElseGet(() -> callbackForUndefined.apply(undefinedFactory.apply(value)));
    }
}
