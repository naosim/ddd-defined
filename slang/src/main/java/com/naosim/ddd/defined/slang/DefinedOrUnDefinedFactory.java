package com.naosim.ddd.defined.slang;

import com.naosim.ddd.defined.common.DefinedOrUnDefinedCommonFactory;
import javaslang.control.Either;


import java.util.function.Function;

public class DefinedOrUnDefinedFactory<U, D> {
    private final DefinedOrUnDefinedCommonFactory<U, D, Either<U, D>> definedOrUnDefinedFactory;
    public DefinedOrUnDefinedFactory(
            Function<String, U> undefinedFactory,
            D[] definedArray,
            Function<D, String> eqValue
    ) {
        definedOrUnDefinedFactory = new DefinedOrUnDefinedCommonFactory<>(
                undefinedFactory,
                definedArray,
                eqValue,
                Either::<U, D>left,
                Either::<U, D>right
        );
    }

    public Either<U, D> create(String value) {
        return definedOrUnDefinedFactory.create(value);
    }
}
