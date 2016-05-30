package com.products.core.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.products.core.data.Product;

public class ProductSerializer extends JsonSerializer<Product> {

	  @Override
	  public void serialize(Product value, JsonGenerator generator,
	            SerializerProvider provider) throws IOException,
	            JsonProcessingException {
	    generator.writeStartObject();
	    generator.writeFieldName("id");
	    generator.writeNumber(value.getId());
	    generator.writeFieldName("name");
	    generator.writeString(value.getName());
	    generator.writeFieldName("description");
	    generator.writeString(value.getDescription());
	    generator.writeFieldName("tags");
	    generator.writeString(value.getTags());
	    generator.writeEndObject();
	  }
	}
