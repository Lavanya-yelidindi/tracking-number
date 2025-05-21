package com.example.tracking_number.service;

import java.security.SecureRandom;
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

	public TrackingNumberResponse generateTrackingNumber(String originCountry, String destinationCountry,
			UUID customerId) {

		if (!isValidCountryCode(originCountry) || !isValidCountryCode(destinationCountry)) {
			throw new IllegalArgumentException("Invalid ISO 3166-1 alpha-2 country code.");
		}
		String origin = originCountry.toUpperCase().substring(0, 1);
		String destination = destinationCountry.toUpperCase().substring(0, 1);

		// String uuidPart = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16).toUpperCase();

		// Timestamp format: yyMMddHH (no seconds) = 8 chars
		String timestamp = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH"));

		String sequenceStr = String.format("%03d", counter.incrementAndGet());

		String randomStr = generateRandomAlphaNumeric(3);

		// Final tracking number (2 + 10 + 4 = 16)
		String number = String.format("%s%s%s%s%s", origin, destination, timestamp, randomStr, sequenceStr);

		trackingRepo.save(new TrackingNumberRecord(number, customerId.toString(), OffsetDateTime.now()));
		System.out.println("Generated Tracking Number: " + number);

		return new TrackingNumberResponse(number, OffsetDateTime.now());
	}

	private boolean isValidCountryCode(String code) {
		return code != null && code.length() == 2 && new Locale("", code.toUpperCase()).getISO3Country() != null;
	}

	private String generateRandomAlphaNumeric(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}
}