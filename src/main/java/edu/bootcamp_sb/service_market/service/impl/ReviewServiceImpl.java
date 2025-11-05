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
import java.util.ArrayList;
import java.util.List;

import static edu.bootcamp_sb.service_market.service.impl.BookingServiceImpl.bookingEntityToBookingResponseDto;
import static edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl.entityToClientDto;
import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ClientRepository clientRepository;

    private final ProviderRepository providerRepository;

    private final BookingRepository bookingRepository;



    public static ReviewResponseDto reviewEntityToReviewDto(ReviewsEntity reviewsEntity){
       return ReviewResponseDto.builder()
               .id(reviewsEntity.getId())
               .reviewsProvider(
                       convertProviderEntityToProviderDto(reviewsEntity.getReviewsProvider())
               )
               .reviewsClient(entityToClientDto(reviewsEntity.getReviewsClient()))
               .booking(bookingEntityToBookingResponseDto(reviewsEntity.getBooking()))
               .comment(reviewsEntity.getComment())
               .providerResponse(reviewsEntity.getProviderResponse())
               .rating(reviewsEntity.getRating())
               .build();

    }


    @Override
    public ResponseEntity<ReviewResponseDto> giveAReview(ReviewsDto review) {

        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setRating(review.getRating());
        reviewsEntity.setComment(review.getComment());
        reviewsEntity.setProviderResponse(review.getProviderResponse());
        reviewsEntity.setCreatedAt(LocalDateTime.now());

        reviewsEntity.setReviewsClient(
                clientRepository.findById(review.getClientId()).orElseThrow(()->
                        new ClientHasBeenNotFoundException("Not found")
        ));

        reviewsEntity.setReviewsProvider(
                providerRepository.findById(review.getProviderId()).orElseThrow(()->
                       new ProviderHasBeenNotFoundException("Invalid Provider"))
        );

        reviewsEntity.setBooking(
                bookingRepository.findById(review.getBookingId()).orElseThrow(()->
                        new BookingHasNotFoundException("No booking was Done"))
        );

        ReviewsEntity save = reviewRepository.save(reviewsEntity);

        return ResponseEntity.ok().body(
                reviewEntityToReviewDto(save)
        );
    }

    @Override
    public ResponseEntity<List<ReviewResponseDto>> getAllResponses() {
        Iterable<ReviewsEntity> allReviews = reviewRepository.findAll();

        ArrayList<ReviewResponseDto> responseDtosList = new ArrayList<>();

        allReviews.forEach(entity->{
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setId(entity.getId());
            reviewResponseDto.setProviderResponse(entity.getProviderResponse());
            reviewResponseDto.setRating(entity.getRating());
            reviewResponseDto.setComment(entity.getComment());
            reviewResponseDto.setCreatedAt(entity.getCreatedAt());
            reviewResponseDto.setBooking(bookingEntityToBookingResponseDto(entity.getBooking()));
            reviewResponseDto.setReviewsProvider(
                    convertProviderEntityToProviderDto(entity.getReviewsProvider())
            );
            reviewResponseDto.setReviewsClient(entityToClientDto(entity.getReviewsClient()));
            responseDtosList.add(reviewResponseDto);

        });


        return ResponseEntity.ok(responseDtosList);
    }
}

