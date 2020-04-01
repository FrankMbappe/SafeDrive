package com.gm.safedrive.banks;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.Brand;

import java.util.ArrayList;

public class BrandBank implements IBank<Brand> {
    private ArrayList<Brand> mBrands;

    public BrandBank() {
        mBrands = new ArrayList<>();
        mBrands.add(new Brand("Alpha Romeo", R.drawable.brand_color_alfaromeo));
        mBrands.add(new Brand("Audi", R.drawable.brand_color_audi));
        mBrands.add(new Brand("BMW", R.drawable.brand_color_bmw));
        mBrands.add(new Brand("Cadillac", R.drawable.brand_color_cadillac));
        mBrands.add(new Brand("Chevrolet", R.drawable.brand_color_chevrolet));
        mBrands.add(new Brand("Dodge", R.drawable.brand_color_dodge));
        mBrands.add(new Brand("Ferrari", R.drawable.brand_color_ferrari));
        mBrands.add(new Brand("Fiat", R.drawable.brand_color_fiat));
        mBrands.add(new Brand("Ford", R.drawable.brand_color_ford));
        mBrands.add(new Brand("GMC", R.drawable.brand_color_gmc));
        mBrands.add(new Brand("Honda", R.drawable.brand_color_honda));
        mBrands.add(new Brand("Hyundai", R.drawable.brand_color_hyundai));
        mBrands.add(new Brand("Infinity", R.drawable.brand_color_infiniti));
        mBrands.add(new Brand("Jaguar", R.drawable.brand_color_jaguar));
        mBrands.add(new Brand("Kia", R.drawable.brand_color_kia));
        mBrands.add(new Brand("Lamborghini", R.drawable.brand_color_lamborghini));
        mBrands.add(new Brand("Land-Rover", R.drawable.brand_color_landrover));
        mBrands.add(new Brand("Lexus", R.drawable.brand_color_lexus));
        mBrands.add(new Brand("Maserati", R.drawable.brand_color_maserati));
        mBrands.add(new Brand("Mazda", R.drawable.brand_color_mazda));
        mBrands.add(new Brand("Mercedes-Benz", R.drawable.brand_color_mercedesbenz));
        mBrands.add(new Brand("Mini", R.drawable.brand_color_mini));
        mBrands.add(new Brand("Nissan", R.drawable.brand_color_nissan));
        mBrands.add(new Brand("Porsche", R.drawable.brand_color_porsche));
        mBrands.add(new Brand("Rolls Royce", R.drawable.brand_color_rollsroyce));
        mBrands.add(new Brand("Subaru", R.drawable.brand_color_subaru));
        mBrands.add(new Brand("Suzuki", R.drawable.brand_color_suzuki));
        mBrands.add(new Brand("Tesla", R.drawable.brand_color_tesla));
        mBrands.add(new Brand("Toyota", R.drawable.brand_color_toyota));
        mBrands.add(new Brand("Volkswagen", R.drawable.brand_color_volswagen));
    }

    @Override
    public ArrayList<Brand> getAll() {
        return mBrands;
    }

    public Brand getBrand(String name){
        for (Brand brand : mBrands) {
            if(brand.getName().toLowerCase().contains(name.toLowerCase())){
                return brand;
            }
        }
        return null;
    }

    public ArrayList<Brand> getBrands(String search){
        ArrayList<Brand> brands = new ArrayList<>();
        for (Brand currentBrand : mBrands) {
            if(currentBrand.getName().toLowerCase().contains(search.toLowerCase())){
                brands.add(currentBrand);
            }
        }
        return brands;
    }

}
