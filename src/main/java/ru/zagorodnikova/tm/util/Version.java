package ru.zagorodnikova.tm.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Version {

    public static String getManifestInfo() {
        Enumeration resEnum;
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL) resEnum.nextElement();
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String builtBy =mainAttribs.getValue("Built-By");
                        String mainClass =mainAttribs.getValue("Main-Class");
                        String build =mainAttribs.getValue("Implementation-Build");
                        String version =mainAttribs.getValue("Build-Jdk");
                        if (version != null && builtBy != null && mainClass != null) {
                            return "Build by: " + builtBy + "\nMain-Class: " + mainClass + "\nBuild-Jdk: " + version + "\nImplementation-Build: " +build;
                        }

                    }
                } catch (Exception e) {
                    // Silently ignore wrong manifests on classpath?
                }
            }
        } catch (IOException e1) {
            // Silently ignore wrong manifests on classpath?
        }
        return null;
    }
}
