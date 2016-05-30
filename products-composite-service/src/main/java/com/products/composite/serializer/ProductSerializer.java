package com.products.composite.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.products.composite.resource.Price;
import com.products.composite.resource.Product;

public class ProductSerializer extends JsonSerializer<Product> {

	@Override
	public void serialize(Product value, JsonGenerator generator, SerializerProvider provider)
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
		if (value.getDescription() != null && !"".equals(value.getDescription().trim())) {
			generator.writeFieldName("description");
			generator.writeString(value.getDescription().trim());
		}
		if (value.getTags() != null && !"".equals(value.getTags().trim())) {
			generator.writeFieldName("tags");
			generator.writeString(value.getTags().trim());
		}
		if (value.getPrices() != null && !value.getPrices().isEmpty()) {
			generator.writeFieldName("prices");
			generator.writeStartArray();
			for (Price price : value.getPrices()) {
				generator.writeObject(price);
			}
			generator.writeEndArray();
		}
		generator.writeEndObject();
	}
}
