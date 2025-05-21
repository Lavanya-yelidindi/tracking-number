package com.example.tracking_number.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tracking_number.model.TrackingNumberRecord;
import com.example.tracking_number.model.TrackingNumberResponse;
import com.example.tracking_number.repository.TrackingRecordRepository;

@Service
public class TrackingNumberService {

	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	TrackingRecordRepository trackingRepo;

	public TrackingNumberResponse generateTrackingNumber(String originCountry, String destinationCountry, OffsetDateTime createdAt, UUID customerId) {

		if (!isValidCountryCode(originCountry) || !isValidCountryCode(destinationCountry)) {
			throw new IllegalArgumentException("Invalid ISO 3166-1 alpha-2 country code.");
		}
		String origin = originCountry.substring(0, 1);
		String destination = destinationCountry.substring(0, 1);

		// Timestamp format: yyMMddHHmm (no seconds) = 10 chars
		String timestamp = createdAt.format(DateTimeFormatter.ofPattern("yyMMddHHmm"));

		String sequenceStr = String.format("%04d", counter.incrementAndGet());

		// Final tracking number (2 + 10 + 4 = 16)
		String number = String.format("%s%s%s%s", origin, destination, timestamp, sequenceStr);
		
		trackingRepo.save(new TrackingNumberRecord(number, customerId.toString(), OffsetDateTime.now()));
	    System.out.println("Generated Tracking Number: " + number);

		return new TrackingNumberResponse(number, OffsetDateTime.now());
	}

	private boolean isValidCountryCode(String code) {
		return code != null && code.length() == 2 && new Locale("", code.toUpperCase()).getISO3Country() != null;
	}
}