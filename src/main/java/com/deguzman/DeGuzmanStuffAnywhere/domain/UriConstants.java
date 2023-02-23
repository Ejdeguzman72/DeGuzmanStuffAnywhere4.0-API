package com.deguzman.DeGuzmanStuffAnywhere.domain;

public class UriConstants {

	// AUTHENTICATION URI
	public static final String SIGN_IN = "/api/auth/signin";
	public static final String SIGN_UP = "/api/auth/signup";
	
	// AUTOSHOP URI
	public static final String GET_ALL_AUTO_SHOP = "/app/auto-repair-shops/all";
	public static final String GET_ALL_AUTO_SHOPS_PAGINATION = "/app-auto-repair-shops/all-shops";
	public static final String GET_AUTO_SHOP_BY_ID = "/app/auto-repair-shops/repair-shop/id";
	public static final String GET_AUTO_SHOP_BY_NAME = "/app/auto-repair-shops/repair-shop/name";
	public static final String GET_AUTO_SHOP_BY_ZIP = "/app/auto-repair-shops/repair-shop/zip";
	public static final String GET_AUTO_SHOP_COUNT = "/app/auto-repair-shops/count";
	public static final String ADD_AUTO_SHOP = "/app/auto-repair-shops/add";
	public static final String UPDATE_AUTO_SHOP = "/app/auto-repairs/updat";
	public static final String DELETE_AUTO_SHOP = "/app/auto-repair-shops/delete";
	public static final String DELETE_ALL_AUTO_SHOPS = "/app/auto-repair-shops/delete-all";
	
	// AUTO TRANSACTION URI
	public static final String GET_ALL_AUTO_TRX = "/app/auto-transactions/all";
	public static final String GET_ALL_AUTO_TRX_PAGINATION = "/app/auto-transactions/all-transactions";
	public static final String GET_AUTO_TRX_BY_VEHICLE = "/app/auto-transactions/transaction/vehicle";
	public static final String GET_AUTO_TRX_BY_USER = "/app/auto-transactions/transaction/user";
	public static final String GET_AUTO_TRX_BY_TYPE = "/app/auto-transactions/transaction/type";
	public static final String GET_AUTO_TRX_DTO_BY_ID = "/app/auto-transactions/transaction-dto/id";
	public static final String GET_AUTO_TRX_BY_ID = "/app/auto-transactions/transaction-id";
	public static final String GET_AUTO_TRX_COUNT = "/app/auto-transactions/count";
	public static final String ADD_AUTO_TRX = "/app/auto-transactions/add";
	public static final String UPDATE_AUTO_TRX = "/app/auto-transactions/update";
	public static final String DELETE_AUTO_TRX = "/app/auto-transactions/delete";
	public static final String DELETE_ALL_AUTO_TRX = "/app/auto-transactions/delete-all";
	
	// BOOK URI
	public static final String GET_ALL_BOOKS = "/app/books/all";
	public static final String GET_ALL_BOOKS_PAGINATION = "/app/books/all-books";
	public static final String GET_BOOKS_BY_AUTHOR = "/app/books/book/author";
	public static final String GET_BOOK_BY_ID = "/app/books/book/id";
	public static final String GET_BOOK_BY_NAME = "/app/books/book/name";
	public static final String ADD_BOOK_INFORMATION = "/app/books/add";
	public static final String UPDATE_BOOK_INFORMATION = "/app/books/update";
	public static final String DELETE_BOOK = "/app/books/delete";
	public static final String DELETE_ALL_BOOKS = "/app/books/delete-all";
	
	// CONTACT INFO URI
	public static final String GET_ALL_CONTACTS = "/app/person-info/all";
	public static final String GET_ALL_CONTACTS_PAGINATION = "/app/person-info/all-contacts";
	public static final String GET_CONTACT_BY_ID = "/app/person-info/contact/id";
	public static final String GET_CONTACT_BY_LASTNAME = "/app/person-info/contact/lastname";
	public static final String GET_CONTACT_BY_EMAIL = "/app/person-info/contact/email";
	public static final String GET_CONTACT_BY_PHONE = "/app/person-info/contact/phone";
	public static final String GET_CONTACT_COUNT = "/app/person-info/count";
	public static final String ADD_CONTACT_INFORMATON = "/app/person-info/add";
	public static final String UPDATE_CONTACT_INFORMATION = "/app/person-info/update";
	public static final String DELETE_CONTACT = "/app/person-info/delete";
	public static final String DELETE_ALL_CONTACTS = "/app/person-info/delete-all";
	
	// EXERCISE INFO URI
	public static final String GET_ALL_EXERCISE_INFO = "/app/gym-tracker/all";
	public static final String GET_ALL_EXERCISE_PAGINATION = "/app/gym-tracker/all-exercises";
	public static final String GET_EXERCISES_BY_USER = "/app/gym-tracker/exercises/user";
	public static final String GET_EXERCISES_BY_TYPE = "/app/gym-tracker/exercises/type";
	public static final String GET_EXERCISE_BY_ID = "/app/gym-tracker/exercise/id";
	public static final String GET_EXERCISE_DTO_BY_ID = "/app/gym-tracker/exercise/id";
	public static final String ADD_EXERCISE_INFORMATION = "/app/gym-tracker/add";
	public static final String UPDATE_EXERCISE_INFORMATION = "/app/gym-tracker/update";
	public static final String DELETE_EXERCISE = "/app/gym-tracker/delete";
	public static final String DELETE_ALL_EXERICSE = "/app/gym-tracker/delete-all";
			
	// EXERCISE TYPE INFO URI
	public static final String GET_ALL_EXERCISE_TYPES = "/app/exercise-type/all";
	public static final String GET_EXERCISE_TYPE_BY_ID = "/app/exercise-type/type/id";
	
	// GENERAL TRX INFO URI
	public static final String GET_ALL_GENERAL_TRX_INFO = "/app/general-transactions/all";
	public static final String GET_ALL_GENERAL_TRX_PAGINATION = "/app/general-transactions/all-transactions";
	public static final String GET_GENERAL_TRANSACTIONS_BY_TYPE = "/app/general-transactions/transaction/type";
	public static final String GET_GENERAL_TRANSACTIONS_BY_USER = "/app/general-transactions/transaction/user";
	public static final String GET_GENERAL_TRANSACTION_DTO_BY_ID = "/app/general-transactions/transaction-dto/id";
	public static final String GET_GENERAL_TRANSACTION_BY_ID = "/app/general-transactions/transaction/id";
	public static final String GET_GENERAL_TRANSACTION_COUNT = "/app/general-transactions/count";
	public static final String ADD_GENERAL_TRX_INFORMATION = "/app/general-transactions/add";
	public static final String UPDATE_GENERAL_TRX_INFORMATION = "/app/general-transactions/update";
	public static final String DELETE_GENERAL_TRX = "/app/general-transactions/delete";
	public static final String DELETE_ALL_GENERAL_TRX = "/app/general-transactions/delete-all";
	
	// HAPI URI 
	public static final String RUN_HAPI_APP = "/app/hapi-app/run-application";
	
	// HOME INFO URI 
	public static final String GET_HOME_INFORMATION = "/app/home-info/all";
	
	// MEDICAL OFFICE URI 
	public static final String GET_ALL_MEDICAL_OFFICES = "/app/medical-offices/all";
	public static final String GET_ALL_MEDICAL_OFFICES_PAGINATION = "/app/medical-offices/all-medical-offices";
	public static final String GET_MEDICAL_OFFICES_BY_ZIP = "/app/medical-offices/offices/zip";
	public static final String GET_MEDICAL_OFFICE_BY_ID = "/app/medical-offices/medical-office/id";
	public static final String GET_MEDICAL_OFFICE_COUNT = "/app/medical-offices/count";
	public static final String ADD_MEDICAL_OFFICE = "/app/medical-offices/add";
	public static final String UPDATE_MEDICAL_OFFICE = "/app/medical-offices/update";
	public static final String DELETE_MEDICAL_OFFICE = "/app/medical-offices/delete";
	public static final String DELETE_ALL_MEDICAL_OFFICES = "/app/medical-offices/delete-all";
	
	// MEDICAL TRX URI
	public static final String GET_ALL_MEDICAL_TRX_INFO = "/app/medical-transactions/all";
	public static final String GET_ALL_MEDICAL_TRX_PAGINATION = "/app/medical-transactions/all-transactions";
	public static final String GET_MEDICAL_TRX_BY_FACILIY = "/app/medical-transactions/transactions/facility";
	public static final String GET_MEDICAL_TRX_BY_TYPE = "/app/medical-transactions/transactions/type";
	public static final String GET_MEDICAL_TRX_BY_USER = "/app/medical-transactions/transactions/user";
	public static final String GET_MEDICAL_TRX_BY_ID = "/app/medical-transactions/transaction/id";
	public static final String GET_MEDICAL_TRX_DTO_BY_ID = "/app/medical-transactions/transaction-dto/id";
	public static final String GET_MEDICAL_TRX_COUNT = "/app/medical-transactions/get-medical-trx-count";
	public static final String ADD_MEDICAL_TRX = "/app/medical-transactions/add";
	public static final String UPDATE_MEDICAL_TRX = "/app/medical-transactions/update";
	public static final String DELETE_MEDICAL_TRX = "/app/medical-transactions/delete";
	public static final String DELETE_ALL_MEDICAL_TRX = "/app/medical-transactions/delete-all";
	
	// POST URI 
	public static final String GET_ALL_POSTS = "/app/posts/all";
	public static final String GET_ALL_POSTS_PAGINATION = "/app/posts/all-posts";
	public static final String GET_POSTS_BY_USER = "/app/posts/user";
	public static final String ADD_POST_INFORMATION = "/app/posts/add";
	public static final String DELETE_POST = "/app/posts/delete";
	
	// RESTAURANT URI 
	public static final String GET_ALL_RESTAURANTS = "/app/restaurants/all";
	public static final String GET_ALL_RESTAURANTS_PAGINATION = "/app/restaurants/all-restaurants";
	public static final String GET_RESTAURANTS_BY_TYPE = "/app/restaurants/all/type";
	public static final String GET_RESTAURANTS_BY_ZIP = "/app/restaurants/all/zip";
	public static final String GET_RESTAURANTS_BY_DESCR = "/app/restaurants/all/descr";
	public static final String GET_RESTAURANT_DTO_BY_ID = "/app/restaurants/restaurant-dto/id";
	public static final String GET_RESTAURANT_BY_ID = "/app/restaurants/restaurant/id";
	public static final String GET_RESTAURANT_BY_NAME = "/app/restaurants/restaurant/name";
	public static final String GET_RESTAURANT_COUNT = "/app/restaurants/count";
	public static final String ADD_RESTAURANT_INFORMATION = "/app/restaurants/add";
	public static final String UPDATE_RESTAURANT_INFORMATION = "/app/restaurants/update";
	public static final String DELETE_RESTAURANT = "/app/restaurants/delete";
	public static final String DELETE_ALL_RESTAURANTS = "/app/restaurants/delete-all";
	
	// RESTAURANT TYPES URI 
	public static final String GET_ALL_RESTAURANT_TYPES = "/app/restaurants-types/all";
	public static final String GET_RESTAURANT_TYPE_BY_ID = "/app/restaurants-types/type/id";
	public static final String GET_RESTAURANT_TYPE_BY_DESCR = "/app/restaurants-types/type/descr";
	public static final String GET_RESTAURANT_TYPE_COUNT = "/app/restaurants-types/type-count";
	
	// RUN TRACKER URI 
	public static final String GET_ALL_RUN_INFORMATION = "/app/run-tracker-app/all";
	public static final String GET_ALL_RUN_PAGINATION = "/app/run-tracker-app/all-runs";
	public static final String GET_RUNS_BY_IUSER = "/app/run-tracker-app/all/user";
	public static final String GET_RUN_BY_ID = "/app/run-tracker-app/run/id";
	public static final String GET_RUN_DTO_BY_ID = "/app/run-tracker-app/run-dto/id";
	public static final String GET_RUN_COUNT = "/app/run-tracker-app/count";
	public static final String ADD_RUN = "/app/run-tracker-app/add";
	public static final String UPDATE_RUN = "/app/run-tracker-app/update";
	public static final String DELETE_RUN = "/app/run-tracker-app/delete";
	public static final String DELETE_ALL_RUNS = "/app/run-tracker-app/delete-all";
	
	// SONG URI 
	public static final String GET_ALL_SONG_INFORMATION = "/app/music/all";
	public static final String GET_ALL_SONG_PAGINATION = "/app/music/all-songs";
	public static final String GET_SONG_BY_ID = "/app/music/song/id";
	public static final String GET_SONGS_BY_ARTIST = "/app/music/al/artist";
	public static final String GET_SONGS_BY_GENRE = "/app/music/all/gere";
	public static final String GET_SONGS_COUNT = "/app/music/count-of-songs";
	public static final String ADD_SONG = "/app/music/add-song-information";
	public static final String UPDATE_SONG = "/app/music/update";
	public static final String DELETE_SONG = "/app/music/delete";
	public static final String DELETE_ALL_SONGS = "/app/music/delete-all";
	
	// TRANSACTION TYPES URI 
	public static final String GET_ALL_TRX_TYPE = "/app/transaction-types/all";
	public static final String GET_TRX_TYPE_BY_ID = "/app/transaction-types/type/id";
	public static final String GET_TRX_TYPE_DESCR = "/app/transaction-types/type/descr";
	
	// USER INFO URI 
	public static final String GET_ALL_USERS = "/app/users/all";
	
	// UTILITY URI 
	public static final String GET_ALL_UTILITY_INFORMATION = "/app/utility-information/all";
	public static final String GET_UTILITY_INFO_BY_DUE_DATE = "/app/utility-information/all/dueDate";
	public static final String GET_UTILITY_BY_ID = "/app/utility-information/utility/id";
	public static final String GET_UTILITY_BY_NAME = "/app/utility-information/utility/name";
	public static final String GET_UTILITY_BY_TYPE = "/app/utility-information/utility/type";
	public static final String GET_UTILITY_COUNT = "/app/utility-information/count";
	public static final String ADD_UTILITY = "/app/utility-information/add";
	public static final String UPDATE_UTILITY = "/app/utility-information/update";
	public static final String DELETE_UTILITY = "/app/utility-information/delete";
	public static final String DELETE_ALL_UTILITY= "/app/utility-information/delete-all";
	
	public static final String GET_ALL_VEHICLES = "/app/vehicles/all";
	public static final String GET_ALL_VEHICLES_PAGINATION = "/app/vehicles/all-vehicles";
	public static final String GET_VEHICLES_BY_MAKE = "/app/vehicles/all/make";
	public static final String GET_VEHICLES_BY_MODEL = "/app/vehicles/all/model";
	public static final String GET_VEHICLES_BY_YEAR = "/app/vehicles/all/year";
	public static final String GET_VEHICLES_BY_TRANSMISSION = "/app/vehicles/all/transmission";
	public static final String GET_VEHICLE_BY_ID = "/app/vehicles/vehicle/id";
	public static final String GET_VEHICLE_COUNT = "/app/vehicles/count";
	public static final String ADD_VEHICLE = "/app/vehicles/add";
	public static final String UPDATE_VEHICLE = "/app/vehicles/update";
	public static final String DELETE_VEHICLE = "/app/vehicles/delete";
	public static final String DELETE_ALL_VEHILCES = "/app/vehicles/delete-all";
	
}
