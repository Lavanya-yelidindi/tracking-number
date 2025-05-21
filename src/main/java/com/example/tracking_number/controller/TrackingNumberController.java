package com.example.tracking_number.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking_number.model.TrackingNumberResponse;
import com.example.tracking_number.service.TrackingNumberService;

@RestController
@RequestMapping("/api")
public class TrackingNumberController {

	@Autowired
	private TrackingNumberService service;

	@GetMapping("/next-tracking-number")
	public ResponseEntity<?> getNextTrackingNumber(@RequestParam String origin_country_id,
			@RequestParam String destination_country_id, @RequestParam UUID customer_id) {
		try {
			TrackingNumberResponse trackingNumber = service.generateTrackingNumber(origin_country_id,
					destination_country_id, customer_id);
			return ResponseEntity.ok(trackingNumber);
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body("Unexpected error occurred.");
		}
	}
}