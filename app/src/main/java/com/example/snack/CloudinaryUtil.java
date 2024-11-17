package com.example.snack;

import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryUtil {



    private static final String CLOUD_NAME = "daniel";
    private static final String API_KEY = "648497256752256";
    private static final String API_SECRET = "ECmMd2ARre9izCGuYf_6eoyQIcU";

    private static Cloudinary cloudinary;

    // Método para inicializar o Cloudinary
    public static Cloudinary getCloudinary() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", CLOUD_NAME,
                    "api_key", API_KEY,
                    "api_secret", API_SECRET
            ));
        }
        return cloudinary;
    }

    // Método para fazer o upload de uma imagem
    public static String uploadImage(File imageFile) {
        try {
            Map<String, String> uploadResult = getCloudinary().uploader().upload(imageFile, ObjectUtils.emptyMap());
            return uploadResult.get("url");
        } catch (IOException e) {
            Log.e("CloudinaryUtil", "Erro ao fazer upload da imagem", e);
            return null;
        }
    }
}
