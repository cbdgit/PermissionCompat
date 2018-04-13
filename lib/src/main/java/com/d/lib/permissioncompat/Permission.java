package com.d.lib.permissioncompat;

import java.util.List;

public class Permission {
    public final String name;
    public final boolean granted;
    public final boolean shouldShowRequestPermissionRationale;

    public Permission(String name, boolean granted) {
        this(name, granted, false);
    }

    public Permission(String name, boolean granted, boolean shouldShowRequestPermissionRationale) {
        this.name = name;
        this.granted = granted;
        this.shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale;
    }

    public Permission(List<Permission> permissions) {
        name = combineName(permissions);
        granted = combineGranted(permissions);
        shouldShowRequestPermissionRationale = combineShouldShowRequestPermissionRationale(permissions);
    }

    @Override
    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Permission that = (Permission) o;

        if (granted != that.granted) return false;
        if (shouldShowRequestPermissionRationale != that.shouldShowRequestPermissionRationale)
            return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (granted ? 1 : 0);
        result = 31 * result + (shouldShowRequestPermissionRationale ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", granted=" + granted +
                ", shouldShowRequestPermissionRationale=" + shouldShowRequestPermissionRationale +
                '}';
    }

    private String combineName(List<Permission> permissions) {
        StringBuilder s = new StringBuilder();
        for (Permission permission : permissions) {
            if (s.length() == 0) {
                s.append(permission.name);
            } else {
                s.append(", ").append(permission.name);
            }
        }
        return s.toString();
    }

    private Boolean combineGranted(List<Permission> permissions) {
        boolean granted = true;
        for (Permission permission : permissions) {
            granted = granted && permission.granted;
        }
        return granted;
    }

    private Boolean combineShouldShowRequestPermissionRationale(List<Permission> permissions) {
        boolean shouldShowRequestPermissionRationale = true;
        for (Permission permission : permissions) {
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale
                    && permission.shouldShowRequestPermissionRationale;
        }
        return shouldShowRequestPermissionRationale;
    }
}