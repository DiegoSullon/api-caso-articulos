package com.caso.articulos.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    Cloudinary cloudinary = Singleton.getCloudinary();

    public CloudinaryService() {

    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {
        Map result = null;
        try {
            result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        } catch (Exception e) {
                System.out.println("Error1: " + e);
            try {
                result = cloudinary.uploader().deleteByToken(id);
            } catch (Exception h) {
                System.out.println("Error2: " + h);
            }
        }
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}