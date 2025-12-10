package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.ReviewsDto;
import edu.bootcamp_sb.service_market.dto.reponse.ReviewResponseDto;
import edu.bootcamp_sb.service_market.entity.ReviewsEntity;
import edu.bootcamp_sb.service_market.exception.booking_exception.BookingHasNotFoundException;
import edu.bootcamp_sb.service_market.exception.client_exceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.*;
import edu.bootcamp_sb.service_market.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static edu.bootcamp_sb.service_market.service.impl.ClientServiceImpl.entityToClientDto;
import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;
import static edu.bootcamp_sb.service_market.service.impl.ServiceGigServiceImpl.convertGigEntityToGigResponseEntity;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ClientRepository clientRepository;

    private final ProviderRepository providerRepository;

    private final ServiceGigRepository serviceGigRepository;



    public static ReviewResponseDto reviewEntityToReviewDto(ReviewsEntity reviewsEntity){
       return ReviewResponseDto.builder()
               .id(reviewsEntity.getId())
               .reviewsProvider(
                       convertProviderEntityToProviderDto(reviewsEntity.getReviewsProvider())
               )
               .reviewsClient(entityToClientDto(reviewsEntity.getReviewsClient()))
               .serviceGigResponseDto(convertGigEntityToGigResponseEntity(reviewsEntity.getServiceGigEntity()))
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

        reviewsEntity.setServiceGigEntity(
                serviceGigRepository.findById(review.getServiceGigId()).orElseThrow(()->
                        new BookingHasNotFoundException("No gig was found"))
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
            reviewResponseDto.setServiceGigResponseDto(
                    convertGigEntityToGigResponseEntity(entity.getServiceGigEntity())
            );
            reviewResponseDto.setReviewsProvider(
                    convertProviderEntityToProviderDto(entity.getReviewsProvider())
            );
            reviewResponseDto.setReviewsClient(entityToClientDto(entity.getReviewsClient()));
            responseDtosList.add(reviewResponseDto);

        });


        return ResponseEntity.ok(responseDtosList);
    }

    @Override
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewInGig(UUID id) {

        Iterable<ReviewsEntity> allByServiceGigEntityId =
                reviewRepository.findAllByServiceGigEntityId(id);

        ArrayList<ReviewResponseDto> responseDtos = new ArrayList<>();


        allByServiceGigEntityId.forEach(reviewsEntity ->
                responseDtos.add(reviewEntityToReviewDto(reviewsEntity))
        );

        return ResponseEntity.ok(responseDtos);
    }
}

