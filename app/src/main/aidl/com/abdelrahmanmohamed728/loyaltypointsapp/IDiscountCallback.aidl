// IDiscountCallback.aidl
package com.abdelrahmanmohamed728.loyaltypointsapp;

oneway interface IDiscountCallback {
    void onPriceCalculated(in float remainingPrice);
    void onNoRemainingAmount();
}
