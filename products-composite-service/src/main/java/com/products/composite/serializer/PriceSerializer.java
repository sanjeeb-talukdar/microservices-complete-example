package com.products.composite.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.products.composite.resource.Price;

public class PriceSerializer extends JsonSerializer<Price> {
	@Override
	public void serialize(Price value, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeStartObject();
		if (value.getProductId() != null) {
			generator.writeFieldName("productId");
			generator.writeNumber(value.getProductId());
		}
		if (value.getCurrency() != null) {
			generator.writeFieldName("currency");
			generator.writeString(value.getCurrency().getName());
		}
		if (value.getPrice() != null) {
			generator.writeFieldName("price");
			generator.writeNumber(value.getPrice());
		}
		generator.writeEndObject();
	}
}