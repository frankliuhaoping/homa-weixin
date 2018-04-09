package cn.cnyirui.framework.utils;

import org.springframework.core.ResolvableType;

public class ReflectUtils {

    /**
     * 得到指定类型的指定位置的泛型实参
     *
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        ResolvableType resolvableType = ResolvableType.forType(clazz.getGenericSuperclass());
        return (Class<T>) resolvableType.getGeneric(index).resolve();
    }
}
