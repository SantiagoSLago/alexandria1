package com.lumbersoft.alexandria.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageDataService {






    public String uploadImageToFileSystem(MultipartFile image){


        if(!image.isEmpty()){
            Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
            String roothPath = directorioRecursos.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path rutaCompleta = Paths.get(roothPath + "//" + image.getOriginalFilename());
                Files.write(rutaCompleta,bytes);
                System.out.println("Se guardo la imagen compadre");


            } catch (IOException e) {
                e.printStackTrace();
            }

        }






        return "Imagen guardada con exito";
    }

//        String finalPath = PATH+image.getOriginalFilename();
//
//        ImageData imageData = dataRepo.save(ImageData.builder()
//                .name(image.getOriginalFilename())
//                .type(image.getContentType())
//                .filePath(finalPath).build());
//
//        image.transferTo(new File(finalPath));

//        if(imageData != null){
//            System.out.println("Image uploaded succesfully: " + finalPath);
//            return "Image uploaded succesfully: " + finalPath;
//        }
//        return null;











}
