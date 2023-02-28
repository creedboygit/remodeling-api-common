package com.hanssem.remodeling.common.common.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hanssem.remodeling.common.common.model.CdnPath;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CdnPathSerializer extends JsonSerializer<CdnPath> {

	@Value("${cdn.image}")
    private String cdnImage;
	
	@Value("${cdn.file}")
    private String cdnFile;
	
//	public void serialize(String path, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//		if(path.startsWith("/imagePrefix")) 
//			jsonGenerator.writeString(path.replace("/imagePrefix", cdnImage));
//		else if(path.startsWith("/filePrefix")) 
//			jsonGenerator.writeString(path.replace("/filePrefix", cdnFile));
//		else
//			jsonGenerator.writeString(path);
//	}

	@Override
	public void serialize(CdnPath data, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		// TODO Auto-generated method stub
		gen.writeString(cdnImage + data.getValue());
	}

	


}
