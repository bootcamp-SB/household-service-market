package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.ReviewsDto;
import edu.bootcamp_sb.service_market.dto.reponse.ReviewResponseDto;
import edu.bootcamp_sb.service_market.entity.ReviewsEntity;
import edu.bootcamp_sb.service_market.exception.booking_exception.BookingHasNotFoundException;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.BookingRepository;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.repository.ReviewRepository;
import edu.bootcamp_sb.service_market.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ClientRepository clientRepository;

    private final ProviderRepository providerRepository;

    private final BookingRepository bookingRepository;



    @Override
    public ResponseEntity<ReviewResponseDto> giveAReview(ReviewsDto review) {
        `

        return null;
    }
}

