package com.products.composite.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.products.composite.resource.Catalogue;
import com.products.composite.resource.Product;

public class CatalogueSerializer extends JsonSerializer<Catalogue> {

	@Override
	public void serialize(Catalogue value, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeStartObject();
		if (value.getId() != null && value.getId() > 0) {
			generator.writeFieldName("id");
			generator.writeNumber(value.getId());
		}
		if (value.getName() != null && !"".equals(value.getName().trim())) {
			generator.writeFieldName("name");
			generator.writeString(value.getName().trim());
		}
		if (value.getProducts() != null && !value.getProducts().isEmpty()) {
			generator.writeFieldName("products");
			generator.writeStartArray();
			for (Product product : value.getProducts()) {
				generator.writeObject(product);
			}
			generator.writeEndArray();
		}
		generator.writeEndObject();
	}
}
