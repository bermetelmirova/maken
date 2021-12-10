package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Rating;
import kg.academy.maken.repository.RatingRepository;
import kg.academy.maken.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating deleteById(Long id) {
        Rating rating = findById(id);
        if (rating != null)
            ratingRepository.deleteById(id);
        return rating;
    }
}
