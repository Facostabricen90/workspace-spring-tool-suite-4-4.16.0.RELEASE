package com.misiontic.celuplanet.services.implement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.misiontic.celuplanet.services.interfaces.IcloudinaryService;

@Service

public class CloudinaryServiceImplement implements IcloudinaryService {

	public Cloudinary context () {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("cloud_name", "dyh6doh8w");
        valuesMap.put("api_key", "615787764495731");
        valuesMap.put("api_secret", "ikOfJvU4noVp9Le9DzTwmUR2PeM");
		return  new Cloudinary(valuesMap);
	}
	
	@Override
	public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
		 File file = this.convert(multipartFile);
	     Map<?, ?> result = this.context().uploader().upload(file, ObjectUtils.emptyMap());
	     file.delete();
	     return result;
	}

	@Override
	public Map<?, ?> delete(String id) throws IOException {
		Map<?, ?> result = this.context().uploader().destroy(id, ObjectUtils.emptyMap());
	     return result;
	}

	@Override
	public File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
	}
	
	
	
}
