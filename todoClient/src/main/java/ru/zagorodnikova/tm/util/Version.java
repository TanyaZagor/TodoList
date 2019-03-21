package ru.zagorodnikova.tm.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Version {

    public static String getManifestInfo() throws Exception {
        Enumeration resEnum;
        resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
        while (resEnum.hasMoreElements()) {
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
        }
        return null;
//        String version = Manifests.read("Main-Class");
    }
}
