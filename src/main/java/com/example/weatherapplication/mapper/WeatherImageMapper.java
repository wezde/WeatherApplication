//package com.example.weatherapplication.mapper;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class WeatherImageMapper {
//
//    private static final Map<String, String> WEATHER_IMAGES = new HashMap<>();
//
//    static {
//        WEATHER_IMAGES.put("ясно", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.photos-public-domain.com%2Fwp-content%2Fuploads%2F2011%2F02%2Fbright-sun-in-blue-sky.jpg&f=1&nofb=1&ipt=b581935b95855d38ed7f8cc50d4feb33e8be8b3a985bc6fdd7d9550c0b444f6c&ipo=images");
//        WEATHER_IMAGES.put("переменная облачность", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.Rutd3St6BQW4rj0cx2f9OwHaF7%26pid%3DApi&f=1&ipt=a8de4e3245507d064e1bd401db24b906dbe0a9ed75a5aeb84ecc57338f029136&ipo=images");
//        WEATHER_IMAGES.put("облачно", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fgetwallpapers.com%2Fwallpaper%2Ffull%2F7%2F6%2F6%2F505110.jpg&f=1&nofb=1&ipt=4b88ddc069be0e798a4e0c321c36d65be56055776b5cc9ad4436185fe97fc0b6&ipo=images");
//        WEATHER_IMAGES.put("дождь", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.GwdK1jjtUA8fNJvi6g4KGQHaFZ%26pid%3DApi&f=1&ipt=1e3ba8346de074d29f256c50b0407e56c71b4213166e5eecb6ff4a01750d595a&ipo=images");
//        WEATHER_IMAGES.put("гроза", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.2amPI9MDBQWmVj4RAj5ydQHaEo%26pid%3DApi&f=1&ipt=b953589046febc1d55866faae1e9e07e498d9afcfd6c9747ed2144bc6bb9a750&ipo=images");
//        WEATHER_IMAGES.put("снег", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.2j8jIXDUPh2Z2NxwQoRi1wHaEn%26pid%3DApi&f=1&ipt=83d38f4f0fdeece39e98effe929575aa88823d253994cd38d4ca0b305f1028b7&ipo=images");
//        WEATHER_IMAGES.put("туман", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.jHAxeAbgP6BqbLwJNM5B6wHaE9%26pid%3DApi&f=1&ipt=43df642c315da302c548d1c06485bc879d56541b7cd77b4e779b8db92980cce8&ipo=images");
//    }
//
//    public static String getImageUrl(String weatherDescription) {
//        return WEATHER_IMAGES.getOrDefault(weatherDescription, "https://example.com/default.png");
//    }
//}
