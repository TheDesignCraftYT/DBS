package de.thedesigncraft.dbs.manage;

import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class ClassManager {

    @NotNull
    public static List<Class<?>> getClasses(@NotNull String packageName, @NotNull Class<?> superClass) {
        return new ArrayList<>(new Reflections(packageName).getSubTypesOf(superClass));
    }

    @NotNull
    public static List<Class<?>> getClasses(@NotNull String packageName, Class<?> superClass, @NotNull List<Class<?>> excludedClasses) {
        List<Class<?>> classes = getClasses(packageName, superClass);
        if (excludedClasses.isEmpty())
            return classes;
        classes.removeAll(excludedClasses);
        return classes;
    }

    @NotNull
    public static List<Object> getInstantiatedClasses(@NotNull String packageName, @NotNull Class<?> superClass) {
        List<Object> returnList = new ArrayList<>();
        getClasses(packageName, superClass).forEach(aClass -> {
            try {
                //noinspection deprecation
                returnList.add(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
        });
        return returnList;
    }

    @NotNull
    public static List<Object> getInstantiatedClasses(@NotNull String packageName, @NotNull Class<?> superClass, @NotNull List<Class<?>> excludedClasses) {
        if (excludedClasses.isEmpty()) {
            return getInstantiatedClasses(packageName, superClass);
        } else {
            List<Object> returnList = new ArrayList<>();
            getClasses(packageName, superClass, excludedClasses).forEach(aClass -> {
                try {
                    //noinspection deprecation
                    returnList.add(aClass.newInstance());
                } catch (InstantiationException | IllegalAccessException ignored) {
                }
            });
            return returnList;
        }
    }

}
