package com.example.Job.App.review;


import com.example.Job.App.company.Company;
import com.example.Job.App.company.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Locale.filter;

@Service
public class ReviewServiceImpl implements  ReviewService{

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
//
   // @Autowired this is optional with single constructor also works
   // in Spring 4.3 and later versions and This will fix the NPE — but it’s lazy and fragile.
   // It’s better to use constructor injection for mandatory dependencies.
   // https://stackoverflow.com/questions/41138013/spring-autowired-null-pointer-exception
   // https://www.baeldung.com/spring-autowire



    public ReviewServiceImpl(ReviewRepository reviewRepository , CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }



    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

//

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;

        }else{
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return  reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean  updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            reviewRepository.save(updatedReview);
            return true;

        }else{
            return false;
        }
        // Implementation for updating a review
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
//            review.setCompany(null);
//            reviewRepository.delete(review);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;

        }else{
            return false;
        }

    }


}
