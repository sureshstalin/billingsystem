package com.itgarden.mapper.impl;

import com.itgarden.dto.OfferInfo;
import com.itgarden.entity.Offer;
import com.itgarden.mapper.OfferMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T09:21:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class OfferMapperImpl implements OfferMapper {

    @Override
    public Offer offerInfoToOffer(OfferInfo offerInfo) {
        if ( offerInfo == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setId( offerInfo.getId() );
        offer.setOfferCode( offerInfo.getOfferCode() );
        offer.setOfferName( offerInfo.getOfferName() );
        offer.setOfferDescription( offerInfo.getOfferDescription() );
        offer.setStatus( offerInfo.getStatus() );

        return offer;
    }

    @Override
    public OfferInfo offerToOfferInfo(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferInfo offerInfo = new OfferInfo();

        offerInfo.setId( offer.getId() );
        offerInfo.setOfferCode( offer.getOfferCode() );
        offerInfo.setOfferName( offer.getOfferName() );
        offerInfo.setOfferDescription( offer.getOfferDescription() );
        offerInfo.setStatus( offer.getStatus() );

        return offerInfo;
    }
}
