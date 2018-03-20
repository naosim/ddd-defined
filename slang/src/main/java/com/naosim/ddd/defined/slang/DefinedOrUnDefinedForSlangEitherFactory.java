package com.naosim.ddd.defined.slang;

import com.naosim.ddd.defined.common.DefinedOrUnDefinedFactory;
import javaslang.control.Either;


import java.util.function.Function;

public class DefinedOrUnDefinedForSlangEitherFactory<U, D> {
    private final DefinedOrUnDefinedFactory<U, D, Either<U, D>> definedOrUnDefinedFactory;
    public DefinedOrUnDefinedForSlangEitherFactory(
            Function<String, U> undefinedFactory,
            D[] definedArray,
            Function<D, String> eqValue
    ) {
        definedOrUnDefinedFactory = new DefinedOrUnDefinedFactory<>(
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
