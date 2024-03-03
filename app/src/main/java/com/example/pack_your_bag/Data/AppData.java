package com.example.pack_your_bag.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.pack_your_bag.Constants.myConstants;
import com.example.pack_your_bag.Database.RoomDb;
import com.example.pack_your_bag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AppData extends Application {
    RoomDb database;
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;


    public AppData(RoomDb database) {
        this.database = database;
    }

    public AppData(RoomDb database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData() {
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<>();
        basicItem.add(new Items("Visa", category, false));
        basicItem.add(new Items("Money", category, false));
        basicItem.add(new Items("Passport", category, false));
        basicItem.add(new Items("Travel insurance", category, false));
        basicItem.add(new Items("Tickets", category, false));
        basicItem.add(new Items("Wallet", category, false));
        basicItem.add(new Items("Driving License", category, false));
        basicItem.add(new Items("Currency", category, false));
        basicItem.add(new Items("House Key", category, false));
        basicItem.add(new Items("Book", category, false));
        basicItem.add(new Items("Travel Pillow", category, false));
        basicItem.add(new Items("Eye Patch", category, false));
        basicItem.add(new Items("Umbrella ", category, false));
        basicItem.add(new Items("Diary", category, false));

        return basicItem;
    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Tooth-brush", "Tooth-paste", "Face wash", "Mouthwash","Makeup", "Shaving Cream","Lotion","Hand cream", "Razor Blade", "Soap","Body wash",
                "Fiber", "Shampoo", "Hair Conditioner", "Brush", "Comb", "Hair Dryer", "Curling Iron", "hair Moulder", "Hair Clip","Hair ties", "Moisturizer", "Lip Cream",
                "Contact Lens", "Perfume", "Deodorant", "Makeup materials", "Makeup Remover", "Wet Wipes", "pad", "Ear stick", "Cotton",
                "Nail Polish", "Nail polish Remover", "Tweezers", "Nail Scissors", "Nail Files", "Suntan Cream","Tissues"};
        return prepareItemList(myConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"Stockings", "Pajamas", "T-Shirts","Blouses", "Casual Dress", "Evening Dress", "Shirt", "Cardigan",
                "Jacket", "Socks", "Sweater","Trousers", "Jeans", "Shorts","Sports Wear","Skirt", "Suit", "Coat", "Rain Coat", "Gloves", "Hat", "Scarf",
                "Bikini", "Belt", "Slipper", "Sneakers", "Casual Shoes", "Heeled shoes", "Sandals"};
        return prepareItemList(myConstants.CLOTHING_CAMEL_CASE, data);

    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Jumpsuit", "Baby Socks", "Baby Hat", "Baby pyjamas", "Baby Bath Towel", "Muslin", "Blanket",
                "Dribble Bibs", "Baby Laundry Detergent", "Baby Bottles", "Baby Food Thermos", "Baby bottle brushes",
                "Water Bottle", "Storage Container", "Baby Food spoon", "Highchairs", "Diaper", "Wet Wipes",
                "Baby Cotton", "Baby Care Cover", "Baby Shampoo", "Baby Soap", "Baby nail scissor", "Baby Moisturizer",
                "Diaper Rash Cream", "Serum Physiological", "Nasal Aspirator", "Fly Repellent Lotion", "Baby Backpack",
                "Stroller", "Baby Carrier", "Toys", "Baby Radio", "Non-slip sea shoes", "Baby sunglasses"};
        return prepareItemList(myConstants.BABY_NEEDS_CAMEL_CASE, data);

    }

    public List<Items> getHealthData() {
        String[] data = {"Aspirin", "Pain Reliever","Antacids","Antihistamines","Bandages","Gauze","Antiseptic wipes","Thermometer","Antibacterial Wipes",
                "Hand Sanitizer","Insect Repellent","Motion sickness medicine","Personalised Medications", "Vitamins Used", "Lens Solutions", "Hot Water Bag", "Adhesive Plaster",
                "First Aid Kit", "Contact Lens","Prescription eyewear", "Pain Killer", "Fever Reducer", "Diarrhea Stopper",
                "Pain Reliever Spray","Water purification tablets"};
        return prepareItemList(myConstants.HEALTH_CAMEL_CASE, data);
    }

    public List<Items> getTechnologyData() {
        String[] data = {"Smart Phone", "Phone Cover", "E-Book Reader", "Camera", "Camera Charger ", "Portable Speaker ",
                "IPad", "Headphones","Earbuds", "Laptop", "Laptop Charger", "Mouse", "Extension Cable", "USB flash drive",
                "Battery", "Power Bank", "DVD Player ", "Voltage Adapter","Universal travel adapter", "SD Card","International SIM card","Hard drive"
        ,"Travel apps","Smart Watch",};
        return prepareItemList(myConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

    public List<Items> getFoodData() {
        String[] data = {"Snacks","Nuts", "Sandwiches","Ketchup","Noodles","Soap","Oatmeal","Protein Shakes","", " Juices", "Tea Bags", "Coffee", "Water ",
                " Thermos", " Chips", "Baby Food"};
        return prepareItemList(myConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getBeachSuppliesData() {
        String[] data = {"Sea Glasses", "Sea Bed", "Sunscreen", " Beach Umbrella ", " Swim Fins", " Beach Bag",
                "Beach Towel", "Beach Slipper","Swimwear","Hat","Beach mat","WaterProof phone case","Trash Bags"};
        return prepareItemList(myConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getCarSupplies() {
        String[] data = {"Pump","Emergency kit","Vehicle documents", "Car Jack", "Spare Parts", "Accident Record Set", "Auto Refrigerator", "Car Cover",
                "Car Charger", "Window sun shades ","Paper towels","Motor oil","Windshield washer fluid","Coolant","Air compressor","Fire Extinguisher"};
        return prepareItemList(myConstants.CAR_SUPPLIES_CAMEL_CASE, data);

    }

    public List<Items> getNeedsData() {
        String[] data = {"BackPack", "Daily Bags ", "Laundry Bag ", " Sewing Kit", "Travel Lock", " Luggage Tag",
                "Magazine ", " Sports Equipment", "Important Numbers "};
        return prepareItemList(myConstants.NEEDS_CAMEL_CASE, data);
    }

    public List<Items> prepareItemList(String category, String[] data) {
        List<String> list = Arrays.asList(data);
        List<Items> datalist = new ArrayList<>();
        datalist.clear();

        for (int i = 0; i < list.size(); i++) {
            datalist.add(new Items(list.get(i), category, false));
        }

        return datalist;
    }

    public List<List<Items>> getAllData() {
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add((getBabyNeedsData()));
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getHealthData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSupplies());
        listOfAllItems.add(getNeedsData());
        listOfAllItems.add(getPersonalCareData());
        return listOfAllItems;

    }
  public void persistAllData(){
        List<List<Items>> listOfAllItems = getAllData();
        for(List<Items> list : listOfAllItems){
            for(Items items : list){
                database.mainDao().saveItems(items);
            }
        }
        System.out.println("Data added");
  }

  public void persistDataByCategory(String category,Boolean  onlyDelete ){
        try{
            List<Items> list = deleteAndGetListByCategory(category,onlyDelete);
            if(!onlyDelete){
                for(Items items:list){
                    database.mainDao().saveItems(items);
                }
                Toast.makeText(context,  "Reset Successfully.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
  }
  private List<Items> deleteAndGetListByCategory(String category,Boolean onlyDelete){
        if(onlyDelete){
           database.mainDao().deleteALlByCategoryAndAddedBy(category,myConstants.SYSTEM_SMALL);
        }else{
            database.mainDao().deleteAllByCategory(category);
        }

        switch (category){
            case myConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();
            case myConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();
            case myConstants.HEALTH_CAMEL_CASE:
                return getHealthData();
            case myConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();
            case myConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();
            case myConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();
            case myConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();
            case myConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSupplies();
            case myConstants.FOOD_CAMEL_CASE:
                return getFoodData();
            case myConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();

        }


  }
}

