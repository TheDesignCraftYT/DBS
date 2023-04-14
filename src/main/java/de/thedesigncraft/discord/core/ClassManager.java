package de.thedesigncraft.discord.core;

import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class ClassManager {

    /**
     * Returns a list of all classes in a package that extend a specific class
     *
     * @param packageName The package to search in
     * @param superClass  The class that the classes should extend
     * @return A list of all classes in the package that extend the specified class
     */
    @NotNull
    public static List<Class<?>> getClasses(@NotNull String packageName, @NotNull Class<?> superClass) {
        return new ArrayList<>(new Reflections(packageName).getSubTypesOf(superClass));
    }

    /**
     * Returns a list of all classes in a package that extend a specific class, excluding the specified classes
     *
     * @param packageName      The package to search in
     * @param superClass       The class that the classes should extend
     * @param excludedClasses  The classes that should be excluded from the list
     * @return A list of all classes in the package that extend the specified class, excluding the specified classes
     */
    @NotNull
    public static List<Class<?>> getClasses(@NotNull String packageName, Class<?> superClass, @NotNull List<Class<?>> excludedClasses) {
        List<Class<?>> classes = getClasses(packageName, superClass);
        if (excludedClasses.isEmpty())
            return classes;
        classes.removeAll(excludedClasses);
        return classes;
    }

    /**
     * Returns a list of all classes in a package that extend a specific class
     * @param packageName The package to search in
     * @param superClass The class that the classes should extend
     * @return A list of all classes in the package that extend the specified class
     */
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

    /**
     * Returns a list of all classes in a package that extend a specific class, excluding the specified classes
     * @param packageName The package to search in
     * @param superClass The class that the classes should extend
     * @param excludedClasses The classes that should be excluded from the list
     * @return A list of all classes in the package that extend the specified class, excluding the specified classes
     */
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
