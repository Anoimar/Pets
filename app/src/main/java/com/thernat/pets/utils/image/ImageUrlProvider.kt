package com.thernat.pets.utils.image

import dagger.Reusable
import javax.inject.Inject

/**
 * Created by m.rafalski on 2019-06-27.
 */
@Reusable
class ImageUrlProvider @Inject constructor() {



    fun getPetTypeImage(type: String): String {
        return when (type) {
            "Cat" -> "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80"
            "Dog" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRz3ngjjW5-wWglQTmyBb0BMzIFiIPg93XKPkr8iLoLicgF94sBgg"
            "Mouse" -> "https://cdn.images.express.co.uk/img/dynamic/130/590x/secondary/A-mouse-1028634.jpg"
            "Bear" -> "https://www.glendaleca.gov/Home/ShowPublishedImage/19260/635787882276930000"
            "Hamster" -> "http://www.scienceshorts.com/wp-content/uploads/2018/02/Hamster.jpg"
            "Dragon" -> "https://i.ytimg.com/vi/jg6bz4x8Rvo/hqdefault.jpg"
            "Goldfish" -> "https://www.petstock.com.au/images/cache/product_feature/images/products/5c366e3fce3661.30579854.jpeg"
            "Snake" -> "https://www.irishcentral.com/uploads/article/130328/cropped_MI_snake_green_getty.jpg?t=1546706211"
            else -> "https://images-na.ssl-images-amazon.com/images/I/51lh93vBeRL._SY679_.jpg"
        }
    }

}