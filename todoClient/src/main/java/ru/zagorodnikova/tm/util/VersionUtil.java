package ru.zagorodnikova.tm.util;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class VersionUtil {

    public static String getManifestInfo() throws Exception {
        @NotNull final Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
        while (resEnum.hasMoreElements()) {
            @NotNull final URL url = (URL) resEnum.nextElement();
            @NotNull final InputStream is = url.openStream();
            if (is != null) {
                @NotNull final Manifest manifest = new Manifest(is);
                @NotNull final Attributes mainAttribs = manifest.getMainAttributes();
                @NotNull final String builtBy =mainAttribs.getValue("Built-By");
                @NotNull final String mainClass =mainAttribs.getValue("Main-Class");
                @NotNull final String build =mainAttribs.getValue("Implementation-Build");
                @NotNull final String version =mainAttribs.getValue("Build-Jdk");
                if (version != null && builtBy != null && mainClass != null) {
                    return "Build by: " + builtBy + "\nMain-Class: " + mainClass + "\nBuild-Jdk: " + version + "\nImplementation-Build: " +build;
                }

            }
        }
        return null;
//        String version = Manifests.read("Main-Class");
//        return version;
    }
}
