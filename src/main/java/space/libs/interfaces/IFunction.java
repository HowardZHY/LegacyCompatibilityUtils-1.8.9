package space.libs.interfaces;

import com.google.common.base.Function;

public interface IFunction<F, T> extends Function<F, T> {

    @Override
    T apply(F f);

}
