package com.pricing.core.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pricing.core.data.Price;

public class PriceSerializer extends JsonSerializer<Price> {
	  @Override
	  public void serialize(Price value, JsonGenerator generator,
	            SerializerProvider provider) throws IOException,
	            JsonProcessingException {
	    generator.writeStartObject();
	    generator.writeFieldName("productId");
	    generator.writeNumber(value.getProductId());
	    generator.writeFieldName("currency");
	    generator.writeString(value.getCurrency()!= null? value.getCurrency().getName(): null);
	    generator.writeFieldName("price");
	    generator.writeNumber(value.getPrice());
	    generator.writeEndObject();
	  }
	}