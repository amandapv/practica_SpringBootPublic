package com.example.palmares_act_form.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URISyntaxException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path rootLocation; //rootLocation es un Path absoluto apuntando a la carpeta uploadDir dentro del módulo.

    //creo un constructor de la clase actual FileStorageService que calcula donde se guardarán los ficheros: 
    //  - Busca hacia arriba desde la ubicación de las clases hasta encontrar un pom.xml (se asume la raíz del módulo). Si lo encuentra, usa <module-root>/uploadDir. Si no lo encuentra o hay error, usa user.dir/uploadDir (directorio de trabajo de la JVM).
    public FileStorageService() {
        Path candidate;
        try {
            Path codePath = Paths.get(FileStorageService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Path dir = codePath;
            if (Files.isRegularFile(dir)) dir = dir.getParent();
            Path moduleRoot = null;
            // walk up until we find a pom.xml (module root)
            while (dir != null) {
                if (Files.exists(dir.resolve("pom.xml"))) {
                    moduleRoot = dir;
                    break;
                }
                dir = dir.getParent();
            }
            if (moduleRoot != null) candidate = moduleRoot.resolve("uploadDir");
            else candidate = Paths.get(System.getProperty("user.dir")).resolve("uploadDir");
        } catch (URISyntaxException e) {
            candidate = Paths.get(System.getProperty("user.dir")).resolve("uploadDir");
        }
        this.rootLocation = candidate; 
    }

    public String store(MultipartFile file, String dni) throws RuntimeException {
        if (file.isEmpty()) { //si el fichero está vacío...
            throw new RuntimeException("Fichero vacío");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("Fichero sin nombre");
        }
        String filename = StringUtils.cleanPath(originalFilename); //almacenar el nombre del fichero

        if (filename.contains("..")) {
            throw new RuntimeException("Fichero incorrecto");
        }
        String extension = StringUtils.getFilenameExtension(filename); //guardar la extensión del fichero
        String storedFilename = dni + "." + extension; //guardar el nuevo nombre del archivo, compuesto por su DNI y la extension

        try (InputStream inputStream = file.getInputStream()) {
            // Ensure target directory exists
            try {
                Files.createDirectories(this.rootLocation);
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el directorio de destino: " + this.rootLocation.toAbsolutePath(), e);
            }

            Files.copy(inputStream, this.rootLocation.resolve(storedFilename),
                    StandardCopyOption.REPLACE_EXISTING);
            return storedFilename;
        }
        catch (IOException ioe) {
            throw new RuntimeException("Error en escritura");
        } 
    }


    public void delete(String filename) throws RuntimeException {
        try {
            Path file = rootLocation.resolve(filename);
            if (!Files.exists(file)) throw new RuntimeException("No existe el fichero");
            Files.delete(file);
        }
        catch (Exception e) {
            throw new RuntimeException("Error en borrado");
        }
    }


    public Resource loadAsResource(String filename) throws RuntimeException {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) return resource;
            else throw new RuntimeException("Error IO");
        }
        catch (Exception e) {
            throw new RuntimeException("Error IO");
        }
    }

    /**
     * Devuelve la ruta absoluta del fichero almacenado dentro de rootLocation.
     */
    public String getAbsolutePath(String filename) {
        return this.rootLocation.resolve(filename).toAbsolutePath().toString();
    }

}
