package com.ns.ecommerce.main.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ns.ecommerce.main.model.Discount;
import com.ns.ecommerce.main.repository.DiscountRepository;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private FileUploadService fileUploadService;

    public Discount getDiscount() {
        List<Discount> discounts = discountRepository.findAll();
        return discounts.isEmpty() ? null : discounts.get(0);
    }

    public void saveOrUpdateDiscount(Discount discount, MultipartFile offerImage) {
        List<Discount> discounts = discountRepository.findAll();

        if (offerImage != null && !offerImage.isEmpty()) {
            try {
                String fileName = fileUploadService.uploadImage(offerImage);
                discount.setImagePath(fileName);
                System.out.println("Image Filename: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!discounts.isEmpty()) {
            Discount existingDiscount = discounts.get(0);
            existingDiscount.setOfferName(discount.getOfferName());
            existingDiscount.setDiscountPercentage(discount.getDiscountPercentage());
            existingDiscount.setAvailable(discount.isAvailable());

            if (discount.getImagePath() != null) {
                existingDiscount.setImagePath(discount.getImagePath());
            }

            discountRepository.save(existingDiscount);
        } else {
            discountRepository.save(discount);
        }
    }

    public Discount getLatestDiscount() {
        return discountRepository.findTopByOrderByIdDesc();
    }
}
