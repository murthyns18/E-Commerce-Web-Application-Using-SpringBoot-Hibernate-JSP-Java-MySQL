package com.ns.ecommerce.main.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ns.ecommerce.main.model.Discount;
import com.ns.ecommerce.main.repository.DiscountRepository;

@Service
public class DiscountService 
{

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private FileUploadService fileUploadService;

    public Discount getDiscount() {
        return discountRepository.findTopByOrderByIdDesc();
    }

    public void saveOrUpdateDiscount(Discount discount, MultipartFile offerImage) {
        Discount existingDiscount = discountRepository.findTopByOrderByIdDesc();

        if (existingDiscount != null) 
        {
            existingDiscount.setOfferName(discount.getOfferName());
            existingDiscount.setDiscountPercentage(discount.getDiscountPercentage());
            existingDiscount.setAvailable(discount.isAvailable());

            if (offerImage != null && !offerImage.isEmpty()) 
            {
                try 
                {
                    String fileName = fileUploadService.uploadImage(offerImage);
                    existingDiscount.setImagePath(fileName);  
                } catch (IOException e) 
                {
                    System.err.println("Error uploading image: " + e.getMessage());
                }
            }

            discountRepository.save(existingDiscount);
        } else 
        {
            if (offerImage != null && !offerImage.isEmpty()) 
            {
                try
                {
                    String fileName = fileUploadService.uploadImage(offerImage);
                    discount.setImagePath(fileName); 
                } catch (IOException e) 
                {
                    System.err.println("Error uploading image: " + e.getMessage());
                }
            }

            discountRepository.save(discount);
        }
    }

    public Discount getLatestDiscount()
    {
        return discountRepository.findTopByOrderByIdDesc();
    }
}
