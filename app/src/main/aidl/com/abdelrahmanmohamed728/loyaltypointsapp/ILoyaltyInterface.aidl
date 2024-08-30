// ILoyaltyInterface.aidl
package com.abdelrahmanmohamed728.loyaltypointsapp;
import com.abdelrahmanmohamed728.loyaltypointsapp.IDiscountCallback;

interface ILoyaltyInterface {
    void getPriceAfterDiscount(float price,float discount,IDiscountCallback discountAppliedCallbacks);
}